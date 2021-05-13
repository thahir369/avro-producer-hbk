package com.helloWorld.config;


import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${producer.kafka.acks}")
    private String acks;
    @Value("${producer.kafka.retries}")
    private int retries;
    @Value("${producer.kafka.retry.backoff.ms}")
    private long retryBackoffMs;
    @Value("${producer.kafka.reconnect.backoff.ms}")
    private int reconnectBackoff;
    @Value("${producer.kafka.reconnect.backoff.max.ms}")
    private int reconnectBackoffMax;
    @Value("${producer.kafka.max.block.ms}")
    private long maxBlockMs;
    @Value("${producer.kafka.delivery.timeout.ms}")
    private int deliveryTimeoutMs;
    @Value("${producer.kafka.bootstrap-servers}")
    private String bootstrapServers;
    @Value("${producer.kafka.schema-registry-url}")
    private String schemaRegistryUrl;

    @Bean
    public ProducerFactory<String, SpecificRecord> customerProducerFactory(){

        Map<String,Object> config=new HashMap<>();

        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);

        config.put(ProducerConfig.ACKS_CONFIG, acks);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        config.put(ProducerConfig.RETRIES_CONFIG, retries);
        config.put(ProducerConfig.RETRY_BACKOFF_MS_CONFIG, retryBackoffMs);
        config.put(ProducerConfig.MAX_BLOCK_MS_CONFIG, maxBlockMs);
        config.put(ProducerConfig.RECONNECT_BACKOFF_MS_CONFIG, reconnectBackoff);
        config.put(ProducerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, reconnectBackoffMax);

        config.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        config.put(AbstractKafkaAvroSerDeConfig.AUTO_REGISTER_SCHEMAS, true);

        return new DefaultKafkaProducerFactory<>(config);

    }

    @Bean
    public KafkaTemplate<String, SpecificRecord> kafkaTemplate(){
        return new KafkaTemplate<>(customerProducerFactory());
    }

}
