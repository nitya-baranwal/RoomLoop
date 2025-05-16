package com.roomloop.backend.controller;

import com.roomloop.backend.dto.*;
import com.roomloop.backend.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    // List rooms with filters
    @GetMapping
    public ResponseEntity<Page<RoomDto>> getAllRooms(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String tag,
            Pageable pageable) {
        return ResponseEntity.ok(roomService.getAllRooms(status, type, tag, pageable));
    }

    // Create new room
    @PostMapping
    public ResponseEntity<RoomDto> createRoom(
            @Valid @RequestBody CreateRoomDto createRoomDto,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(roomService.createRoom(createRoomDto, userId));
    }

    // Get room details
    @GetMapping("/{id}")
    public ResponseEntity<RoomDetailsDto> getRoomDetails(@PathVariable UUID id) {
        return ResponseEntity.ok(roomService.getRoomDetails(id));
    }

    // Update room (creator only)
    @PutMapping("/{id}")
    @PreAuthorize("@roomSecurity.isRoomCreator(#id, #jwt.subject)")
    public ResponseEntity<RoomDto> updateRoom(
            @PathVariable UUID id,
            @Valid @RequestBody UpdateRoomDto updateRoomDto,
            @AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(roomService.updateRoom(id, updateRoomDto));
    }

    // List room participants
    @GetMapping("/{id}/participants")
    public ResponseEntity<Page<ParticipantDto>> getRoomParticipants(
            @PathVariable UUID id,
            Pageable pageable) {
        return ResponseEntity.ok(roomService.getRoomParticipants(id, pageable));
    }

    // Join a room
    @PostMapping("/{id}/join")
    public ResponseEntity<ParticipantDto> joinRoom(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(roomService.joinRoom(id, userId));
    }

    // Leave a room
    @PostMapping("/{id}/leave")
    public ResponseEntity<Void> leaveRoom(
            @PathVariable UUID id,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        roomService.leaveRoom(id, userId);
        return ResponseEntity.noContent().build();
    }

    // Post message to room
    @PostMapping("/{id}/messages")
    public ResponseEntity<MessageDto> postMessage(
            @PathVariable UUID id,
            @Valid @RequestBody CreateMessageDto createMessageDto,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(roomService.postMessage(id, createMessageDto, userId));
    }

    // Get room messages
    @GetMapping("/{id}/messages")
    public ResponseEntity<Page<MessageDto>> getRoomMessages(
            @PathVariable UUID id,
            Pageable pageable) {
        return ResponseEntity.ok(roomService.getRoomMessages(id, pageable));
    }

    // Add reaction
    @PostMapping("/{id}/reactions")
    public ResponseEntity<ReactionDto> addReaction(
            @PathVariable UUID id,
            @Valid @RequestBody CreateReactionDto createReactionDto,
            @AuthenticationPrincipal Jwt jwt) {
        String userId = jwt.getSubject();
        return ResponseEntity.ok(roomService.addReaction(id, createReactionDto, userId));
    }
}