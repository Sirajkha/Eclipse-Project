package com.irctc_booking.kafka.producer.service;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

	final KafkaTemplate<String, String> kafkaTemplate;

	KafkaService(KafkaTemplate<String, String> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void publishMessage(String topic,String message) {
		
		System.out.println("KafkaService.publish()========= START ==========");
		ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, message);
		
		kafkaTemplate.send(record);
		
		System.out.println("KafkaService.publish()========= END ==========");
		
	}
	
}
