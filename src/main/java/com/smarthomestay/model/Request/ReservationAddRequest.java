package com.smarthomestay.model.Request;

import lombok.Data;

import java.util.List;

@Data
public class ReservationAddRequest {
    private Long customer;
    private Long room;
    private List<Long> roomFacilities;
    private String checkInDate;
    private String checkOutDate;
}
