package com.andersonfagundes.mylibrary.controller;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;
import com.andersonfagundes.mylibrary.requests.author.AuthorPutRequestBody;
import com.andersonfagundes.mylibrary.service.AuthorService;
import com.andersonfagundes.mylibrary.util.author.AuthorCreator;
import com.andersonfagundes.mylibrary.util.author.AuthorPostRequestBodyCreator;
import com.andersonfagundes.mylibrary.util.author.AuthorPutRequestBodyCreator;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;

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

        BDDMockito.when(authorServiceMock.findByIdOrThrowBadRequestException(ArgumentMatchers.anyLong()))
                .thenReturn(AuthorCreator.createValidAuthor());

        BDDMockito.when(authorServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AuthorCreator.createValidAuthor()));

        BDDMockito.when(authorServiceMock.save(ArgumentMatchers.any(AuthorPostRequestBody.class)))
                .thenReturn(AuthorCreator.createValidAuthor());

        BDDMockito.doNothing().when(authorServiceMock).replace(ArgumentMatchers.any(AuthorPutRequestBody.class));

        BDDMockito.doNothing().when(authorServiceMock).delete(ArgumentMatchers.anyLong());
    }

    @Test
    @DisplayName("listAll list of author inside page object when successful")
    void list_ReturnsListOfAuthorsInsidePageObject_WhenSuccessfull(){
        String expectedName = AuthorCreator.createValidAuthor().getName();
        Page<Author> authorPage = authorController.listAll(null).getBody();
        Assertions.assertThat(authorPage).isNotNull();
        Assertions.assertThat(authorPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(authorPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns author when successful")
    void findById_ReturnsAuthor_WhenSuccessfull(){
        Long expectedId = AuthorCreator.createValidAuthor().getId();
        Author author = authorController.findById(1).getBody();
        Assertions.assertThat(author)
                .isNotNull();
        Assertions.assertThat(author.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of author when successful")
    void findByName_ReturnsListOfAuthor_WhenSuccessfull(){
        String expectedName = AuthorCreator.createValidAuthor().getName();
        List<Author> authors = authorController.findByName("author").getBody();
        Assertions.assertThat(authors)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(authors.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of author when author is not found")
    void findByName_ReturnsEmptyListOfAuthor_WhenAuthorIsNotFound(){
        BDDMockito.when(authorServiceMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Author> authors = authorController.findByName("author").getBody();
        Assertions.assertThat(authors)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save author when successful")
    void save_ReturnsAuthor_WhenSuccessfull(){
//        Long expectedId = AuthorCreator.createValidAuthor().getId();
        Author author = authorController.save(AuthorPostRequestBodyCreator.createAuthorPostRequestBody()).getBody();
        Assertions.assertThat(author)
                .isNotNull().isEqualTo(AuthorCreator.createValidAuthor());
//        Assertions.assertThat(author.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("replace updated author when successful")
    void replace_UpdatesAuthor_WhenSuccessfull(){

        Assertions.assertThatCode(() -> authorController.replace(AuthorPutRequestBodyCreator.createAuthorPutRequestBody()))
                        .doesNotThrowAnyException();

        ResponseEntity<Void> entity = authorController.replace(AuthorPutRequestBodyCreator.createAuthorPutRequestBody());

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes author when successful")
    void delete_RemovesAuthor_WhenSuccessfull(){

        Assertions.assertThatCode(() -> authorController.delete(1))
                .doesNotThrowAnyException();

        ResponseEntity<Void> entity = authorController.delete(1);

        Assertions.assertThat(entity).isNotNull();

        Assertions.assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

}