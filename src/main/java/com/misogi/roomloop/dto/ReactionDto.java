package com.misogi.roomloop.dto;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class ReactionDto {
    private UUID id;
    private String emoji;
    private LocalDateTime createdAt;
    private UUID userId;
    private String userName;
    private UUID messageId; // Null if room reaction
}