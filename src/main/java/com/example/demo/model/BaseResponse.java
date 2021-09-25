package com.example.demo.model;

import com.example.demo.constant.DemoConstant;
import com.example.demo.exception.ErrorResponse;
import lombok.Getter;
import lombok.Setter;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BaseResponse<T> {
    private int code;
    private T data;
    private String traceId;

    private BaseResponse(int code, T data) {
        this.code = code;
        this.data = data;
        this.traceId = ThreadContext.get(DemoConstant.Tracing.TRACE_ID);
    }

    public static <T> BaseResponse<T> ofSuccess(T data) {
        return new BaseResponse<>(200, data);
    }

    public static <T> BaseResponse<T> ofSuccess(HttpStatus status, T data) {
        return new BaseResponse<>(status.value(), data);
    }

    public static BaseResponse<ErrorResponse> ofFailed(ErrorResponse errorResponse) {
        return new BaseResponse<>(errorResponse.getStatus().value(), errorResponse);
    }
}
