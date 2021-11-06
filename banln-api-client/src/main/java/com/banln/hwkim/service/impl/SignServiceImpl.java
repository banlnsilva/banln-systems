package com.banln.hwkim.service.impl;

import com.banln.hwkim.config.security.JwtTokenProvider;
import com.banln.hwkim.service.SignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SignServiceImpl implements SignService {
    private final Logger logger = LoggerFactory.getLogger(SignServiceImpl.class);

    public SignDataHandler signDataHandler;
    public JwtTokenProvider jwtTokenProvider;
    public PasswordEncoder passwordEncoder;
}
