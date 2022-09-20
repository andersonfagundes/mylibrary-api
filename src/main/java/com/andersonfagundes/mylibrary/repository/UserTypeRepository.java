package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserType, Long> {
}
