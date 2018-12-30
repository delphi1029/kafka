package com.kafka.consumer;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

public class KafkaMessageConsumer {

	public static void main(String[] args) {
Properties props = new Properties();
		
		props.setProperty("bootstrap.servers", "127.0.0.1:9092");
		props.setProperty("key.deserializer", StringDeserializer.class.getName());
		props.setProperty("value.deserializer", StringDeserializer.class.getName());
		props.setProperty("group.id", "test");
		props.setProperty("enable.auto.commit", "true");
		props.setProperty("auto.offset.reset", "earliest");
		
		KafkaConsumer<String,String> consumer = new KafkaConsumer<String,String>(props);
		consumer.subscribe(Arrays.asList("second_topic"));
		
		while(true) {
			ConsumerRecords<String,String> cRecs = consumer.poll(100);
			
			for(ConsumerRecord<String,String> cRec : cRecs) {
				System.out.println("Partition: "+cRec.partition() +
									"offset: "+cRec.offset() +
									"Key: "+cRec.key() +
									"Value: "+cRec.value()
						);
			}
		}
	}

}
