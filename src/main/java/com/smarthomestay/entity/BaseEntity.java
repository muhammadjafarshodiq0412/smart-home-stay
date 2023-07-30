package com.smarthomestay.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.smarthomestay.constant.GeneralConstant;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = GeneralConstant.YYYY_MM_DD)
    private Date createdDate;
    private String createdBy;
    @JsonFormat(pattern = GeneralConstant.YYYY_MM_DD)
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