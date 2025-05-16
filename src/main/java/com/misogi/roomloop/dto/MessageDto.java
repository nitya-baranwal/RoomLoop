package com.misogi.roomloop.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class MessageDto {
    private UUID id;
    private String content;
    private LocalDateTime createdAt;
    private UUID userId;
    private String userName;
    private String userAvatar;
}