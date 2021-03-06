package com.mits4u.transactionsdemo.service.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    private Logger log = LoggerFactory.getLogger(JmsProducer.class);

    @Autowired
    private JmsTemplate jmsTemplate;

    @Value("${activemq.queue.name}")
    private String destination;

    public void send(String message) {
        jmsTemplate.convertAndSend(destination, message);
        log.info("Sent JMS message='{}'", message);
    }
}