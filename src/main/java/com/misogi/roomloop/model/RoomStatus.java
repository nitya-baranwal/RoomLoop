package com.misogi.roomloop.model;

public enum RoomStatus {
    SCHEDULED,  // Room is waiting to start (future start time)
    LIVE,       // Room is currently active (between start and end times)
    CLOSED      // Room has ended (past end time)
}