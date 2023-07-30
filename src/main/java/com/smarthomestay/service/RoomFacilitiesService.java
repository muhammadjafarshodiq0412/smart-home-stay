package com.smarthomestay.service;

import com.smarthomestay.entity.RoomFacilities;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.repository.RoomFacilitiesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoomFacilitiesService {
    private RoomFacilitiesRepository roomFacilitiesRepository;

    @Autowired
    public RoomFacilitiesService(RoomFacilitiesRepository roomFacilitiesRepository) {
        this.roomFacilitiesRepository = roomFacilitiesRepository;
    }

    public GeneralResponse<Object> findAll() {
        log.info("Start process get room facilities data");
        List<RoomFacilities> roomTypes;
        try {
            roomTypes = roomFacilitiesRepository.findAll();
        } catch (Exception e) {
            log.info("Error process get room facilities data with message {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
        log.info("End process get room facilities data");
        return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success get data", roomTypes);
    }
}
