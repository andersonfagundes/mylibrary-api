package com.andersonfagundes.mylibrary.requests.author;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class AuthorPutRequestBody {
    private Long id;
    @NotEmpty(message = "The author name cannot be empty")
    private String name;
}
