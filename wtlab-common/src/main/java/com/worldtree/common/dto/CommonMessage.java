package com.worldtree.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CommonMessage {
    private String topic;
    private String sender;
    private String content;
    private long timestamp;

    public CommonMessage(String sender, String content, String topic) {
        this.sender = sender;
        this.content = content;
        this.timestamp = System.currentTimeMillis();
        this.topic = topic;
    }
}
