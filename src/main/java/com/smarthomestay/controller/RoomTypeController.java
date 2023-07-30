package com.smarthomestay.controller;


import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.service.RoomTypeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/room-type")
public class RoomTypeController {
    private RoomTypeService roomTypeService;

    @Autowired
    public RoomTypeController(RoomTypeService roomTypeService) {
        this.roomTypeService = roomTypeService;
    }

    @GetMapping
    public GeneralResponse<Object> findAll() {
        log.info("Incoming get room type data list");
        return roomTypeService.findAll();
    }

    @GetMapping("/findBy")
    public GeneralResponse<Object> findById(@RequestParam Long id) {
        log.info("Incoming get room type data with id {}", id);
        return roomTypeService.findById(id);
    }
}
