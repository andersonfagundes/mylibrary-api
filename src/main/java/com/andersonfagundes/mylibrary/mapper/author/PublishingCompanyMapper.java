package com.andersonfagundes.mylibrary.mapper.author;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import com.andersonfagundes.mylibrary.requests.publishingcompany.PublishingCompanyPostRequestBody;
import com.andersonfagundes.mylibrary.requests.publishingcompany.PublishingCompanyPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class PublishingCompanyMapper {

    public static final PublishingCompanyMapper INSTANCE = Mappers.getMapper(PublishingCompanyMapper.class);

    public abstract PublishingCompany toPublishingCompany(PublishingCompanyPostRequestBody publishingCompanyPostRequestBody);

    public abstract PublishingCompany toPublishingCompany(PublishingCompanyPutRequestBody publishingCompanyPutRequestBody);

}
