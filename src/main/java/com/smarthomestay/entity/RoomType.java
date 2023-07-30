package com.smarthomestay.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class RoomType extends BaseEntity{
    private String name;
    private String description;
    private int pricePerNight;
}
