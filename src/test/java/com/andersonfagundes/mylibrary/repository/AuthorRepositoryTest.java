package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.Author;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@DisplayName("Tests for author repository")
class AuthorRepositoryTest {
    @Autowired
    private AuthorRepository authorRepository;
    @Test
    @DisplayName("save_PersirtAuthor_WhenSuccessfull")
    void save_PersirtAuthor_WhenSuccessfull(){ //nome do metodo_o que esse metodo precisa fazer_quando isso deve acontecer
        Author authorToBeSaved = createAuthor();
        Author savedAuthor = this.authorRepository.save(authorToBeSaved);
        Assertions.assertThat(savedAuthor).isNotNull();
        Assertions.assertThat(savedAuthor.getId()).isNotNull();
        Assertions.assertThat(savedAuthor.getName()).isEqualTo(authorToBeSaved.getName());
    }

    private Author createAuthor(){
        return Author.builder()
                .name("Monteiro Lobato")
                .build();
    }
}