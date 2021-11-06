package com.banln.hwkim.service.impl;

import com.banln.hwkim.service.RestTemplateService;
import com.banln.hwkim.web.dto.MemberDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.lang.reflect.Member;
import java.net.URI;

@Service
public class RestTemplateServiceImpl implements RestTemplateService {
    private final Logger LOGGER = LoggerFactory.getLogger(RestTemplateServiceImpl.class);
    private final String myDomain = "http://localhost:9090";

    @Override
    public String getBanlnHub() {
        URI uri = UriComponentsBuilder
                .fromUriString(myDomain)
                .path("/api/server/around-hub")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status Code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString(myDomain)
                .path("/api/server/name")
                .queryParam("name", "Banlnsilva")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status Code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public String getName2() {
        URI uri = UriComponentsBuilder
                .fromUriString(myDomain)
                .path("/api/server/path-variable/{name}")
                .encode()
                .build()
                .expand("Banlnsilva") // 복수의 값을 넣어야할 경우 , 를 추가하여 구분
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        LOGGER.info("status Code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<MemberDto> postDto() {
        URI uri = UriComponentsBuilder
                .fromUriString(myDomain)
                .path("/api/server/member")
                .queryParam("name", "Banlnsilva")
                .queryParam("email", "banlnsilva@naver.com")
                .queryParam("organization", "BANLN Hub")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("Banlnsilva");
        memberDto.setEmail("banlnsilva@naver.com");
        memberDto.setOrganization("BANLN Hub");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);

        LOGGER.info("status Code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }

    @Override
    public ResponseEntity<MemberDto> addHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString(myDomain)
                .path("/api/server/add-header")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("Banlnsilva");
        memberDto.setEmail("banlnsilva@naver.com");
        memberDto.setOrganization("BANLN Hub");

        RequestEntity<MemberDto> requestEntity = RequestEntity
                .post(uri)
                .header("banln-header", "BANLN Hub")
                .body(memberDto);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);

        LOGGER.info("status Code : {}", responseEntity.getStatusCode());
        LOGGER.info("body : {}", responseEntity.getBody());

        return responseEntity;
    }
}
