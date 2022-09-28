package com.andersonfagundes.mylibrary.integration;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.repository.AuthorRepository;
import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;
import com.andersonfagundes.mylibrary.util.author.AuthorCreator;
import com.andersonfagundes.mylibrary.util.author.AuthorPostRequestBodyCreator;
import com.andersonfagundes.mylibrary.wrapper.PageableResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class AuthorControllerIT {
    @Autowired
    private TestRestTemplate testRestTemplate;
    @LocalServerPort
    private int port;
    @Autowired
    private AuthorRepository authorRepository;

    @Test
    @DisplayName("listAll list of author inside page object when successful")
    void list_ReturnsListOfAuthorsInsidePageObject_WhenSuccessfull(){
        Author savedAuthor = authorRepository.save(AuthorCreator.createAuthorToBeSaved());
        String expectedName = savedAuthor.getName();

        PageableResponse<Author> authorPage = testRestTemplate.exchange("/authors", HttpMethod.GET, null,
                new ParameterizedTypeReference<PageableResponse<Author>>() {
                }).getBody();
        Assertions.assertThat(authorPage).isNotNull();
        Assertions.assertThat(authorPage.toList())
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(authorPage.toList().get(0).getName()).isEqualTo(expectedName);
    }

    @Test
    @DisplayName("findById returns author when successful")
    void findById_ReturnsAuthor_WhenSuccessfull(){
        Author savedAuthor = authorRepository.save(AuthorCreator.createAuthorToBeSaved());
        Long expectedId = savedAuthor.getId();
        Author author = testRestTemplate.getForObject("/authors/{id}",Author.class, expectedId);
        Assertions.assertThat(author)
                .isNotNull();
        Assertions.assertThat(author.getId()).isNotNull().isEqualTo(expectedId);
    }

    @Test
    @DisplayName("findByName returns a list of author when successful")
    void findByName_ReturnsListOfAuthor_WhenSuccessfull(){

        List<Author> authors = testRestTemplate.exchange("/authors/findByName?name=nometeste", HttpMethod.GET, null,
                new ParameterizedTypeReference<List<Author>>() {
                }).getBody();

        Assertions.assertThat(authors)
                .isNotNull()
                .isEmpty();

    }

    @Test
    @DisplayName("save author when successful")
    void save_ReturnsAuthor_WhenSuccessfull(){
        AuthorPostRequestBody authorPostRequestBody = AuthorPostRequestBodyCreator.createAuthorPostRequestBody();

        ResponseEntity<Author> authorResponseEntity = testRestTemplate.postForEntity("/authors", authorPostRequestBody, Author.class);

        Assertions.assertThat(authorResponseEntity).isNotNull();
        Assertions.assertThat(authorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(authorResponseEntity.getBody()).isNotNull();
        Assertions.assertThat(authorResponseEntity.getBody().getId()).isNotNull();
    }

    @Test
    @DisplayName("replace updated author when successful")
    void replace_UpdatesAuthor_WhenSuccessfull(){
        Author savedAuthor = authorRepository.save(AuthorCreator.createAuthorToBeSaved());
        savedAuthor.setName("new name");

        ResponseEntity<Void> authorResponseEntity = testRestTemplate.exchange("/authors",
                HttpMethod.PUT, new HttpEntity<>(savedAuthor), Void.class);

        Assertions.assertThat(authorResponseEntity).isNotNull();
        Assertions.assertThat(authorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    @DisplayName("delete removes author when successful")
    void delete_RemovesAuthor_WhenSuccessfull(){

        Author savedAuthor = authorRepository.save(AuthorCreator.createAuthorToBeSaved());

        ResponseEntity<Void> authorResponseEntity = testRestTemplate.exchange("/authors/{id}",
                HttpMethod.DELETE, null, Void.class, savedAuthor.getId());

        Assertions.assertThat(authorResponseEntity).isNotNull();
        Assertions.assertThat(authorResponseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
