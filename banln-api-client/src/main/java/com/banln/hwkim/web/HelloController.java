package com.banln.hwkim.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
//    private final static Logger LOGGER = LoggerFactory.getLogger(HelloController.class);
    private final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!!";
    }

    @PostMapping("log-test")
    public void logTest() {
        LOGGER.trace("Trace Log");
        LOGGER.debug("Debug Log");
        LOGGER.info("Info Log");
        LOGGER.warn("Warn Log");
        LOGGER.error("Error Log");

    }

    @PostMapping("/exception")
    public void exceptionTest() throws Exception {
        throw new Exception();
    }
}
