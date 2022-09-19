package com.andersonfagundes.mylibrary.service;

import com.andersonfagundes.mylibrary.domain.Author;
import com.andersonfagundes.mylibrary.domain.UserType;
import com.andersonfagundes.mylibrary.exception.BadRequestException;
import com.andersonfagundes.mylibrary.mapper.usertype.UserTypeMapper;
import com.andersonfagundes.mylibrary.repository.UserTypeRepository;
import com.andersonfagundes.mylibrary.requests.usertype.UserTypePostRequestBody;
import com.andersonfagundes.mylibrary.requests.usertype.UserTypePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public Page<UserType> listAll(Pageable pageable) {
        return userTypeRepository.findAll(pageable);
    }

    public UserType findByIdOrThrowBadRequestException(long id) {
        return userTypeRepository.findById(id)
                .orElseThrow(() -> new BadRequestException("UserType not found"));
    }

    public UserType save(UserTypePostRequestBody userTypePostRequestBody) {
        return userTypeRepository.save(UserTypeMapper.INSTANCE.toUserType(userTypePostRequestBody));
    }

    public void replace(UserTypePutRequestBody userTypePutRequestBody) {
        UserType userTypeSaved = findByIdOrThrowBadRequestException(userTypePutRequestBody.getId());
        UserType userType = UserTypeMapper.INSTANCE.toUserType(userTypePutRequestBody);
        userType.setId(userTypeSaved.getId());
        userTypeRepository.save(userType);
    }

    public void delete(long id) {
        userTypeRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
