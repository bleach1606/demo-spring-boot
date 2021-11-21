package com.example.demo.config;

import com.example.demo.model.Message.NotificationMessage;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value(value = "${kafka.servers-config}")
    private String serversConfig;

    @Bean
    public ProducerFactory<String, NotificationMessage> notificationProducerFactory() {
        Map<String, Object> configProps = defaultConfigProps();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    private Map<String, Object> defaultConfigProps() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(
                ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
                serversConfig);
        configProps.put(
                ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        configProps.put(
                ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                JsonSerializer.class);
        return configProps;
    }

    @Bean
    public KafkaTemplate<String, NotificationMessage> notificationKafkaTemplate() {
        return new KafkaTemplate<>(notificationProducerFactory());
    }

}
