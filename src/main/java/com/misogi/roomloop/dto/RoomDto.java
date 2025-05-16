package com.misogi.roomloop.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.misogi.roomloop.model.Room.RoomType;
import com.misogi.roomloop.model.RoomStatus;

import lombok.Data;

@Data
public class RoomDto {
    private UUID id;
    private String title;
    private String description;
    private RoomType type;
    private RoomStatus status;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxParticipants;
    private String tag;
    private UUID creatorId;
    private String creatorName;
    private Integer participantCount;
}
