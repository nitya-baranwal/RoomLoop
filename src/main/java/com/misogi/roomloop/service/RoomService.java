package com.misogi.roomloop.service;

import com.misogi.roomloop.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface RoomService {
    Page<RoomDto> getAllRooms(String status, String type, String tag, Pageable pageable);
    RoomDto createRoom(CreateRoomDto createRoomDto, String userId);
    RoomDetailsDto getRoomDetails(UUID id);
    RoomDto updateRoom(UUID id, UpdateRoomDto updateRoomDto);
    Page<ParticipantDto> getRoomParticipants(UUID id, Pageable pageable);
    ParticipantDto joinRoom(UUID id, String userId);
    void leaveRoom(UUID id, String userId);
    MessageDto postMessage(UUID id, CreateMessageDto createMessageDto, String userId);
    Page<MessageDto> getRoomMessages(UUID id, Pageable pageable);
    ReactionDto addReaction(UUID id, CreateReactionDto createReactionDto, String userId);
}