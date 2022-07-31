package com.andersonfagundes.mylibrary.controller;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;
import com.andersonfagundes.mylibrary.requests.author.AuthorPutRequestBody;
import com.andersonfagundes.mylibrary.service.AuthorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("authors")
@Log4j2
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<Author>> listAll(){
        return new ResponseEntity<>(authorService.listAll(),HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<Author> findById(@PathVariable long id){
        return new ResponseEntity<>(authorService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<List<Author>> findByName(@RequestParam String name){
        return new ResponseEntity<>(authorService.findByName(name), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Author> save(@RequestBody @Valid AuthorPostRequestBody authorPostRequestBody) { //utilizar o jackson para fazer o mapeamento para a classe Author
        return new ResponseEntity<>(authorService.save(authorPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        authorService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid AuthorPutRequestBody authorPutRequestBody){
        authorService.replace(authorPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
