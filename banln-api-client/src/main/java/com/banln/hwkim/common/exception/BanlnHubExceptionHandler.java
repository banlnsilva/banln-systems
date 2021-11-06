package com.banln.hwkim.common.exception;

<<<<<<< HEAD
import org.apache.catalina.filters.RestCsrfPreventionFilter;
=======
>>>>>>> origin/master
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class BanlnHubExceptionHandler {
<<<<<<< HEAD
    private final Logger LOGGER= LoggerFactory.getLogger(BanlnHubExceptionHandler.class);
=======
    private final Logger logger = LoggerFactory.getLogger(BanlnHubExceptionHandler.class);
>>>>>>> origin/master

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(Exception e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

<<<<<<< HEAD
        LOGGER.info("Advice 내 ExceptionHandler 호출");
=======
        logger.error("Advice 내 ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());
>>>>>>> origin/master

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }

    @ExceptionHandler(value = BanlnHubException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(BanlnHubException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
<<<<<<< HEAD
        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("error code", Integer.toString(e.getHttpStatusCode()));
=======

        Map<String, String> map = new HashMap<>();
        map.put("error type", e.getHttpStatusType());
        map.put("error code", Integer.toString(e.getHttpStatusCode())); // Map<String, Object>로 설정하면 toString 불필요
>>>>>>> origin/master
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map, responseHeaders, e.getHttpStatus());
    }
}
