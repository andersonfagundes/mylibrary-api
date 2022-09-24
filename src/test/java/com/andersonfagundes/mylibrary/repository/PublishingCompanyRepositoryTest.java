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

    @Test
    @DisplayName("Saves update publishing company")
    void save_UpdatePublishingCompany_WhenSuccesfull(){
        PublishingCompany publishingCompanyToBeSaved = createPublishingCompany();
        PublishingCompany publishingCompanySaved = this.publishingCompanyRepository.save(publishingCompanyToBeSaved);
        publishingCompanySaved.setName("Orvalho.com");
        PublishingCompany publishingCompanyUpdated = this.publishingCompanyRepository.save(publishingCompanySaved);
        Assertions.assertThat(publishingCompanySaved).isNotNull();
        Assertions.assertThat(publishingCompanySaved.getId()).isNotNull();
        Assertions.assertThat(publishingCompanySaved.getName()).isEqualTo(publishingCompanyUpdated.getName());
    }

    private PublishingCompany createPublishingCompany() {
        return PublishingCompany.builder()
                .name("Editora Vida")
                .build();
    }

}