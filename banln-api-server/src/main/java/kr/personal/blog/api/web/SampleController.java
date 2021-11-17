package kr.personal.blog.api.web;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import kr.personal.blog.api.web.dto.SampleResponseDto;
import kr.personal.blog.common.exception.BlogInvalidParameterException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class SampleController {

    @GetMapping("/api/sample")
    public String apiSample(@RequestParam(required = false) String testKey) {
        if(!"test".equals(testKey)) {
            throw new BlogInvalidParameterException("잘못된 파라메터입니다.:" + testKey);
        }

        return "call sample test api";
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/hello")
    public String hello(@RequestParam(required = false) String testKey) {
        return "hello1111";
    }

    @GetMapping("/hello/dto")
    public SampleResponseDto helloDto(@RequestParam("name") String name,
                                      @RequestParam("amount") int amount) {
        return new SampleResponseDto(name, amount);
    }

}
