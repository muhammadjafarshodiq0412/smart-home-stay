package com.smarthomestay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity{
    @OneToOne
    private RoomType roomType;
    @OneToMany
    @JoinTable(name = "additional_facilities", joinColumns = @JoinColumn(name = "room"), inverseJoinColumns = @JoinColumn(name = "room_facility"))
    private List<RoomFacilities> roomFacilities;
    private String capacity;
    private String status;
}
