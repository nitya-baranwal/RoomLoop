package com.misogi.roomloop.repository;

import com.misogi.roomloop.model.Room;
import com.misogi.roomloop.model.RoomStatus;
import com.misogi.roomloop.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {
    boolean existsByIdAndCreatorId(UUID id, UUID creatorId);
    
    @Query("SELECT r FROM Room r WHERE " +
           "(:status IS NULL OR r.status = :status) AND " +
           "(:type IS NULL OR r.type = :type) AND " +
           "(:tag IS NULL OR r.tag = :tag)")
    Page<Room> findByFilters(String status, String type, String tag, Pageable pageable);
}
