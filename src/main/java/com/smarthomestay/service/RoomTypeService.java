package com.smarthomestay.service;

import com.smarthomestay.entity.RoomType;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.repository.RoomTypeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class RoomTypeService {
    private RoomTypeRepository roomTypeRepository;

    @Autowired
    public RoomTypeService(RoomTypeRepository roomTypeRepository) {
        this.roomTypeRepository = roomTypeRepository;
    }

    public GeneralResponse<Object> findAll() {
        log.info("Start process get room type data");
        List<RoomType> roomTypes;
        try {
            roomTypes = roomTypeRepository.findAll();
        } catch (Exception e) {
            log.info("Error process get room type data with message {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
        log.info("End process get room type data");
        return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success get data", roomTypes);
    }

    public GeneralResponse<Object> findById(Long id) {
        log.info("Start process get room type data");
        RoomType roomType;
        try {
            Optional<RoomType> roomTypeOptional = roomTypeRepository.findById(id);
            if (roomTypeOptional.isEmpty()){
                return new GeneralResponse<>(400, "Failed", "Data Not Found", null);
            }
            roomType = roomTypeOptional.get();
        } catch (Exception e) {
            log.info("Error process get room type data with message {}", e.getMessage());
            return new GeneralResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), null);
        }
        log.info("End process get room type data");
        return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success get data", roomType);
    }
}
