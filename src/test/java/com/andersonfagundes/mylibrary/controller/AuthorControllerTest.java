package com.andersonfagundes.mylibrary.controller;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.service.AuthorService;
import com.andersonfagundes.mylibrary.util.AuthorCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class AuthorControllerTest {

    @InjectMocks
    private AuthorController authorController;
    @Mock
    private AuthorService authorServiceMock;

    @BeforeEach
    void setUp(){
        PageImpl<Author> authorPage = new PageImpl<>(List.of(AuthorCreator.createValidAuthor()));
        BDDMockito.when(authorServiceMock.listAll(ArgumentMatchers.any()))
                .thenReturn(authorPage);
    }

    @Test
    @DisplayName("List returns list of author inside page object when successful")
    void list_ReturnsListOfAuthorsInsidePageObject_WhenSuccessfull(){
        String expectedName = AuthorCreator.createValidAuthor().getName();
        Page<Author> authorPage = authorController.listAll(null).getBody();
        Assertions.assertThat(authorPage).isNotNull();
        Assertions.assertThat(authorPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(authorPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

}