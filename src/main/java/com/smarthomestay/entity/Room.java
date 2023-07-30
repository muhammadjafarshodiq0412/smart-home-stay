package com.smarthomestay.entity;

import com.smarthomestay.model.Request.RoomSaveRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Room extends BaseEntity {
    private String roomNumber;
    @OneToOne
    private RoomType roomType;
    private String capacity;
    private String floor;
    private String status;

    public Room(RoomSaveRequest request, RoomType roomType, String status) {
        this.roomNumber = request.getRoomNumber();
        this.roomType = roomType;
        this.capacity = request.getCapacity();
        this.floor = request.getFloor();
        this.status = status;
    }
}
