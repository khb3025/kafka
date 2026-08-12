package com.example.emailsendconsumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class EmailSendDltConsumer {
    private final KafkaTemplate kafkaTemplate;
    
    public EmailSendDltConsumer(KafkaTemplate kafkaTemplate){
        this.kafkaTemplate=kafkaTemplate;
    }

    @KafkaListener(
        topics = "email.send.dlt",
        groupId = "email-send-dlt-group"
    )
    public void consume(String message){
        // 로그 시스템에 전송
        System.out.println("로그 시스템 전송 : " + message);

        // 알림 발송
        System.out.println("Slack 알림 발송");
    }
}
