package com.misogi.roomloop.dto;

import javax.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class UpdateRoomDto {
    @Size(max = 100)
    private String title;
    
    @Size(max = 500)
    private String description;
    
    private LocalDateTime endTime; // For extending duration
    
    private Integer maxParticipants;
}
