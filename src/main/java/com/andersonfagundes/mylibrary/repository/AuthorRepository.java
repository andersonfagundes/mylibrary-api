package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    List<Author> findByName(String name); //findByName o "Name" precisa ser exatamente o nome declarado no modelo
}
