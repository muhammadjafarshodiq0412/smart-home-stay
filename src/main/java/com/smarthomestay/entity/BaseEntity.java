package com.smarthomestay.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date createdDate;
    private String createdBy;
    private Date updatedDate;
    private String updatedBy;

    @PrePersist
    private void beforeSave(){
        this.createdDate = new Date();
        this.createdBy = "Admin";

    }
    @PreUpdate
    private void beforeUpdate(){
        this.updatedDate = new Date();
    }


}