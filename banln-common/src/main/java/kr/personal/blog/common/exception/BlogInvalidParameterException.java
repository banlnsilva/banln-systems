package kr.personal.blog.common.exception;

import java.security.InvalidParameterException;

public class BlogInvalidParameterException extends InvalidParameterException {
    public BlogInvalidParameterException(String message) {
        super(message);
    }
}
