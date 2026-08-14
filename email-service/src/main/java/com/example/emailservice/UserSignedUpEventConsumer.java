package com.example.emailservice;

import org.springframework.kafka.annotation.BackOff;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.RetryableTopic;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Service;

@Service
public class UserSignedUpEventConsumer {
    
    private final EmailLogRepository emailLogRepository;

    public UserSignedUpEventConsumer(EmailLogRepository emailLogRepository) {
        this.emailLogRepository = emailLogRepository;
    }

    @KafkaListener(
        topics = "user.signed-up",
        groupId = "email-service",
        concurrency = "3"
        
    )
    @RetryableTopic(
        attempts = "3",
        backOff = @BackOff(delay = 1000, multiplier = 2),
        dltTopicSuffix = ".dlt"
    )
    public void consume(String message) throws InterruptedException {
        UserSignedUpEvent userSignedUpEvent = UserSignedUpEvent.fromJson(message);
        // 실제 이메일 발송 로직
        String receiverEmail = userSignedUpEvent.getEmail();
        String subject = userSignedUpEvent.getName() +" 님, 회원가입을 축하합니다.";
        Thread.sleep(3000);
        System.out.println("이메일 발송 완료");

        //이메일 발송 로그 저장
        EmailLog emailLog = new EmailLog(
            userSignedUpEvent.getUserId(),
            receiverEmail,
            subject
        );
        
        emailLogRepository.save(emailLog);
    }
}
