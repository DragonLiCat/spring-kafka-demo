/** 
 * Project Name:spring-kafka-demo 
 * File Name:ConsumerServiceImpl.java 
 * Package Name:com.dragonlicat.spring.kafka.demo.service.impl 
 * Date:2018年8月20日下午9:53:49 
 * Copyright (c) 2018, shchsh888@hotmail.com All Rights Reserved. 
 * 
*/  
  
package com.dragonlicat.spring.kafka.demo.service.impl;

import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Service;

import com.dragonlicat.spring.kafka.demo.service.ConsumerService;

/** 
 * ClassName:ConsumerServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年8月20日 下午9:53:49 <br/> 
 * @author   DANAODAI
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class ConsumerServiceImpl implements ConsumerService {
	
	Logger logger = LoggerFactory.getLogger(ConsumerServiceImpl.class);
	
	private final CountDownLatch latch1 = new CountDownLatch(1);
	

	@KafkaListener( topicPartitions =
        { 
        	@TopicPartition(topic = "test_schema", 
                    partitionOffsets = @PartitionOffset(partition = "0", initialOffset = "1"))
          
        })
    public void listen(ConsumerRecord<?, ?>  record) {
    	
    	logger.info("topic:{} ; partition:{} ; offset:{}  ;header:{} ; time:{}",record.topic(),record.partition(),record.offset(), record.headers().toString(),record.timestamp());
    	
    	latch1.countDown();
    }

}
  