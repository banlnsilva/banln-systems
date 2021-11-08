package com.banln.hwkim.config.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.security.Key;
import java.util.Date;
import java.util.List;

/**
 * JWT 토큰을 생성하고 유효성을 검증하는 컴포넌트 클래스 JWT 는 여러 암호화 알고리즘을 제공하고 알고리즘과 비밀키를 가지고 토큰을 생성
 * <p>
 * claim 정보에는 토큰에 부가적으로 정보를 추가할 수 있음 claim 정보에 회원을 구분할 수 있는 값을 세팅하였다가 토큰이 들어오면 해당 값으로 회원을 구분하여 리소스
 * 제공
 * <p>
 * JWT 토큰에 expire time을 설정할 수 있음
 */
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    private final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
    private final UserDetailsService userDetailsService;

    @Value("spring.jwt.secret")
    private String secretKey;
    private long tokenValidMillisecond = 1000L * 60 * 60; // 1시간 토큰 유효
    private Key accessKey;

    /**
     * SecretKey 에 대해 인코딩 수행
     */
    @PostConstruct
    protected void init() {
        logger.info("[init] JwtTokenProvider 내 secretKey 초기화 시작");
        accessKey = Keys.hmacShaKeyFor(secretKey.getBytes());
        logger.info("[init] JwtTokenProvider 내 secretKey 초기화 완료");
    }

    //JWT 토큰 생성
    public String createToken(String userPk, List<String> roles) {
        logger.info("[createToken] 토큰 생성");
        Claims claims = Jwts.claims().setSubject(userPk);
        claims.put("roles", roles);
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) //데이타
                .setIssuedAt(now) //토큰 발행일자
                .setExpiration(new Date(now.getTime() + tokenValidMillisecond)) // 토큰 유효시간 설정
                .signWith(accessKey, SignatureAlgorithm.HS256) //암호화 알고리즘, 암호키
                .compact();
    }

    // JWT 토큰으로 인증 정보 조회
    public Authentication getAuthentication(String token) {
        logger.info("[getAuthentication] 토큰 인증 정보 조회 시작");
        UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserPk(token));
        logger.info("[getAuthentication] 토큰 인증 정보 조회 완료, UserDetails UserName : {}", userDetails.getUsername());
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    // JWT 토큰에서 회원 구별 정보 추출
    public String getUserPk(String token) {
        logger.info("[getUserPk] 토큰 기반 회원 구별 정보 추출");
        String info = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(token).getBody().getSubject();

        logger.info("[getUserPk] 토큰 기반 회원 구별 정보 추출 완료, info : {}", info);

        return info;
    }

    /**
     * HTTP Request Header 에 설정된 토큰 값을 가져옴
     *
     * @param request Http Request Header
     * @return String type Token 값
     */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }

    //Jwt 토큰의 유효성 + 만료일자 확인
    public boolean validateToken(String jwtToken) {
        logger.info("[validateToken] 토큰 유효 체크 시작");
        try {
            Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(accessKey).build().parseClaimsJws(jwtToken);
            logger.info("[validateToken] 토큰 유효 체크 완료");
            return !claims.getBody().getExpiration().before(new Date());
        } catch(Exception e) {
            logger.info("[validateToken] 토큰 유효 체크 예외 발생");
            return false;
        }
    }
}
