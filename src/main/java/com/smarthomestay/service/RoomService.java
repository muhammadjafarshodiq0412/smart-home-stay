package com.smarthomestay.service;

import com.smarthomestay.constant.GeneralConstant;
import com.smarthomestay.entity.Customer;
import com.smarthomestay.entity.Room;
import com.smarthomestay.entity.RoomFacilities;
import com.smarthomestay.entity.RoomType;
import com.smarthomestay.model.Request.CustomerSaveRequest;
import com.smarthomestay.model.Request.RoomSaveRequest;
import com.smarthomestay.model.Response.GeneralResponse;
import com.smarthomestay.repository.CustomerRepository;
import com.smarthomestay.repository.RoomRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class RoomService {

    private RoomRepository roomRepository;
    private RoomTypeService roomTypeService;

    @Autowired
    public RoomService(RoomRepository roomRepository, RoomTypeService roomTypeService) {
        this.roomRepository = roomRepository;
        this.roomTypeService = roomTypeService;
    }

    public GeneralResponse<Object> save(RoomSaveRequest request) {
        log.info("Start process saving room");
        try {
            //todo check room type is avaliable ?
            GeneralResponse<Object> roomTypeServiceById = roomTypeService.findById(request.getRoomType());
            if (roomTypeServiceById.getCode() != HttpStatus.OK.value()){
                return new GeneralResponse<>(HttpStatus.BAD_REQUEST.value(), "Failed", "Room Type Not Found", null);
            }
            
            if(roomTypeServiceById.getData() instanceof RoomType){
                RoomType roomType = (RoomType) roomTypeServiceById.getData(); // todo untuk ubah object ke kelas yang specific (cast)
                Room data = new Room(request, roomType, GeneralConstant.STATUS_AVAILABLE);
                data = roomRepository.save(data);
                log.info("Success process saving room");
                return new GeneralResponse<>(HttpStatus.OK.value(), "Success", "Success save data", data);
            }else {
                return new GeneralResponse<>(400, "Failed", "Room Type Not Found", null);
            }

        } catch (Exception e) {
            log.error("Error process saving room with message : {}", e.getMessage());
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
}
