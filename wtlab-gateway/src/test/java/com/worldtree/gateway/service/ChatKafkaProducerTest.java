package com.worldtree.gateway.service;

import com.worldtree.common.dto.CommonMessage;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.KafkaTemplate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ChatKafkaProducerTest {
    private static final String DEFAULT_TOPIC = "chat-messages";

    @Test
    public void testSendMessageUsesDefaultTopicWhenNoneProvided() {
        KafkaTemplate<String, CommonMessage> kafkaTemplate = mock(KafkaTemplate.class);
        ChatKafkaProducer producer = new ChatKafkaProducer(kafkaTemplate);

        CommonMessage message = new CommonMessage();
        message.setSender("Tester");
        message.setContent("Hello test!");
        // no topic

        producer.sendMessage(message);

        verify(kafkaTemplate).send(eq(DEFAULT_TOPIC), any(CommonMessage.class));
    }
}
