package com.fms.fmsback.exception;

import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {

    /**
     * Error Code
     */
    private Integer code;

    /**
     * Service Exception
     * @param code
     * @param message
     */
    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    };

}
