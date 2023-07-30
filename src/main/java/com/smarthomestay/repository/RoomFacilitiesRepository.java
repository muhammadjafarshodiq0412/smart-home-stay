package com.smarthomestay.repository;

import com.smarthomestay.entity.RoomFacilities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomFacilitiesRepository extends JpaRepository<RoomFacilities, Long> {
}
