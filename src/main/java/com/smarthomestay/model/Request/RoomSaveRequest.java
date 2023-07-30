package com.smarthomestay.model.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoomSaveRequest {
    private String roomNumber;
    private Long roomType;
    private String capacity;
    private String floor;
}
