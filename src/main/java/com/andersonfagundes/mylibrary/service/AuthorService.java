package com.andersonfagundes.mylibrary.service;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.repository.AuthorRepository;
import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;
import com.andersonfagundes.mylibrary.requests.author.AuthorPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public List<Author> listAll() {
        return authorRepository.findAll();
    }

    public Author findByIdOrThrowBadRequestException(long id) {
        return authorRepository.findById(id) //findById retorna um optional
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found"));
    }

    public Author save(AuthorPostRequestBody authorPostRequestBody) {
        Author author = Author.builder().name(authorPostRequestBody.getName()).build();
        return authorRepository.save(author); //salva essa referencia (objeto) e retorna ela atualizada com id
    }

    public void delete(long id) {
        authorRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AuthorPutRequestBody authorPutRequestBody) {
        Author savedAuthor = findByIdOrThrowBadRequestException(authorPutRequestBody.getId());
        Author author = Author.builder()
                .id(savedAuthor.getId()) //so para ter certeza que o id que esta vindo e o que esta no bd, e todo o resto do objeto sera atualizado
                .name(authorPutRequestBody.getName())
                .build();

        authorRepository.save(author);
    }
}
