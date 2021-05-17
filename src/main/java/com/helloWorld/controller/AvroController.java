package com.helloWorld.controller;

import com.helloWorld.avro.CustomerV1;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.specific.SpecificRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
@Slf4j
public class AvroController {

    @Autowired
    private KafkaTemplate<String, SpecificRecord> kafkaTemplate;

    @Value("${producer.kafka.topic}")
    private String topicName;

    @PostMapping("/publish1")
    public String publishCustomerV1(@RequestBody final CustomerV1 data) {

        kafkaTemplate.send(topicName,data);

        log.info("published below customer data");
        log.info(data.toString());

        return "CustomerV1 Data published successfully!!!!";
    }
}
