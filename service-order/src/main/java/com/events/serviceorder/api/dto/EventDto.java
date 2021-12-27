package com.events.serviceorder.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EventDto {
    private Long event_id;
    private String eventName;
    private String destination;
    private int duration;
    private String description;
    private int cost;
}
