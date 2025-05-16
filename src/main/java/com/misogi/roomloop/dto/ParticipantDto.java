package com.misogi.roomloop.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ParticipantDto {
    private UUID userId;
    private String userName;
    private String avatarUrl;
    private LocalDateTime joinedAt;
    private LocalDateTime lastActive;
}