package com.andersonfagundes.mylibrary.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity(name = "publishing_company")
public class PublishingCompany {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true, length=100)
    @NotEmpty(message = "The PublishingCompany cannot be empty")
    private String name;

    public PublishingCompany(){
    }

    public PublishingCompany(long id, String name){
        this.id = id;
        this.name = name;
    }

    public long getId(){
        return this.id;
    }

    public void setId(long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }
}
