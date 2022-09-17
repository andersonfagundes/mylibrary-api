package com.andersonfagundes.mylibrary.requests.author;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthorPostRequestBody {
    @NotEmpty(message = "The author name cannot be empty")
    private String name;
}
