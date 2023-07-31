package com.smarthomestay.service;

import com.smarthomestay.constant.GeneralConstant;
import com.smarthomestay.entity.*;
import com.smarthomestay.model.Request.ReservationAddRequest;
import com.smarthomestay.model.Request.RoomSaveRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.repository.CustomerRepository;
import com.smarthomestay.repository.ReservationRepository;
import com.smarthomestay.repository.RoomFacilitiesRepository;
import com.smarthomestay.repository.RoomRepository;
import com.smarthomestay.utils.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class ReservationService {

    private ReservationRepository reservationRepository;
    private CustomerRepository customerRepository;
    private RoomFacilitiesRepository roomFacilitiesRepository;
    private RoomRepository roomRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, CustomerRepository customerRepository, RoomFacilitiesRepository roomFacilitiesRepository, RoomRepository roomRepository) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.roomFacilitiesRepository = roomFacilitiesRepository;
        this.roomRepository = roomRepository;
    }

    public GeneralResponse<Object> addReservation(ReservationAddRequest request) {
        log.info("Start process add reservation");
        try {
            Optional<Customer> customerOptional = customerRepository.findById(request.getCustomer());
            if (customerOptional.isEmpty()) {
                return new GeneralResponse<>(400, "Failed", "Customer unregistered", null);
            }
            Customer customer = customerOptional.get();
            if (customer.getStatus().equals(GeneralConstant.STATUS_UNACTIVED)) {
                return new GeneralResponse<>(400, "Failed", "Customer unactivated", null);
            }

            Optional<Room> roomOptional = roomRepository.findById(request.getRoom());
            if (roomOptional.isEmpty()) {
                return new GeneralResponse<>(400, "Failed", "Room Not Found", null);
            }
            Room room = roomOptional.get();
            if (!room.getStatus().equals(GeneralConstant.STATUS_AVAILABLE)) {
                return new GeneralResponse<>(400, "Failed", "Room Not Available", null);
            }

            List<RoomFacilities> roomFacilitiesList = new ArrayList<>();
            int totalPriceAddtionalFacilities = 0;
            for (Long roomFacilitiesId : request.getRoomFacilities()) {
                Optional<RoomFacilities> roomFacilitiesOptional = roomFacilitiesRepository.findById(roomFacilitiesId);
                if (roomFacilitiesOptional.isEmpty()) {
                    return new GeneralResponse<>(400, "Failed", "Room Facility id : " + roomFacilitiesId + " Not Found ", null);
                }
                RoomFacilities roomFacilities = roomFacilitiesOptional.get();
                totalPriceAddtionalFacilities = totalPriceAddtionalFacilities + roomFacilities.getPrice();
                roomFacilitiesList.add(roomFacilities);
            }

            Date checkInDate = DateUtil.stringToDate(request.getCheckInDate(), GeneralConstant.YYYYMMDDHHMMSS);
            Date checkOutDate = DateUtil.stringToDate(request.getCheckOutDate(), GeneralConstant.YYYYMMDDHHMMSS);
            if (checkInDate == null) {
                return new GeneralResponse<>(400, "Failed", "Check in date cannot be null", null);
            }
            if (checkOutDate == null) {
                return new GeneralResponse<>(400, "Failed", "Check out date cannot be null", null);
            }
            int totalNight = (int) ChronoUnit.DAYS.between(checkInDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), checkOutDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
            int totalPriceReservation = (room.getRoomType().getPricePerNight() + totalPriceAddtionalFacilities) * totalNight;
            Reservation reservation = new Reservation(customer, room, roomFacilitiesList, checkInDate, checkOutDate, totalNight, totalPriceReservation, GeneralConstant.STATUS_NEW);
            reservation = reservationRepository.save(reservation);
            room.setStatus(GeneralConstant.STATUS_RESERVED);
            roomRepository.save(room);
            log.info("End process add reservation");
            return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success add data", reservation);
        } catch (Exception e) {
            log.info("Error process add reservation with message {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }

    }

    public GeneralResponse<Object> findAll() {
        log.info("Start process get room data");
        List<Room> data;
        try {
            data = roomRepository.findAll();
        } catch (Exception e) {
            log.info("Error process get room data with message {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
        log.info("End process get room data");
        return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success get data", data);
    }

    private static int getDaysBetweenDates(Date checkInDate, Date checkOutDate) {
        long differenceMillis = checkOutDate.getTime() - checkInDate.getTime();
        long daysBetween = TimeUnit.MILLISECONDS.toDays(differenceMillis);
        return (int) daysBetween;
    }
}
