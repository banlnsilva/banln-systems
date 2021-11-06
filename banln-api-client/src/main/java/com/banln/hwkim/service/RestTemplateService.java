package com.banln.hwkim.service;

import com.banln.hwkim.web.dto.MemberDto;
import org.springframework.http.ResponseEntity;

public interface RestTemplateService {
    public String getBanlnHub();
    public String getName();
    public String getName2();
    public ResponseEntity<MemberDto> postDto();
    public ResponseEntity<MemberDto> addHeader();
}
