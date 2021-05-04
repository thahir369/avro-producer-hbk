package com.helloWorld.config;


import com.helloWorld.avro.Customer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;


import java.util.HashMap;
import java.util.Map;


@Configuration
@EnableKafka
public class AvroProducerConfig {

    @Bean
    public ProducerFactory<String, Customer> customerProducerFactory(){

        Map<String,Object> configs=new HashMap<>();

        configs.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        configs.put(ProducerConfig.ACKS_CONFIG, "1");
        configs.put(ProducerConfig.RETRIES_CONFIG, "10");

        configs.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        configs.put("schema.registry.url", "http://localhost:8081");
        configs.put("auto.register.schemas", false);
        configs.put("use.latest.version", true);

        return new DefaultKafkaProducerFactory<>(configs);

    }

    @Bean
    public KafkaTemplate<String, Customer> kafkaTemplate1(){
        return new KafkaTemplate<String,Customer>(customerProducerFactory());
    }

}
