package com.smarthomestay.controller;

import com.smarthomestay.model.Request.PaymentCreateRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.service.PaymentServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    private PaymentServiceImpl paymentService;

    @Autowired
    public PaymentController(PaymentServiceImpl paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public GeneralResponse<Object> createPayment(@RequestBody @Valid PaymentCreateRequest request) {
        log.info("Incoming create payment with request {}", request.toString());
        return paymentService.create(request);
    }
}
