package com.springbootandkafka.demo;

import com.springbootandkafka.demo.kafka.consumer.KafkaConsumer;
import com.springbootandkafka.demo.kafka.consumer.KafkaConsumerConfig;
import com.springbootandkafka.demo.kafka.producer.KafkaProducer;
import com.springbootandkafka.demo.kafka.producer.KafkaProducerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1,
        topics = {DemoApplicationTests.TEST_TOPIC})
public class DemoApplicationTests {

    static final String TEST_TOPIC = "testTopic.t";

    @Autowired
    private KafkaConsumer kafkaConsumer;

    @Autowired
    private KafkaProducer kafkaProducer;

    @Test
    public void testReceive() throws Exception {
        kafkaProducer.send("Hello Spring Kafka!");

        kafkaConsumer.getLatch().await(100, TimeUnit.MILLISECONDS);
        assertThat(kafkaConsumer.getLatch().getCount()).isEqualTo(0);
    }
}