package com.worldtree.gateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.worldtree.common.dto.CommonMessage;
import com.worldtree.gateway.service.ChatKafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
public class ChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ChatKafkaProducer producer;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testSendMessageEndpoint() throws Exception {
        CommonMessage msg = new CommonMessage();
        msg.setSender("UnitTest");
        msg.setContent("Testing controller");
        msg.setTopic("chat-messages");

        mockMvc.perform(post("/chat/send")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(msg)))
                .andExpect(status().isOk());

        verify(producer).sendMessage(any(CommonMessage.class));
    }
}
