package fr.nakaoni.inm.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import fr.nakaoni.inm.exception.BankNotFoundException;

@RestControllerAdvice
public class BankNotFoundAdvice {

    @ExceptionHandler(BankNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String bankNotFoundHandler(BankNotFoundException e)
    {
        return e.getMessage();
    }
}
