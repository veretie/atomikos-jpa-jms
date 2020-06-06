package com.mits4u.transactionsdemo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TransactionController {

	@GetMapping("demo/greeting")
	public String greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
		return "AA";
	}
}