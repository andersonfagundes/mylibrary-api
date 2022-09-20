package com.andersonfagundes.mylibrary.controller;

import com.andersonfagundes.mylibrary.domain.UserType;
import com.andersonfagundes.mylibrary.requests.usertype.UserTypePostRequestBody;
import com.andersonfagundes.mylibrary.requests.usertype.UserTypePutRequestBody;
import com.andersonfagundes.mylibrary.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("user-types")
@Log4j2
@RequiredArgsConstructor
public class UserTypeController {

    private final UserTypeService userTypeService;

    @GetMapping
    public ResponseEntity<Page<UserType>> listAll(Pageable pageable){
        return new ResponseEntity<>(userTypeService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserType> findByIdOrThrowBadRequestException(@PathVariable long id){
        return new ResponseEntity<>(userTypeService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserType> save(@RequestBody @Valid UserTypePostRequestBody userTypeRequestBody){
        return new ResponseEntity<>(userTypeService.save(userTypeRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid UserTypePutRequestBody userTypePutRequestBody){
        userTypeService.replace(userTypePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        userTypeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
