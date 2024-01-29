package com.abelyaev.balance.producer;

import com.abelyaev.balance.model.dto.BalanceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceProducer {

    private final KafkaTemplate<String, BalanceInfo> kafkaTemplate;

    @Value("${balance.kafka.topic-name}")
    private String topicName;

    public void sendMessage(BalanceInfo balanceInfo) {

        Message<BalanceInfo> message = MessageBuilder
                .withPayload(balanceInfo)
                .setHeader(KafkaHeaders.TOPIC, topicName)
                .build();

        kafkaTemplate.send(message);
    }
}
