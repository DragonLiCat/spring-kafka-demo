/** 
 * Project Name:spring-kafka-demo 
 * File Name:SpringProducerConfig.java 
 * Package Name:com.dragonlicat.spring.kafka.demo.factory 
 * Date:2018年8月20日下午9:51:39 
 * Copyright (c) 2018, shchsh888@hotmail.com All Rights Reserved. 
 * 
*/

package com.dragonlicat.spring.kafka.demo.config;

import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * ClassName:SpringProducerConfig <br/>
 * Function: TODO ADD FUNCTION. <br/>
 * Reason: TODO ADD REASON. <br/>
 * Date: 2018年8月20日 下午9:51:39 <br/>
 * 
 * @author DANAODAI
 * @version
 * @since JDK 1.8
 * @see
 */
@Configuration
public class SpringProducerConfig {

	Logger logger = LoggerFactory.getLogger(SpringProducerConfig.class);

	@Autowired
	private KafkaProperties kafkaProperties;

	@Value("${schema.registry.url}")
	String schemaRegsitryUrl;

	@Bean
	public ProducerFactory producerFactory() {
		return new DefaultKafkaProducerFactory<>(producerConfigs());
	}

	@Bean
	public Map<String, Object> producerConfigs() {
		Map<String, Object> props = kafkaProperties.buildProducerProperties();

		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
				io.confluent.kafka.serializers.KafkaAvroSerializer.class);
		// See https://kafka.apache.org/documentation/#producerconfigs for more
		// properties
		props.put("schema.registry.url", schemaRegsitryUrl);

		return props;
	}

	@Bean
	public KafkaTemplate kafkaTemplate() {
		return new KafkaTemplate(producerFactory());
	}
}
