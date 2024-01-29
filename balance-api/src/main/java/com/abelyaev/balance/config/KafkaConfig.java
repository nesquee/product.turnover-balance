package com.abelyaev.balance.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Value("${balance.kafka.topic-name}")
    private String topicName;

    @Bean
    public NewTopic wikimediaStreamTopic() {
        return TopicBuilder
                .name(topicName)
                .build();
    }
}
