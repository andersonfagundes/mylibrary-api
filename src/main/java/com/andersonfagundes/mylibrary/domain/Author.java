package com.andersonfagundes.mylibrary.domain;

import lombok.*;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "The name cannot be empty")
    private String name;

}
