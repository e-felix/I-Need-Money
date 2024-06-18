package fr.nakaoni.inm.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.nakaoni.inm.exception.AccountNotFoundException;

@RestControllerAdvice
public class AccountNotFoundAdvice {

    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bankNotFoundHandler(AccountNotFoundException e)
    {
        return e.getMessage();
    }
}
