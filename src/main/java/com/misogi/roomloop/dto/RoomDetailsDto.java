package com.misogi.roomloop.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class RoomDetailsDto extends RoomDto {
    private List<ParticipantDto> participants;
    private List<MessageDto> messages;
    private List<MessageDto> pinnedMessages;
}