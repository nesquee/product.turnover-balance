package com.abelyaev.consumer.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static java.lang.String.format;

@Service
@Slf4j
public class BalanceConsumer {

    @KafkaListener(topics = "balance-stream", groupId = "saldoGroup")
    public void consumeMsg(String msg) {
        log.info(format("Consuming the message from balance-stream Topic:: %s", msg));
    }
}
