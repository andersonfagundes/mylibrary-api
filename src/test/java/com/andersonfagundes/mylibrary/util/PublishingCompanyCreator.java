package com.andersonfagundes.mylibrary.util;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;

public class PublishingCompanyCreator {

    public static PublishingCompany createPublishingCompanyToBeSaved() {
        return PublishingCompany.builder()
                .name("Editora Vida")
                .build();
    }

    public static PublishingCompany createValidPublishingCompany() {
        return PublishingCompany.builder()
                .name("Editora Vida")
                .id(1L)
                .build();
    }

    public static PublishingCompany createValidUpdatedPublishingCompany() {
        return PublishingCompany.builder()
                .name("Editora Vida 2")
                .id(1L)
                .build();
    }

}
