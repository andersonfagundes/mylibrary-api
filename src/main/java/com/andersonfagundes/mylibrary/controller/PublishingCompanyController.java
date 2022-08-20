package com.andersonfagundes.mylibrary.controller;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import com.andersonfagundes.mylibrary.service.PublishingComapanyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("publishing-companies")
@Log4j2
@RequiredArgsConstructor
public class PublishingCompanyController {

    private final PublishingComapanyService publishingComapanyService;

    @GetMapping
    public ResponseEntity<Page<PublishingCompany>> listAll(Pageable pageable){
        return new ResponseEntity<>(publishingComapanyService.listAll(pageable), HttpStatus.OK);
    }

}
