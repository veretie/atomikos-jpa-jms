package com.mits4u.transactionsdemo.service;

import com.mits4u.transactionsdemo.service.jms.JmsConsumer;
import com.mits4u.transactionsdemo.service.jms.JmsProducer;
import com.mits4u.transactionsdemo.service.jpa.Name;
import com.mits4u.transactionsdemo.service.jpa.NamesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NameService {

    private Logger log = LoggerFactory.getLogger(JmsConsumer.class);

    @Autowired
    private JmsProducer jmsProducer;

    @Autowired
    private NamesDao namesDao;

    public void addName(String name) {
        jmsProducer.send(name);
        namesDao.save(Name.builder().name(name).build());
        log.info("name={} added", name);
    }

}
