package com.andersonfagundes.mylibrary.util.author;

import com.andersonfagundes.mylibrary.requests.author.AuthorPutRequestBody;

public class AuthorPutRequestBodyCreator {
    public static AuthorPutRequestBody createAuthorPutRequestBody(){
        return AuthorPutRequestBody.builder()
                .id(AuthorCreator.createValidAuthor().getId())
                .name(AuthorCreator.createValidAuthor().getName())
                .build();
    }
}
