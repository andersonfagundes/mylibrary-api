package com.andersonfagundes.mylibrary.requests.publishingcompany;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PublishingCompanyPutRequestBody {
    private Long id;

    @NotEmpty(message = "The PublishingCompany name not be empty")
    private String name;
}
