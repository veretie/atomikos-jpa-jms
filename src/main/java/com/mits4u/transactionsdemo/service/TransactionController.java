package com.mits4u.transactionsdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@Autowired
	private JmsProducer jmsProducer;

	@GetMapping("demo/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		jmsProducer.send(name);
		return "AA";
	}
}