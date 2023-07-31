package com.smarthomestay.repository;

import com.smarthomestay.entity.RoomFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoomFacilitiesRepository extends JpaRepository<RoomFacilities, Long> {
    List<RoomFacilities> findByIdIn(List<Long> roomFacilities);
}
