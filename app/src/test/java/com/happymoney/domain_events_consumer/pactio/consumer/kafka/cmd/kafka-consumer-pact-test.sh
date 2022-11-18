./gradlew test --tests KafkaConsumerPactTest \
#pactPublish \
canideploy -Ppacticipant='ApiVerifyProvider' -PpacticipantVersion=0.0.2-SNAPSHOT \
-Ppacticipant='DomainEventsConsumer' -PpacticipantVersion=0.0.2-SNAPSHOT
