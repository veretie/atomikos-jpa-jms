package com.mits4u.transactionsdemo.service.jms;

import com.mits4u.transactionsdemo.service.error.SimulatedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class AsyncJmsConsumer {

    private Logger log = LoggerFactory.getLogger(AsyncJmsConsumer.class);

    @JmsListener(destination = "${activemq.queue.name}")
    public void receive(String message) {
        log.info("Trying to consumed JMS message='{}'", message);
        throw new SimulatedRuntimeException("simulated consumption failure");
    }

}