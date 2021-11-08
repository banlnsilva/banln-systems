package com.banln.hwkim.web;

import com.banln.hwkim.common.Constants;
import com.banln.hwkim.common.exception.BanlnHubException;
import com.banln.hwkim.data.dto.SignInResultDto;
import com.banln.hwkim.data.dto.SignUpResultDto;
import com.banln.hwkim.service.SignService;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/sign-api")
public class SignController {
    private final Logger logger = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(@ApiParam(value = "ID", required = true) @RequestParam String id,
                                  @ApiParam(value = "Password", required = true) @RequestParam String password)
    throws BanlnHubException {
        logger.info("[signIn] 로그인을 시도하고 있습니다. id : {}, pw : ****", id);
        SignInResultDto signInResultDto = signService.getSignInResult(id, password);
        if(signInResultDto.getCode() == 0) {
            logger.info("[signIn] 정상적으로 로그인되었습니다. id : {}, token : {}", id, signInResultDto.getToken());
        }

        return signInResultDto;
    }

    @PostMapping(value = "sign-up")
    public SignUpResultDto signUp(@ApiParam(value = "ID", required = true) @RequestParam String id,
                                  @ApiParam(value = "비밀번호", required = true) @RequestParam String password,
                                  @ApiParam(value = "이름", required = true) @RequestParam String name) {
        logger.info("[signUp] 회원가입을 수행합니다. id : {}, password : ****, name : {}", id, name);
        SignUpResultDto signUpResultDto = signService.getSignUpResult(id, password, name);

        logger.info("[signUp] 회원가입을 완료했습니다. id : {}", id);

        return signUpResultDto;

    }

    @PostMapping(value = "/exception")
    public void exceptionTest() throws BanlnHubException {
        throw new BanlnHubException(Constants.ExceptionClass.SIGN, HttpStatus.FORBIDDEN, "접근이 금지되었습니다.");
    }
}
