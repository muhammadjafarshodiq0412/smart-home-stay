package com.smarthomestay.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "customer")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "room")
    private Room room;
    @OneToMany
    @JoinTable(name = "additional_facilities", joinColumns = @JoinColumn(name = "room"), inverseJoinColumns = @JoinColumn(name = "room_facility"))
    private List<RoomFacilities> roomFacilities;
    private Date checkInDate;
    private Date checkOutDate;
    private int totalNight;
    private int totalPrice;
}
