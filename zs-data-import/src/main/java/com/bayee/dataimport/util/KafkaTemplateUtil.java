package com.bayee.dataimport.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;

@Component
public class KafkaTemplateUtil {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    private static KafkaTemplateUtil kafkaTemplateUtil;

    @PostConstruct
    public void init() {
        kafkaTemplateUtil = this;
        kafkaTemplateUtil.kafkaTemplate = this.kafkaTemplate;
    }

    public static ListenableFuture send(String topic, Object data) {
        return kafkaTemplateUtil.kafkaTemplate.send(topic,data);
    }
}
