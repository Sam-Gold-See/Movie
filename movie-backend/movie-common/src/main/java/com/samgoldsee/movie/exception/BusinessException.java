package com.samgoldsee.movie.exception;

/**
 * 业务异常父类
 */
public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
