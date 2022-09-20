package com.andersonfagundes.mylibrary.mapper.usertype;

import com.andersonfagundes.mylibrary.domain.UserType;
import com.andersonfagundes.mylibrary.requests.usertype.UserTypePostRequestBody;
import com.andersonfagundes.mylibrary.requests.usertype.UserTypePutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class UserTypeMapper {
    public static final UserTypeMapper INSTANCE = Mappers.getMapper(UserTypeMapper.class);

    public abstract UserType toUserType(UserTypePostRequestBody userTypePostRequestBody);

    public abstract UserType toUserType(UserTypePutRequestBody userTypePutRequestBody);
}
