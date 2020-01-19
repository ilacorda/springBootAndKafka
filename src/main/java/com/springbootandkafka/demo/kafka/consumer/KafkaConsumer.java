package com.springbootandkafka.demo.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;

import java.util.concurrent.CountDownLatch;

public class KafkaConsumer {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(KafkaConsumer.class);

    private CountDownLatch latch = new CountDownLatch(1);

    public CountDownLatch getLatch() {
        return latch;
    }

    @KafkaListener(topics = "testTopic.t")
    public void receive(String payload) {
        LOGGER.info("This is the received paylod='{}'", payload);
        latch.countDown();
    }
}
