package com.andersonfagundes.mylibrary.requests.publishingcompany;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PublishingCompanyPostRequestBody {
    @NotEmpty(message = "The PublishingCompany name cannot be empty")
    private String name;
}
