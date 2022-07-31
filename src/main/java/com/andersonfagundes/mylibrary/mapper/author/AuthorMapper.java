package com.andersonfagundes.mylibrary.mapper.author;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.requests.author.AuthorPostRequestBody;
import com.andersonfagundes.mylibrary.requests.author.AuthorPutRequestBody;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public abstract class AuthorMapper {
    public static final AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    public abstract Author toAuthor(AuthorPostRequestBody authorPostRequestBody);

    public abstract Author toAuthor(AuthorPutRequestBody authorPutRequestBody);
}
