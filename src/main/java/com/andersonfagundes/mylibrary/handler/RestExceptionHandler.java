package com.andersonfagundes.mylibrary.handler;

import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.exception.BadRequestExceptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

//RestExceptionHandler (handler = manipuladora) porque podemos ter diversos tipos de excecoes dentro dessa classe


@ControllerAdvice //quando utilizamos dessa forma, estamos dizendo para todas os controles que eles tem que utilizar o que está dentro dessa classe baseado na "flag(@ExceptionHandler)"
public class RestExceptionHandler {
    @ExceptionHandler(BadRequestException.class) //Essa "flag" diz que se o controller lancar uma exceção do tipo "BadRequestHandler", o controller precisara usar o metodo abaixo e retornar esse valor
    public ResponseEntity<BadRequestExceptionDetails> handlerBadRequestException(BadRequestException bre) {
        return new ResponseEntity<>(
                BadRequestExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Requst Exception. Check the documentation")
                        .details(bre.getMessage())
                        .developerMessage(bre.getClass().getName())
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
