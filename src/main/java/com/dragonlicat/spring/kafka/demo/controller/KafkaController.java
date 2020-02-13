package com.dragonlicat.spring.kafka.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.dragonlicat.spring.kafka.demo.service.ProducerService;

@RestController
public class KafkaController {

	Logger logger = LoggerFactory.getLogger(KafkaController.class);
	
	@Autowired
	public ProducerService producerService;
	
	@GetMapping("/v1/producer")
	public JSONObject producerMsg() {
		producerService.sendMsg();
		return null;	
	}
}
