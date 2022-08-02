package com.andersonfagundes.mylibrary.handler;

import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.exception.BadRequestExceptionDetails;
import com.andersonfagundes.mylibrary.exception.ValidationExceptionDetails;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

//RestExceptionHandler (handler = manipuladora) porque podemos ter diversos tipos de excecoes dentro dessa classe


@ControllerAdvice //quando utilizamos dessa forma, estamos dizendo para todas os controles que eles tem que utilizar o que está dentro dessa classe baseado na "flag(@ExceptionHandler)"
@Log4j2
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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException(
            MethodArgumentNotValidException exception) {

        //        log.info("Fields {}", exception.getBindingResult().getFieldError().getField());

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                ValidationExceptionDetails.builder()
                        .timestamp(LocalDateTime.now())
                        .status(HttpStatus.BAD_REQUEST.value())
                        .title("Bad Requst Exception. Invalid Fields")
//                        .details(exception.getMessage())
                        .details("Check the field(s) error")
                        .developerMessage(exception.getClass().getName())
                        .fields(fields)
                        .fieldsMessage(fieldsMessage)
                        .build(), HttpStatus.BAD_REQUEST);
    }
}
