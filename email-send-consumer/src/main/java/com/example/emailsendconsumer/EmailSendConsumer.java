package com.example.emailsendconsumer;

import javax.management.RuntimeErrorException;

import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class EmailSendConsumer {

    @KafkaListener(
        topics="email.send",
        groupId = "email-send-group"
    )
    @RetryableTopic(
        attempts = "5", // 재시도 횟수
        backOff = @BackOff(delay = 1000, 
                           multiplier = 2), // 2배수로 delay 증가 1초 2초 4초 8초 ...
        dltTopicSuffix = ".dlt" // send.email.dlt  [설정안할 시(기본) = send.email-dlt]
    )
    public void consumer(String message){
        System.out.println("Kafka로부터 받아온 메시지 : " + message);
        EmailSendMessage emailSendMessage =EmailSendMessage.fromJson(message);

        if(emailSendMessage.getTo().equals("fail@naver.com")){
            System.out.println("잘못된 이메일 주소로 인해 발송 실패");
            throw new RuntimeException("잘못된 이메일 주소로 인해 발송 실패");
        }

        // ... 실제 이메일 발송 로직은 생략 ...
        /*
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException("이메일 발송 실패");
        }
        */
        System.out.println("이메일 발송 완료");
    }
}
