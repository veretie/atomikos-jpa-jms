package com.mits4u.transactionsdemo.service.jms;

import com.mits4u.transactionsdemo.service.error.SimulatedCheckedException;
import com.mits4u.transactionsdemo.service.error.SimulatedRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.ErrorHandler;

@Component
public class AsyncJmsErrorHandler implements ErrorHandler {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void handleError(Throwable e) {
        if (e.getCause() instanceof SimulatedRuntimeException || e.getCause() instanceof SimulatedCheckedException) {
            log.error(e.getCause().getMessage());
        } else {
            log.error(e.getMessage(), e);
        }

    }
}