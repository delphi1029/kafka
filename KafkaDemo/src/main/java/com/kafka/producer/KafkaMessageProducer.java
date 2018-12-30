package com.kafka.producer;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaMessageProducer {

	public static void main(String[] args) {
		
		Properties props = new Properties();
		
		props.setProperty("bootstrap.servers", "127.0.0.1:9092");
		props.setProperty("key.serializer", StringSerializer.class.getName());
		props.setProperty("value.serializer", StringSerializer.class.getName());
		props.setProperty("acks", "1");
		props.setProperty("retries", "3");
		props.setProperty("linger.ms", "1");
		
		Producer<String,String> producer = new KafkaProducer<String,String>(props);
		
		for(int key=0; key<10; key++) {
			ProducerRecord<String,String> message = new ProducerRecord<String,String>("second_topic","key"+key,"This second value is for key "+key);
			producer.send(message);
		}
		producer.close();
		
	}
}
