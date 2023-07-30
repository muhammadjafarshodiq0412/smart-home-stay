package com.smarthomestay.controller;

import com.smarthomestay.model.Request.CustomerSaveRequest;
import com.smarthomestay.model.Request.RoomSaveRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.service.CustomerServiceImpl;
import com.smarthomestay.service.RoomService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/room")
public class RoomController {

    private RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/create")
    public GeneralResponse<Object> register(@RequestBody @Valid RoomSaveRequest request) {
        log.info("Incoming room create with request {}", request.toString());
        return roomService.save(request);
    }

    @GetMapping
    public GeneralResponse<Object> findAll() {
        log.info("Incoming get room data");
        return roomService.findAll();
    }
}
