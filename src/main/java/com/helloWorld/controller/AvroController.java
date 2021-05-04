package com.helloWorld.controller;

import com.helloWorld.avro.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class AvroController {

    @Autowired
    private KafkaTemplate<String, Customer> kafkaTemplate;

    String topicName="avro-thahir";


    @PostMapping("/publish")
    public void publishOCSMessage(@RequestBody final Customer customer) {

        kafkaTemplate.send(topicName,customer);

        System.out.println("published below customer data");
        System.out.println(customer);

    }

}
