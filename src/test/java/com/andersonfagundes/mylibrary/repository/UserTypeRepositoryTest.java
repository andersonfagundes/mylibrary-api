package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.UserType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Test for User Type Respository")
class UserTypeRepositoryTest {

    @Autowired
    private UserTypeRepository userTypeRepository;

    @Test
    @DisplayName("Save persists user type when successfull")
    void save_PersistsUserType_WhenSuccessfull(){
        UserType userTypeToBeSaved = createUserType();
        UserType userTypeSaved = this.userTypeRepository.save(userTypeToBeSaved);
        Assertions.assertThat(userTypeSaved).isNotNull();
        Assertions.assertThat(userTypeSaved.getId()).isNotNull();
        Assertions.assertThat(userTypeSaved.getType()).isEqualTo(userTypeToBeSaved.getType());
    }

    private UserType createUserType() {
        return UserType.builder()
                .type("admin")
                .build();
    }
}