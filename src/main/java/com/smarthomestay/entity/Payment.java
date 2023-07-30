package com.smarthomestay.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Payment extends BaseEntity{
    @OneToOne
    private Reservation reservation;
    private int amountPaid;
    private String status;
}
