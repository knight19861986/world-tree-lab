package com.worldtree.gateway.service;

import com.worldtree.common.dto.CommonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ChatKafkaProducer {
    private static final String DEFAULT_TOPIC = "chat-messages";

    private final KafkaTemplate<String, CommonMessage> kafkaTemplate;

    @Autowired
    public ChatKafkaProducer(KafkaTemplate<String, CommonMessage> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(CommonMessage message) {
        if (message.getTimestamp() <= 0) {
            message.setTimestamp(System.currentTimeMillis());
        }

        String topic = (message.getTopic() != null && !message.getTopic().isEmpty())
                ? message.getTopic()
                : DEFAULT_TOPIC;

        message.setTopic(topic);
        kafkaTemplate.send(topic, message);
        System.out.println("Sent message to topic [" + topic + "]: " + message);
    }
}
