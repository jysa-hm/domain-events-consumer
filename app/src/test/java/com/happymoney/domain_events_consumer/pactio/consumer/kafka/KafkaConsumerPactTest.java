package com.happymoney.domain_events_consumer.pactio.consumer.kafka;

import au.com.dius.pact.consumer.MessagePactBuilder;
import au.com.dius.pact.consumer.dsl.PactDslJsonBody;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.consumer.junit5.ProviderType;
import au.com.dius.pact.core.model.PactSpecVersion;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.messaging.Message;
import au.com.dius.pact.core.model.messaging.MessagePact;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.happymoney.domain_events_consumer.dto.ResourceEventDto;
import com.happymoney.domain_events_consumer.enumeration.EventStatus;
import org.hamcrest.MatcherAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import com.happymoney.api_platform.avro.ResourceEvent;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "ApiVerifyProvider", providerType = ProviderType.ASYNCH, pactVersion = PactSpecVersion.V3)
public class KafkaConsumerPactTest {
    ObjectMapper mapper = new ObjectMapper();

    ResourceEventDto resourceEventDto = ResourceEventDto.builder()
            .eventId("74aa7a5b-6ff1-45fd-ae02-5ed0d27b6fae")
            .tenantId("213cbf50-eafb-41b3-bce5-bd662fdbf0a7")
            .resourceId(null)
            .resourceUrl(null)
            .applicationName("verify-api")
            .eventType("COMPLETED")
            .created(LocalDateTime.now())
            .status(EventStatus.SUCCESS)
            .reason(null)
            .build();

    /**
     Below is an example of a Consumer test case requiring data shape (eventId, tenantId, applicationName, eventType, created, status, reason)
     of type string and not null (based on avro). Add/update as required.

     Example of exact data shape being validated:
     {
     "eventId": "74aa7a5b-6ff1-45fd-ae02-5ed0d27b6fae",
     "tenantId": "213cbf50-eafb-41b3-bce5-bd662fdbf0a7",
     "resourceId": "5bd2e0a9-d819-439f-ae11-b2d0dfe8f2c3",
     "resourceUrl": "https://api-platform-api-qa.happymoney.com/services/loanapplication/loanapplicationservice/v1/applications/5bd2e0a9-d819-439f-ae11-b2d0dfe8f2c3",
     "applicationName": "espial.loan_application",
     "eventType": "underwriting.complete",
     "created": "2022-11-09T18:44:12.847476",
     "status": "SUCCESS",
     "reason": "Underwriting complete"
     }
     Note: In this case, extra fields are fine, but not less than required as data shape above.
     **/

    @Pact(consumer = "DomainEventsConsumer")
    public MessagePact getResourceEventFromKafka(MessagePactBuilder builder) throws JsonProcessingException {
        return builder
                .expectsToReceive("correct data shape from kafka provider")
                .withContent(
                        new PactDslJsonBody()
                                .stringType("eventId", resourceEventDto.getEventId())
                                .stringType("tenantId", resourceEventDto.getTenantId())
                                .nullValue("resourceId")
                                .nullValue("resourceUrl")
                                .stringType("applicationName", resourceEventDto.getApplicationName())
                                .stringType("eventType", resourceEventDto.getEventType())
                                .stringType("created", resourceEventDto.getCreated().toString())
                                .stringType("status", resourceEventDto.getStatus().toString())
                                .nullValue("reason")
                )
                .toPact();
    }

    @Test
    @PactTestFor(pactMethod = "getResourceEventFromKafka", pactVersion = PactSpecVersion.V3)
    public void testValidResourceEventFromProvider(List<Message> messages) throws JsonProcessingException {
        assertThat(messages).isNotEmpty();
        ResourceEvent parsedResult = mapper.readValue(messages.get(0).contentsAsString(), ResourceEvent.class);
        System.out.println("DATA: "+ parsedResult);
        MatcherAssert.assertThat(parsedResult, allOf(
                hasProperty("eventId", notNullValue(String.class)),
                hasProperty("tenantId", notNullValue(String.class)),
                hasProperty("applicationName", notNullValue(String.class)),
                hasProperty("eventType", notNullValue(String.class)),
                hasProperty("created", notNullValue(String.class)),
                hasProperty("status", notNullValue(String.class))
                                                    ));
    }
}
