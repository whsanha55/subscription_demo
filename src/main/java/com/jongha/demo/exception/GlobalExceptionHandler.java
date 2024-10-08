package com.jongha.demo.exception;

import com.jongha.demo.global.base.BaseResponse;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RequiredArgsConstructor
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({TypeMismatchException.class})
    protected ResponseEntity<BaseResponse<String>> handleTypeMismatchException(TypeMismatchException exception) {
        return ResponseEntity.badRequest()
            .body(BaseResponse.error(exception.getLocalizedMessage()));
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<BaseResponse<String>> handleValidationException(BindException exception) {
        var message = Optional.ofNullable(exception.getFieldError())
            .map(fieldError -> fieldError.getField() + " : " + fieldError.getDefaultMessage())
            .orElse("");
        return ResponseEntity.badRequest()
            .body(BaseResponse.error(message));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    protected ResponseEntity<Void> handleNoHandlerFoundException(NoHandlerFoundException exception) {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(BaseException.class)
    protected ResponseEntity<BaseResponse<String>> handleBaseException(BaseException exception) {
        log.info("BaseException", exception);

        return ResponseEntity.status(exception.getHttpStatus())
            .body(BaseResponse.error(exception.getLocalizedMessage()));
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<BaseResponse<String>> handlerException(Exception exception) {
        log.error("Exception", exception);

        var message = exception.getClass().getSimpleName() + ":::" + exception.getLocalizedMessage();
        return ResponseEntity.internalServerError()
            .body(BaseResponse.error(message));
    }

}
