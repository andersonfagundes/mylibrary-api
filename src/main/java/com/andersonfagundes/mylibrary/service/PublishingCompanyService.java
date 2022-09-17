package com.andersonfagundes.mylibrary.service;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.mapper.author.PublishingCompanyMapper;
import com.andersonfagundes.mylibrary.repository.PublishingCompanyRepository;
import com.andersonfagundes.mylibrary.requests.publishingcompany.PublishingCompanyPostRequestBody;
import com.andersonfagundes.mylibrary.requests.publishingcompany.PublishingCompanyPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PublishingCompanyService {

    private final PublishingCompanyRepository publishingCompanyRepository;

    public Page<PublishingCompany> listAll(Pageable pageable) {
        return publishingCompanyRepository.findAll(pageable);
    }

    public PublishingCompany findByIdOrThrowBadRequestException(long id) {
        return publishingCompanyRepository.findById(id)
                .orElseThrow(()-> new BadRequestException("Publishing Company not found"));
    }

    public void delete(long id) {
        publishingCompanyRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    @Transactional
    public PublishingCompany save(PublishingCompanyPostRequestBody publishingCompanyPostRequestBody) {
        return publishingCompanyRepository.save(PublishingCompanyMapper.INSTANCE.toPublishingCompany(publishingCompanyPostRequestBody));
    }

    public void replace(PublishingCompanyPutRequestBody publishingCompanyPutRequestBody) {
        PublishingCompany savedPublishingCompany = findByIdOrThrowBadRequestException(publishingCompanyPutRequestBody.getId());
        PublishingCompany publishingCompany = PublishingCompanyMapper.INSTANCE.toPublishingCompany(publishingCompanyPutRequestBody);
        publishingCompany.setId(savedPublishingCompany.getId());
        publishingCompanyRepository.save(publishingCompany);
    }
}
