package kr.personal.blog.api.config;

import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class ApiProcessInterceptor  implements AsyncHandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestUri = request.getRequestURI();
System.out.println("1111111111111111111111111111111111111>>>" + requestUri);
        if(Objects.equals(requestUri, "/hello")) {
            String token = request.getHeader("X-AUTH-TOKEN");
System.out.println("2222222222222222222222222222222222222>>>" + token);
            if(!Objects.equals(token, "sampletoken")) {
System.out.println("3333333333333333333333333333333333333>>>" + token);
                return false;
            }
        }

System.out.println("4444444444444444444444444444444444>>>");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
    }
}
