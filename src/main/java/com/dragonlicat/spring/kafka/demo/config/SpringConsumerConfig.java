/** 
 * Project Name:spring-kafka-demo 
 * File Name:SpringConsumerConfig.java 
 * Package Name:com.dragonlicat.spring.kafka.demo.factory 
 * Date:2018年8月20日下午9:51:54 
 * Copyright (c) 2018, shchsh888@hotmail.com All Rights Reserved. 
 * 
*/

package com.dragonlicat.spring.kafka.demo.config;

import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;

/**
 * ClassName:SpringConsumerConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年8月20日 下午9:51:54 <br/>
 * 
 * @author DANAODAI
 * @version
 * @since JDK 1.8
 * @see
 */

@SpringBootConfiguration
public class SpringConsumerConfig {
	
	Logger logger = LoggerFactory.getLogger(SpringProducerConfig.class);
	
	@Autowired
	private KafkaProperties kafkaProperties;
	
	@Value("${schema.registry.url}")
	String schemaRegsitryUrl;
	

	@Bean
	public ConcurrentKafkaListenerContainerFactory<Integer, String>
                        kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Integer, String> factory =
                                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        
        return factory;
    }

    @Bean
    public ConsumerFactory<Integer, String> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(consumerProps());
    }

	@Bean
	public  Map<String, Object> consumerProps() {
		Map<String, Object> props = kafkaProperties.buildConsumerProperties();
		props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
		props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
		props.put("schema.registry.url", schemaRegsitryUrl);
		return props;
	}
}
