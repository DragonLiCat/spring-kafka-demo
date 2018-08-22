/** 
 * Project Name:spring-kafka-demo 
 * File Name:ProducerServiceImpl.java 
 * Package Name:com.dragonlicat.spring.kafka.demo.service.impl 
 * Date:2018年8月20日下午9:54:11 
 * Copyright (c) 2018, shchsh888@hotmail.com All Rights Reserved. 
 * 
*/  
  
package com.dragonlicat.spring.kafka.demo.service.impl;

import javax.annotation.PostConstruct;
import org.apache.avro.Schema;
import org.apache.avro.Schema.Field;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.dragonlicat.spring.kafka.demo.avro.User;
import com.dragonlicat.spring.kafka.demo.service.ProducerService;


/** 
 * ClassName:ProducerServiceImpl <br/> 
 * Function: TODO ADD FUNCTION. <br/> 
 * Reason:   TODO ADD REASON. <br/> 
 * Date:     2018年8月20日 下午9:54:11 <br/> 
 * @author   DANAODAI
 * @version   
 * @since    JDK 1.8
 * @see       
 */
@Service
public class ProducerServiceImpl implements ProducerService {
  @Autowired
  private User user;
  
  @Value("${kafka.topic}")
  private String topic;
  
  @Autowired
  private KafkaTemplate kafkaTemplate;
  
  

  @SuppressWarnings({"unchecked", "rawtypes"})
  @Override
  @PostConstruct
  public void sendMsg() {
    // TODO Auto-generated method stub
    Schema.Parser parser = new Schema.Parser();
    Schema schema = parser.parse(user.getSchema().toString());
    ProducerRecord record = new ProducerRecord<>(topic, convertAvro(schema));
    kafkaTemplate.send(record);
    kafkaTemplate.flush();
    
  }
  
  
  private GenericRecord convertAvro(Schema schema) {
    
    GenericRecord avRecord= new GenericData.Record(schema);
    avRecord.put("name", "cat");
    avRecord.put("favorite_number", 3);
    avRecord.put("favorite_color", "red");
    return avRecord;
    
  }

  
}
  