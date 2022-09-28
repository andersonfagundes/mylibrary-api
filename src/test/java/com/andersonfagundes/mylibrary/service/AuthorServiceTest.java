package com.andersonfagundes.mylibrary.service;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.repository.AuthorRepository;
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
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class AuthorServiceTest {

    @InjectMocks
    private AuthorService authorService;
    @Mock
    private AuthorRepository authorRepositoryMock;

    @BeforeEach
    void setUp(){
        PageImpl<Author> authorPage = new PageImpl<>(List.of(AuthorCreator.createValidAuthor()));
        BDDMockito.when(authorRepositoryMock.findAll(ArgumentMatchers.any(PageRequest.class)))
                .thenReturn(authorPage);

        BDDMockito.when(authorRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(AuthorCreator.createValidAuthor()));

        BDDMockito.when(authorRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(List.of(AuthorCreator.createValidAuthor()));

        BDDMockito.when(authorRepositoryMock.save(ArgumentMatchers.any(Author.class)))
                .thenReturn(AuthorCreator.createValidAuthor());

        BDDMockito.doNothing().when(authorRepositoryMock).delete(ArgumentMatchers.any(Author.class));
    }

    @Test
    @DisplayName("listAll list of author inside page object when successful")
    void listAll_ReturnsListOfAuthorsInsidePageObject_WhenSuccessfull(){
        String expectedName = AuthorCreator.createValidAuthor().getName();
        Page<Author> authorPage = authorService.listAll(PageRequest.of(1,1));
        Assertions.assertThat(authorPage).isNotNull();
        Assertions.assertThat(authorPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(authorPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException returns author when successful")
    void findById_ReturnsAuthor_WhenSuccessfull(){
        Long expectedId = AuthorCreator.createValidAuthor().getId();
        Author author = authorService.findByIdOrThrowBadRequestException(1);
        Assertions.assertThat(author)
                .isNotNull();
        Assertions.assertThat(author.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByIdOrThrowBadRequestException throws BadRequestException when author is not found")
    void findByIdOrThrowBadRequestException_ThrowsBadRequestException_WhenAuthorIsNotFound(){
        BDDMockito.when(authorRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(BadRequestException.class)
                .isThrownBy(() -> authorService.findByIdOrThrowBadRequestException(1));
    }

    @Test
    @DisplayName("findByName returns a list of author when successful")
    void findByName_ReturnsListOfAuthor_WhenSuccessfull(){
        String expectedName = AuthorCreator.createValidAuthor().getName();
        List<Author> authors = authorService.findByName("author");
        Assertions.assertThat(authors)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);

        Assertions.assertThat(authors.get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findByName returns an empty list of author when author is not found")
    void findByName_ReturnsEmptyListOfAuthor_WhenAuthorIsNotFound(){
        BDDMockito.when(authorRepositoryMock.findByName(ArgumentMatchers.anyString()))
                .thenReturn(Collections.emptyList());

        List<Author> authors = authorService.findByName("author");
        Assertions.assertThat(authors)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save author when successful")
    void save_ReturnsAuthor_WhenSuccessfull(){
//        Long expectedId = AuthorCreator.createValidAuthor().getId();
        Author author = authorService.save(AuthorPostRequestBodyCreator.createAuthorPostRequestBody());
        Assertions.assertThat(author)
                .isNotNull().isEqualTo(AuthorCreator.createValidAuthor());
//        Assertions.assertThat(author.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("replace updated author when successful")
    void replace_UpdatesAuthor_WhenSuccessfull(){

        Assertions.assertThatCode(() -> authorService.replace(AuthorPutRequestBodyCreator.createAuthorPutRequestBody()))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("delete removes author when successful")
    void delete_RemovesAuthor_WhenSuccessfull(){

        Assertions.assertThatCode(() -> authorService.delete(1))
                .doesNotThrowAnyException();

    }

}