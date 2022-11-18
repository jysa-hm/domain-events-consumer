//package com.happymoney.domain_events_consumer.dto;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import javax.validation.constraints.NotBlank;
//import java.time.LocalDateTime;
//
//import static com.happymoney.api_platform.utilities.util.DateUtils.HM_DATETIME_PATTERN;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class WebhookMessage {
//
//    @NotBlank
//    @JsonProperty("eventId")
//    private String eventId;
//
//    @NotBlank
//    @JsonProperty("tenantId")
//    private String tenantId;
//
//    @JsonProperty("resourceId")
//    private String resourceId;
//
//    @JsonProperty("resourceUrl")
//    private String resourceUrl;
//
//    @NotBlank
//    @JsonProperty("eventType")
//    private String eventType;
//
//    @NotBlank
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = HM_DATETIME_PATTERN)
//    @JsonProperty("created")
//    private LocalDateTime created;
//
//}
