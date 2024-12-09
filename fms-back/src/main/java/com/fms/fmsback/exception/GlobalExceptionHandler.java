package com.fms.fmsback.exception;

import com.fms.fmsback.common.result.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Capture Service Error (ServiceException.class)
     * @param serviceError
     * @return Result
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public Result handle(ServiceException serviceError) {
        return Result.error(serviceError.getCode(), serviceError.getMessage());
    };

}
