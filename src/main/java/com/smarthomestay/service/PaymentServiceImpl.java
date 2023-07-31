package com.smarthomestay.service;

import com.smarthomestay.constant.GeneralConstant;
import com.smarthomestay.entity.Payment;
import com.smarthomestay.entity.Reservation;
import com.smarthomestay.entity.Room;
import com.smarthomestay.model.Request.PaymentCreateRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.repository.PaymentRepository;
import com.smarthomestay.repository.ReservationRepository;
import com.smarthomestay.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class PaymentServiceImpl {

    private PaymentRepository paymentRepository;
    private ReservationRepository reservationRepository;
    private RoomRepository roomRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, ReservationRepository reservationRepository, RoomRepository roomRepository) {
        this.paymentRepository = paymentRepository;
        this.reservationRepository = reservationRepository;
        this.roomRepository = roomRepository;
    }

    public GeneralResponse<Object> create(PaymentCreateRequest request) {
        log.info("Start process create payment");
        try {
            Optional<Reservation> reservationOptional = reservationRepository.findById(request.getReservation());
            if (reservationOptional.isEmpty()) {
                return new GeneralResponse<>(400, "Failed", "Reservation not found", null);
            }
            Reservation reservation = reservationOptional.get();
            Payment payment = new Payment(reservation, request.getAmountPaid(), GeneralConstant.STATUS_DONE);
            payment = paymentRepository.save(payment);

            reservation.setStatus(GeneralConstant.STATUS_DONE);
            reservationRepository.save(reservation);

            Room room = reservation.getRoom();
            room.setStatus(GeneralConstant.STATUS_AVAILABLE);
            roomRepository.save(room);
            log.info("Success process create payment");
            return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success save data", payment);
        } catch (Exception e) {
            log.error("Error process create payment with message : {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
    }
}
