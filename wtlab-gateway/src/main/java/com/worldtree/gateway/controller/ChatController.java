package com.worldtree.gateway.controller;

import com.worldtree.common.dto.CommonMessage;
import com.worldtree.gateway.service.ChatKafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/chat")
public class ChatController {
    private final ChatKafkaProducer chatKafkaProducer;

    @Autowired
    public ChatController(ChatKafkaProducer producer) {
        this.chatKafkaProducer = producer;
    }

    @PostMapping("/send")
    public String sendChatMessage(@RequestBody CommonMessage msg) {
        chatKafkaProducer.sendMessage(msg);
        return "Message sent to topic: " + (msg.getTopic() != null ? msg.getTopic() : "default");
    }

}
