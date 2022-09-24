package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@DataJpaTest
@DisplayName("Test for Publishing Company Repository")
class PublishingCompanyRepositoryTest {

    @Autowired
    PublishingCompanyRepository publishingCompanyRepository;

    @Test
    @DisplayName("Save persists publishingcompany when successfull")
    void save_PersistsPublishingCompany_WhenSuccessfull(){
        PublishingCompany publishingCompanyToBeSaved = createPublishingCompany();
        PublishingCompany publishingCompanySaved = this.publishingCompanyRepository.save(publishingCompanyToBeSaved);
        Assertions.assertThat(publishingCompanySaved).isNotNull();
        Assertions.assertThat(publishingCompanySaved.getId()).isNotNull();
        Assertions.assertThat(publishingCompanyToBeSaved.getName()).isEqualTo(publishingCompanySaved.getName());
    }

    private PublishingCompany createPublishingCompany() {
        return PublishingCompany.builder()
                .name("Editora Vida")
                .build();
    }

}