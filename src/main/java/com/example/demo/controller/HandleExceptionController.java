package com.example.demo.controller;

import com.example.demo.exception.BusinessCode;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.ErrorResponse;
import com.example.demo.model.BaseResponse;
import com.example.demo.utils.StringUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Log4j2
@ControllerAdvice
public final class HandleExceptionController {


    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBusinessException(BusinessException businessException) {
        HttpStatus httpStatus = businessException.getErrorResponse().getStatus();
        log.error("Exception Detail: {} and rootCause: {}",
                businessException.getErrorResponse().getMessage(),
                StringUtil.stackTraceToString(businessException.getCause())
        );
        return new ResponseEntity<>(BaseResponse.ofFailed(businessException.getErrorResponse()), httpStatus);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBusinessException(Exception exception) {
        BusinessException businessException = new BusinessException(BusinessCode.INTERNAL_SERVER);
        if (!StringUtils.isBlank(exception.getMessage())) {
            log.error("Exception Detail: {} and rootCause: {}",
                    exception.getMessage(),
                    StringUtil.stackTraceToString(exception.getCause()));
        }
        HttpStatus httpStatus = businessException.getErrorResponse().getStatus();

        return new ResponseEntity<>(BaseResponse.ofFailed(businessException.getErrorResponse()), httpStatus);
    }


    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleBusinessException(Throwable exception) {
        BusinessException businessException = new BusinessException(BusinessCode.INTERNAL_SERVER);
        HttpStatus httpStatus = businessException.getErrorResponse().getStatus();
        log.error("Exception global with message: {} status: {} exception: {}", exception.getMessage(), httpStatus, StringUtil.stackTraceToString(exception));
        return new ResponseEntity<>(BaseResponse.ofFailed(businessException.getErrorResponse()), httpStatus);
    }
}
