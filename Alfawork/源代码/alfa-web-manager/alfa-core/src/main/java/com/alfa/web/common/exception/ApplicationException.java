package com.alfa.web.common.exception;

/**
 * Created by Administrator on 2017/4/24.
 */
public class ApplicationException extends RuntimeException {
    public ApplicationException() {
    }

    public ApplicationException(String message) {
        super(message);
    }
}
