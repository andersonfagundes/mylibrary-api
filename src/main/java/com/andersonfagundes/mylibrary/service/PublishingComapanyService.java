package com.andersonfagundes.mylibrary.service;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.repository.PublishingCompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublishingComapanyService {

    private final PublishingCompanyRepository publishingCompanyRepository;

    public Page<PublishingCompany> listAll(Pageable pageable) {
        return publishingCompanyRepository.findAll(pageable);
    }

    public PublishingCompany findByIdOrThrowBadRequestException(long id) {
        return publishingCompanyRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("Publishing company not found"));
    }

}
