package com.smarthomestay.controller;

import com.smarthomestay.model.Request.CustomerSaveRequest;
import com.smarthomestay.model.Request.ReservationAddRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.service.CustomerServiceImpl;
import com.smarthomestay.service.ReservationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/add")
    public GeneralResponse<Object> register(@RequestBody @Valid ReservationAddRequest request) {
        log.info("Incoming add reservation with request {}", request.toString());
        return reservationService.addReservation(request);
    }
}
