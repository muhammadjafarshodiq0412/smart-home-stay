package com.smarthomestay.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoomFacilities extends BaseEntity{
    private String name;
    private int price;
    private String description;
}
