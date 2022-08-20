package com.andersonfagundes.mylibrary.repository;

import com.andersonfagundes.mylibrary.domain.PublishingCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublishingCompanyRepository extends JpaRepository<PublishingCompany, Long> {
}
