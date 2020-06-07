package com.mits4u.transactionsdemo.service;

import com.mits4u.transactionsdemo.service.error.SimulatedCheckedException;
import com.mits4u.transactionsdemo.service.error.SimulatedRuntimeException;
import com.mits4u.transactionsdemo.service.jms.JmsProducer;
import com.mits4u.transactionsdemo.service.jpa.Name;
import com.mits4u.transactionsdemo.service.jpa.NamesDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NameService {

    private Logger log = LoggerFactory.getLogger(NameService.class);

    @Autowired
    private JmsProducer jmsProducer;

    @Autowired
    private NamesDao namesDao;

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void addName__checkedException(String name) throws SimulatedCheckedException {
        jmsProducer.send(name);
//        namesDao.save(Name.builder().name(name).build());
        throw new SimulatedCheckedException("simulated checked exception fail");
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void addName__runtimeException(String name) {
        jmsProducer.send(name);
//        namesDao.save(Name.builder().name(name).build());
        throw new SimulatedRuntimeException("simulated RuntimeException fail");
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT)
    public void happyPath(String name) {
        jmsProducer.send(name);
//        namesDao.save(Name.builder().name(name).build());
        log.info("added to DB and JMS");
    }

}
