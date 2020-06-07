package com.mits4u.transactionsdemo;

import com.mits4u.transactionsdemo.service.NameService;
import com.mits4u.transactionsdemo.service.error.SimulatedCheckedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    @Autowired
    private NameService nameService;

    @GetMapping("demo/cExc")
    public void cExc(@RequestParam(value = "name") String name) throws SimulatedCheckedException {
        nameService.addName__checkedException(name);
    }

    @GetMapping("demo/rExc")
    public void rExc(@RequestParam(value = "name") String name) {
        nameService.addName__runtimeException(name);
    }

    @GetMapping("demo/sucess")
    public void sucess(@RequestParam(value = "name") String name) {
        nameService.happyPath(name);
    }
}