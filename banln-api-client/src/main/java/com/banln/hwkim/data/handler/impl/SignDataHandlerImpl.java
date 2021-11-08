package com.banln.hwkim.data.handler.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class SignDataHandlerImpl {
    private final Logger logger = LoggerFactory.getLogger(SignDataHandlerImpl.class);
}
