package com.example.emailsendproducer;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SendEmailRequestDto {
    private String from; // 누구로 부터 왔는지 (발신자)
    private String to; // 누구에게 보내는지 (수신자)
    private String subject; // 제목
    private String body; // 본문

    @JsonCreator
    public SendEmailRequestDto(
        @JsonProperty("from") String from,
        @JsonProperty("to") String to,
        @JsonProperty("subject") String subject,
        @JsonProperty("body") String body
    ) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.body = body;
    }
    public String getFrom() {
        return from;
    }
    public String getTo() {
        return to;
    }
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
}
