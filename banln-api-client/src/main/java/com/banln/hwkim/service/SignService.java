package com.banln.hwkim.service;

import com.banln.hwkim.common.exception.BanlnHubException;
import com.banln.hwkim.data.dto.SignInResultDto;
import com.banln.hwkim.data.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto getSignUpResult(String id, String password, String name);
    SignInResultDto getSignInResult(String id, String password) throws BanlnHubException;
}
