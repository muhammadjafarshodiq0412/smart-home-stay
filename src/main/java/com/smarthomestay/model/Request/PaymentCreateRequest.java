package com.smarthomestay.model.Request;

import lombok.Data;

@Data
public class PaymentCreateRequest {
    private long reservation;
    private int amountPaid;
}
