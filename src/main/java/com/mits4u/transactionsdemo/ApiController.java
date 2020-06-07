package com.mits4u.transactionsdemo;

import com.mits4u.transactionsdemo.service.NameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private NameService nameService;

    @GetMapping("demo/success")
    public void greeting(@RequestParam(value = "name") String name) {
        nameService.addName(name);
    }
}