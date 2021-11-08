package com.banln.hwkim.service.impl;

import com.banln.hwkim.common.CommonResponse;
import com.banln.hwkim.common.Constants;
import com.banln.hwkim.common.exception.BanlnHubException;
import com.banln.hwkim.config.security.JwtTokenProvider;
import com.banln.hwkim.data.dto.SignInResultDto;
import com.banln.hwkim.data.dto.SignUpResultDto;
import com.banln.hwkim.data.entity.UserEntity;
import com.banln.hwkim.data.handler.SignDataHandler;
import com.banln.hwkim.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class SignServiceImpl implements SignService {
    private final Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    public SignDataHandler signDataHandler;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;

    @Autowired
    public SignServiceImpl(SignDataHandler signDataHandler, JwtTokenProvider jwtTokenProvider,
                           PasswordEncoder passwordEncoder) {
        this.signDataHandler = signDataHandler;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SignUpResultDto getSignUpResult(String id, String password, String name) {
        logger.info("[getSignUpResult] signDataHandler 로 회원 가입 정보 전달");

        UserEntity userEntity = signDataHandler.saveUserEntity(id, passwordEncoder.encode(password),
                name, Collections.singletonList("ROLL_USER"));
        SignUpResultDto signUpResultDto = new SignUpResultDto();

        logger.info("[getSignUpResult] userEntity 값이 들어왔는지 확인 후 결과값 주입");
        if(!userEntity.getName().isEmpty()) {
            logger.info("[getSignUpResult] 정상 처리 완료");
            setSuccessResult(signUpResultDto);
        } else {
            logger.info("[getSignUpResult] 실패 처리 완료");
            setFailResult(signUpResultDto);
        }

        return signUpResultDto;
    }

    @Override
    public SignInResultDto getSignInResult(String id, String password) throws BanlnHubException {
        logger.info("[getSignInResult] signDataHandler 로 회원 정보 요청");

        UserEntity userEntity = signDataHandler.getByUid(id);
        logger.info("[getSignInResult] Id : {}", id);
        logger.info("[getSignInResult] 패스워드 비교 수행");

        if(!passwordEncoder.matches(password, userEntity.getPassword())) {
            throw new BanlnHubException(Constants.ExceptionClass.SIGN, HttpStatus.BAD_REQUEST, "패스워드가 틀렸습니다.");
        }
        logger.info("[getSignInResult] 패스워드 일치");
        logger.info("[getSignInResult] SignInResultDto 객체 생성");

        SignInResultDto signInResultDto = SignInResultDto.builder()
                .token(jwtTokenProvider.createToken(String.valueOf(userEntity.getMsrl()), userEntity.getRoles())).build();
        logger.info("[getSignInResult] SignInResultDto 객체에 값 주입");

        setSuccessResult(signInResultDto);

        return signInResultDto;
    }

    private void setSuccessResult(SignUpResultDto result) {
        result.setSuccess(true);
        result.setCode(CommonResponse.SUCCESS.getCode());
        result.setMsg(CommonResponse.SUCCESS.getMsg());
    }

    private void setFailResult(SignUpResultDto result) {
        result.setSuccess(false);
        result.setCode(CommonResponse.FAIL.getCode());
        result.setMsg(CommonResponse.FAIL.getMsg());
    }
}
