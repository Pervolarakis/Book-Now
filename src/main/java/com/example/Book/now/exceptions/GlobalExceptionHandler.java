package com.example.Book.now.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBadCredentialsException(BadCredentialsException exception){
        return "wrong pass dude";
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleUserAlreadyExistsException(UserAlreadyExistsException exception){
        return "User already exists.";
    }

    @ExceptionHandler(CannotSendEmailException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ResponseBody
    public String handleCannotSendEmailException(CannotSendEmailException exception){
        return "Mail service down";
    }

    @ExceptionHandler(EmailAlreadyVerifiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleEmailAlreadyVerifiedException(EmailAlreadyVerifiedException exception){
        return "Email Already verified";
    }

    @ExceptionHandler(EmailNotVerifiedException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleEmailNotVerifiedException(EmailNotVerifiedException exception){
        return "Email not verified, please check your inbox";
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleUserNotFoundException(UserAlreadyExistsException exception){
        return "Invalid user";
    }
}
