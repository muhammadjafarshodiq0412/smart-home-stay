package com.smarthomestay.controller;

import com.smarthomestay.model.Request.CustomerSaveRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.service.CustomerServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/additional-facilities")
public class AdditionalFacilitiesController {

    private CustomerServiceImpl customerService;

    @Autowired
    public AdditionalFacilitiesController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/add")
    public GeneralResponse<Object> register(@RequestBody @Valid CustomerSaveRequest request) {
        log.info("Incoming customer register with request {}", request.toString());
        return customerService.save(request);
    }
}
