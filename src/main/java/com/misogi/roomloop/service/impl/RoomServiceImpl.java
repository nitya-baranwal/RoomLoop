package com.misogi.roomloop.service.impl;

import com.misogi.roomloop.dto.*;
import com.misogi.roomloop.exception.ResourceNotFoundException;
import com.misogi.roomloop.mapper.*;
import com.misogi.roomloop.model.*;
import com.misogi.roomloop.repository.*;
import com.misogi.roomloop.service.RoomService;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RoomServiceImpl implements RoomService {


    @Autowired
    private RoomRepository roomRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ParticipantRepository participantRepository;
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private ReactionRepository reactionRepository;
    
    @Autowired
    private RoomMapper roomMapper;
    
    @Autowired
    private ParticipantMapper participantMapper;
    
    @Autowired
    private MessageMapper messageMapper;
    
    @Autowired
    private ReactionMapper reactionMapper;


    @Override
    public Page<RoomDto> getAllRooms(String status, String type, String tag, Pageable pageable) {
        // Implement filtering logic based on parameters
        return roomRepository.findAll(pageable).map(roomMapper::toDto);
    }

    @Override
    @Transactional
    public RoomDto createRoom(CreateRoomDto createRoomDto, String userId) {
        User creator = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Room room = roomMapper.toEntity(createRoomDto);
        room.setCreator(creator);
        room.setStatus(RoomStatus.SCHEDULED);
        
        Room savedRoom = roomRepository.save(room);
        return roomMapper.toDto(savedRoom);
    }

    @Override
    public RoomDetailsDto getRoomDetails(UUID id) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        return roomMapper.toDetailsDto(room);
    }

    @Override
    @Transactional
    public RoomDto updateRoom(UUID id, UpdateRoomDto updateRoomDto) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        
        roomMapper.updateEntity(updateRoomDto, room);
        Room updatedRoom = roomRepository.save(room);
        return roomMapper.toDto(updatedRoom);
    }

    @Override
    public Page<ParticipantDto> getRoomParticipants(UUID id, Pageable pageable) {
        if (!roomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Room not found");
        }
        return participantRepository.findByRoomId(id, pageable)
                .map(participantMapper::toDto);
    }

    @Override
    @Transactional
    public ParticipantDto joinRoom(UUID id, String userId) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        
        if (room.getStatus() != RoomStatus.LIVE) {
            throw new IllegalStateException("Room is not live");
        }
        
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (participantRepository.existsByRoomAndUser(room, user)) {
            throw new IllegalStateException("User already joined the room");
        }
        
        Participant participant = new Participant();
        participant.setRoom(room);
        participant.setUser(user);
        participant.setJoinedAt(LocalDateTime.now());
        
        Participant savedParticipant = participantRepository.save(participant);
        return participantMapper.toDto(savedParticipant);
    }

    @Override
    @Transactional
    public void leaveRoom(UUID id, String userId) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        Participant participant = participantRepository.findByRoomAndUser(room, user)
                .orElseThrow(() -> new ResourceNotFoundException("Participant not found"));
        
        participantRepository.delete(participant);
    }

    @Override
    @Transactional
    public MessageDto postMessage(UUID id, CreateMessageDto createMessageDto, String userId) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        
        if (room.getStatus() != RoomStatus.LIVE) {
            throw new IllegalStateException("Room is not live");
        }
        
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (!participantRepository.existsByRoomAndUser(room, user)) {
            throw new IllegalStateException("User is not a participant of this room");
        }
        
        Message message = messageMapper.toEntity(createMessageDto);
        message.setRoom(room);
        message.setUser(user);
        message.setCreatedAt(LocalDateTime.now());
        
        Message savedMessage = messageRepository.save(message);
        return messageMapper.toDto(savedMessage);
    }

    @Override
    public Page<MessageDto> getRoomMessages(UUID id, Pageable pageable) {
        if (!roomRepository.existsById(id)) {
            throw new ResourceNotFoundException("Room not found");
        }
        return messageRepository.findByRoomIdOrderByCreatedAtDesc(id, pageable)
                .map(messageMapper::toDto);
    }

    @Override
    @Transactional
    public ReactionDto addReaction(UUID id, CreateReactionDto createReactionDto, String userId) {
        Room room = roomRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found"));
        
        if (room.getStatus() != RoomStatus.LIVE) {
            throw new IllegalStateException("Room is not live");
        }
        
        User user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        
        if (!participantRepository.existsByRoomAndUser(room, user)) {
            throw new IllegalStateException("User is not a participant of this room");
        }
        
        Reaction reaction = reactionMapper.toEntity(createReactionDto);
        reaction.setRoom(room);
        reaction.setUser(user);
        reaction.setCreatedAt(LocalDateTime.now());
        
        Reaction savedReaction = reactionRepository.save(reaction);
        return reactionMapper.toDto(savedReaction);
    }
}