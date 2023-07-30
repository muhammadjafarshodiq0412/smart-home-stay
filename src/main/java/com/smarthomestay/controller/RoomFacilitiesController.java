package com.smarthomestay.controller;


import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.service.RoomFacilitiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/room-facilities")
public class RoomFacilitiesController {
    private RoomFacilitiesService roomFacilitiesService;

    @Autowired
    public RoomFacilitiesController(RoomFacilitiesService roomFacilitiesService) {
        this.roomFacilitiesService = roomFacilitiesService;
    }

    @GetMapping
    public GeneralResponse<Object> findAll() {
        log.info("Incoming get room facilities data list");
        return roomFacilitiesService.findAll();
    }
}
