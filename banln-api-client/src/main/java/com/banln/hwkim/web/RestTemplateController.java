package com.banln.hwkim.web;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/rest-template")
public class RestTemplateController {
    RestTemplateService restTemplateService;
}
