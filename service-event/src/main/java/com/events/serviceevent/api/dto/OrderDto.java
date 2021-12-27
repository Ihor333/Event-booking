package com.events.serviceevent.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class OrderDto {
    private Long id;
    private String orderPassword;
    private Long clientId;
    private Long eventId;
    private LocalDate orderDate;
}