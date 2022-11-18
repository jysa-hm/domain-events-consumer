package com.happymoney.domain_events_consumer.dto;

import com.happymoney.domain_events_consumer.enumeration.EventStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResourceEventDto {
    private String eventId;
    private String tenantId;
    private String resourceId;
    private String applicationName;
    private String eventType;
    private String resourceUrl;
    private LocalDateTime created;
    private EventStatus status;
    private String reason;

}
