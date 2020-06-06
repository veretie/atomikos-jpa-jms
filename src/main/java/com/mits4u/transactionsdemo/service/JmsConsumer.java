package com.mits4u.transactionsdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class JmsConsumer {

    Logger log = LoggerFactory.getLogger(JmsConsumer.class);

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(String message) {
        log.info("Received message='{}'", message);
    }

}