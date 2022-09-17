package com.andersonfagundes.mylibrary.service;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.mapper.author.AuthorMapper;
import com.andersonfagundes.mylibrary.repository.AuthorRepository;
import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;
import com.andersonfagundes.mylibrary.requests.author.AuthorPutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Page<Author> listAll(Pageable pageable) {
        return authorRepository.findAll(pageable);
    }

    public List<Author> findByName(String name) {
        return authorRepository.findByName(name);
    }

    public Author findByIdOrThrowBadRequestException(long id) {
        return authorRepository.findById(id) //findById retorna um optional
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found"));
                .orElseThrow(() -> new BadRequestException("Author not found"));
    }

//    @Transactional(rollbackOn = Exception.class) // dessa forma o transactional leva em consideração tambem as exceções do tipo checked

    public Author save(AuthorPostRequestBody authorPostRequestBody) {
        return authorRepository.save(AuthorMapper.INSTANCE.toAuthor(authorPostRequestBody));
        //return authorRepository.save(Author.builder().name(authorPostRequestBody.getName()).build());
    }

    public void delete(long id) {
        authorRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AuthorPutRequestBody authorPutRequestBody) {
        Author savedAuthor = findByIdOrThrowBadRequestException(authorPutRequestBody.getId());
        Author author = AuthorMapper.INSTANCE.toAuthor(authorPutRequestBody);
        author.setId(savedAuthor.getId());
        authorRepository.save(author);
    }
}
