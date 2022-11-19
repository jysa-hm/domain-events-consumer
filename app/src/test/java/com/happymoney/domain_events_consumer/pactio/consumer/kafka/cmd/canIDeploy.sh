./gradlew test --tests KafkaConsumerPactTest \
canideploy -Ppacticipant='ApiVerifyProvider' -PpacticipantVersion=0.0.1-SNAPSHOT \
-Ppacticipant='DomainEventsConsumer' -PpacticipantVersion=0.0.1-SNAPSHOT
