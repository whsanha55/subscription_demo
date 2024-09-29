package com.jongha.demo.global.base;

import com.jongha.demo.global.filter.MDCFilter;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Value;
import org.slf4j.MDC;

@Value
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BaseResponse<T> {

    String requestId = MDC.get(MDCFilter.REQUEST_ID);
    String errorMessage;
    T returnObject;

    public static <T> BaseResponse<T> ok() {
        return new BaseResponse<>(null, null);
    }

    public static <T> BaseResponse<T> ok(T returnObject) {
        return new BaseResponse<>(null, returnObject);
    }

    public static <T> BaseResponse<T> error(String errorMessage) {
        return new BaseResponse<>(errorMessage, null);
    }

}
