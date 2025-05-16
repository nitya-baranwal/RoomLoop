package com.misogi.roomloop.security;

import com.misogi.roomloop.repository.RoomRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomSecurity {
    
	@Autowired
    private RoomRepository roomRepository;
    
    public boolean isRoomCreator(UUID roomId, String userId) {
        return roomRepository.existsByIdAndCreatorId(roomId, UUID.fromString(userId));
    }
}
