package com.andersonfagundes.mylibrary.controller;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import com.andersonfagundes.mylibrary.requests.publishingcompany.PublishingCompanyPostRequestBody;
import com.andersonfagundes.mylibrary.requests.publishingcompany.PublishingCompanyPutRequestBody;
import com.andersonfagundes.mylibrary.service.PublishingCompanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("publishing-companies")
@Log4j2
@RequiredArgsConstructor
public class PublishingCompanyController {
    private final PublishingCompanyService publishingCompanyService;

    @GetMapping
    public ResponseEntity<Page<PublishingCompany>> listAll(Pageable pageable){
        return new ResponseEntity<>(publishingCompanyService.listAll(pageable), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<PublishingCompany> findById(@PathVariable long id){
        return new ResponseEntity<>(publishingCompanyService.findByIdOrThrowBadRequestException(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PublishingCompany> save(@RequestBody @Valid PublishingCompanyPostRequestBody publishingCompanyPostRequestBody){
        return new ResponseEntity<>(publishingCompanyService.save(publishingCompanyPostRequestBody), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody @Valid PublishingCompanyPutRequestBody publishingCompanyPutRequestBody){
        publishingCompanyService.replace(publishingCompanyPutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id){
        publishingCompanyService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
