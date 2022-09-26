package com.andersonfagundes.mylibrary.util;

import com.andersonfagundes.mylibrary.domain.Author;

public class AuthorCreator {

    public static Author createAuthorToBeSaved(){
        return Author.builder()
                .name("Monteiro Lobato")
                .build();
    }

    public static Author createValidAuthor(){
        return Author.builder()
                .name("Monteiro Lobato")
                .id(1L)
                .build();
    }

    public static Author createValidUpdatedAuthor(){
        return Author.builder()
                .name("Monteiro Lobato 2")
                .id(1L)
                .build();
    }

}
