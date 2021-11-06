package com.banln.hwkim.web;

import com.banln.hwkim.service.RestTemplateService;
import com.banln.hwkim.web.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {
    private final RestTemplateService restTemplateService;

    @GetMapping(value = "/banln-hub")
    public String getBanlnHub() {
        return restTemplateService.getBanlnHub();
    }

    @GetMapping(value = "/name")
    public String getName() {
        return restTemplateService.getName();
    }

    @GetMapping("/name2")
    public String getName2() {
        return restTemplateService.getName2();
    }

    @PostMapping(value = "/dto")
    public ResponseEntity<MemberDto> postDto() {
        return restTemplateService.postDto();
    }

    @PostMapping(value = "/add-header")
    public ResponseEntity<MemberDto> addHeader() {
        return restTemplateService.addHeader();
    }

}
