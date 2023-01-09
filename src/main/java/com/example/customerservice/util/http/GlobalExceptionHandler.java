package com.example.customerservice.util.http;

import com.example.customerservice.util.exception.ApplicationException;
import com.example.customerservice.util.exception.BadRequestException;
import com.example.customerservice.util.exception.InternalServerException;
import com.example.customerservice.util.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public @ResponseBody HttpErrorInfo handleNotFoundExceptions(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.NOT_FOUND, request, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    public @ResponseBody HttpErrorInfo handleBadRequestExceptions(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, request, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public @ResponseBody HttpErrorInfo handleJsonParseErrors(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, request, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public @ResponseBody HttpErrorInfo handleValidationExceptions(HttpServletRequest request, MethodArgumentNotValidException ex){
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, request, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public @ResponseBody HttpErrorInfo validationErrors(HttpServletRequest request, Exception ex){
        log.info("error -> {}", ex.getMessage());
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, request, ex);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public @ResponseBody HttpErrorInfo sqlErrors(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.BAD_REQUEST, request, ex);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ApplicationException.class)
    public @ResponseBody HttpErrorInfo handleApplicationException(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request, ex);
    }



    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public @ResponseBody HttpErrorInfo handleBadInputException(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request, ex);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    public @ResponseBody HttpErrorInfo handleInternalServerExceptions(HttpServletRequest request, Exception ex){
        return createHttpErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, request, ex);
    }

    private HttpErrorInfo createHttpErrorInfo(HttpStatus httpStatus, HttpServletRequest request, Exception ex) {
        final String path = request.getServletPath();
        final String message = ex.getMessage();

        log.debug("Returning HTTP status: {} for path: {}, message: {}", httpStatus, path, message);
        return new HttpErrorInfo(httpStatus, path, message);
    }
}
