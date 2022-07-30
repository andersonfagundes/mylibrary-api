package com.andersonfagundes.mylibrary.requests.author;

import lombok.Data;

@Data
public class AuthorPutRequestBody {
    private Long id;
    private String name;
}
