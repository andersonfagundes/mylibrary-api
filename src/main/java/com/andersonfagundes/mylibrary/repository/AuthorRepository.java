package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
}
