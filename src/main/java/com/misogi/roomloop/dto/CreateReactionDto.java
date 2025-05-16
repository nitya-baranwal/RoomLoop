package com.misogi.roomloop.dto;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import java.util.UUID;

@Data
public class CreateReactionDto {
    @NotBlank
    private String emoji;
    
    private UUID messageId; // Optional, null if room reaction
}