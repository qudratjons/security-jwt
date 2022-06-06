package com.jonso.security.webapp.advice;

import com.jonso.security.error.AppErrors;
import com.jonso.security.error.AppException;
import com.jonso.security.service.dto.CommonResult;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Optional;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class ErrorTranslator {

    private final ResourceBundleMessageSource messageSource;

    @ExceptionHandler(AppException.class)
    public ResponseEntity<CommonResult<?>> handleAppException(AppException e, HttpServletRequest request) {
        log.error("--> Handled AppException message: {}", e.getMessage());
        return ResponseEntity.ok(
                new CommonResult<>(
                        messageSource.getMessage(e.getAppErrors().name(), null, getLocale(request)),
                        e.getAppErrors().getCode()
                ));
    }

    @ExceptionHandler
    public <EXCEPTION extends Exception> ResponseEntity<CommonResult<?>> handleGlobalError(EXCEPTION e, HttpServletRequest request) {
        log.error("--> Handled UnExpectedException type: {}, message: {}", e.getClass(), e.getMessage());
        log.error("--> Error details",e);
        return ResponseEntity.ok(
                new CommonResult<>(
                        messageSource.getMessage(AppErrors.UNKNOWN_ERROR.name(), null, getLocale(request)),
                        AppErrors.UNKNOWN_ERROR.getCode()
                ));
    }

    private Locale getLocale(HttpServletRequest request) {
        return Locale.forLanguageTag(
                Optional.ofNullable(request.getHeader(HttpHeaders.ACCEPT_LANGUAGE))
                        .orElse("ru")
        );
    }
}
