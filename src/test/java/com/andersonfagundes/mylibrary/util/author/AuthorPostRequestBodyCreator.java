package com.andersonfagundes.mylibrary.util.author;

import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;

public class AuthorPostRequestBodyCreator {
    public static AuthorPostRequestBody createAuthorPostRequestBody(){
        return AuthorPostRequestBody.builder()
                .name(AuthorCreator.createAuthorToBeSaved().getName())
                .build();
    }
}
