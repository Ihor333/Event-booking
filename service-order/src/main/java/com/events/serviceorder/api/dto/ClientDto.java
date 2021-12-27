package com.events.serviceorder.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClientDto {
    private Long client_id;
    private String clientFirstName;
    private String clientSecondName;
    private String clientEmail;
    private String clientPhone;
    private String clientPassword;
}
