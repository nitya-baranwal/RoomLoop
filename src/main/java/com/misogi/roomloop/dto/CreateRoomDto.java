package com.misogi.roomloop.dto;

import java.time.LocalDateTime;

import javax.validation.constraints.*;

import com.misogi.roomloop.model.Room.RoomType;
import lombok.Data;


@Data
public class CreateRoomDto {
    @NotBlank
    @Size(max = 100)
    private String title;
    
    @Size(max = 500)
    private String description;
    
    @NotNull
    private RoomType type;
    
    @NotNull
    @Future
    private LocalDateTime startTime;
    
    @NotNull
    @Future
    private LocalDateTime endTime;
    
    @Min(1)
    private Integer maxParticipants;
    
    @NotBlank
    private String tag;
}
