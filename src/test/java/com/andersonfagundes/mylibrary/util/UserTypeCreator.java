package com.andersonfagundes.mylibrary.util;

import com.andersonfagundes.mylibrary.domain.UserType;

public class UserTypeCreator {

    public static UserType createUserTypeToBeSaved() {
        return UserType.builder()
                .type("admin")
                .build();
    }

    public static UserType createValidUserType() {
        return UserType.builder()
                .type("admin")
                .id(1L)
                .build();
    }

    private UserType createValidUpdatedUserType() {
        return UserType.builder()
                .type("admin 2")
                .id(1L)
                .build();
    }
}
