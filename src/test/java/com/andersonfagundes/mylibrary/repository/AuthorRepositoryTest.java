package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.Author;
import lombok.extern.log4j.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@DisplayName("Tests for Author Repository")
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Test
    @DisplayName("Save persists author when successfull")
    void save_PersirtAuthor_WhenSuccessfull(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        Assertions.assertThat(authorSaved).isNotNull(); //verifica se tem um objeto
        Assertions.assertThat(authorSaved.getId()).isNotNull(); //verifica se foi criado um id
        Assertions.assertThat(authorSaved.getName()).isEqualTo(authorToBeSaved.getName()); //verifica se o valor do obj criado é igual ao valor passada
    }

    @Test
    @DisplayName("Save Updates author when successfull")
    void save_UpdatesAuthor_WhenSuccessfull(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        authorSaved.setName("Lobato Monteiro");
        Author authorUpdated = this.authorRepository.save(authorSaved);
        Assertions.assertThat(authorUpdated).isNotNull();
        Assertions.assertThat(authorUpdated.getId()).isNotNull();
        Assertions.assertThat(authorUpdated.getName()).isEqualTo(authorSaved.getName());
    }

    @Test
    @DisplayName("Delete remove author when successfull")
    void delete_RemovesAuthor_WhenSuccessfull(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        this.authorRepository.delete(authorSaved);
        Optional<Author> authorOptional = this.authorRepository.findById(authorSaved.getId());
        Assertions.assertThat(authorOptional).isEmpty();
    }

    @Test
    @DisplayName("Find by name returns list of author when successful")
    void findByNae_ReturnsListofAuthor_WhenSuccessfull(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
        Author authorToBeSaved = createAuthor();
        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        String name = authorSaved.getName();
        List<Author> authors = this.authorRepository.findByName(name);
        Assertions.assertThat(authors).isNotEmpty()
                .contains(authorSaved);
//        Assertions.assertThat(authors).isNotEmpty();
//        Assertions.assertThat(authors).contains(authorSaved);
    }

    @Test
    @DisplayName("Find by name returns empty list when no author is found")
    void findByNae_ReturnsEmptyList_WhenAuthorIsNotFouned(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
//        Author authorToBeSaved = createAuthor();
//        Author authorSaved = this.authorRepository.save(authorToBeSaved);
        List<Author> authors = this.authorRepository.findByName("name not found");
        Assertions.assertThat(authors).isEmpty();
    }

    @Test
    @DisplayName("Save throw ConstraintViolationException when name is empty")
    void save_ThrowConstraintViolationException_WhenNameIsEmpty(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
        Author author = new Author();

//        Forma 1 de fazer
//        Assertions.assertThatThrownBy(() -> this.authorRepository.save(author))
//                .isInstanceOf(ConstraintViolationException.class);

//        Forma 2 de fazer
        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                .isThrownBy(() -> this.authorRepository.save(author))
                .withMessageContaining("The name cannot be empty");

    }

    private Author createAuthor(){
        return Author.builder()
                .name("Monteiro Lobato")
                .build();
    }
}