package com.misogi.roomloop.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.Data;

@Entity
@Data
public class Room {
    @Id
    @GeneratedValue
    private UUID id;
    
    private String title;
    private String description;
    
    @Enumerated(EnumType.STRING)
    private RoomStatus status; 
    
    @Enumerated(EnumType.STRING)
    private RoomType roomType;
    
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer maxParticipants;
    
    @Enumerated(EnumType.STRING)
    private RoomTag tag;
    
    @ManyToOne
    private User creator;
    
    public enum RoomType {
        PRIVATE, PUBLIC
    }
    
    public enum RoomTag {
        HANGOUT, WORK, BRAINSTORM, WELLNESS
    }
    
 // Business logic to update status
    @PreUpdate
    @PrePersist
    public void updateStatus() {
        LocalDateTime now = LocalDateTime.now();
        if (now.isBefore(startTime)) {
            this.status = RoomStatus.SCHEDULED;
        } else if (now.isAfter(endTime)) {
            this.status = RoomStatus.CLOSED;
        } else {
            this.status = RoomStatus.LIVE;
        }
    }
}