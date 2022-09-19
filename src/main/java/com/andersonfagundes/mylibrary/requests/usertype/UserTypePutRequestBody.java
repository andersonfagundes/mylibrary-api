package com.andersonfagundes.mylibrary.requests.usertype;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserTypePutRequestBody {
    private Long id;
    @NotEmpty(message = "The type cannot be empty")
    private String type;
}
