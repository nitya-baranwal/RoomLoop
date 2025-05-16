package com.misogi.roomloop.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateMessageDto {
    @NotBlank
    @Size(max = 1000)
    private String content;
    
    private boolean isPinned = false;
}