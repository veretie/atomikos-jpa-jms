package com.mits4u.transactionsdemo;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import com.atomikos.jms.AtomikosConnectionFactoryBean;
import org.apache.activemq.ActiveMQXAConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.broker.BrokerService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;

@Configuration
public class AtomikosConfig {

    @Value("${activemq.broker.url}")
    private String brokerUrl;

    @Bean
    public BrokerService embeddedBroker() throws Exception {
        BrokerService broker = new BrokerService();
        broker.setPersistent(false);
        broker.setUseJmx(true);
        broker.addConnector(brokerUrl);
        return broker;
    }

    // Construct Atomikos UserTransactionManager, needed to configure Spring
    @Bean(initMethod = "init", destroyMethod = "close")
    public UserTransactionManager atomikosTransactionManager() throws SystemException {
        var tm = new UserTransactionManager();
        tm.setTransactionTimeout(300);
        tm.setForceShutdown(true);
        return tm;
    }

    //Also use Atomikos UserTransactionImp, needed to configure Spring
    @Bean
    public UserTransactionImp atomikosUserTransaction() throws SystemException {
        var atomikosUserTransaction = new UserTransactionImp();
        atomikosUserTransaction.setTransactionTimeout(300);
        return atomikosUserTransaction;
    }

    //Configure the Spring framework to use JTA transactions from Atomiko
    @Bean(name = "transactionManager")
    public JtaTransactionManager springJtaTransactionManager() throws SystemException {
        JtaTransactionManager jtaTransactionManager = new JtaTransactionManager();
        jtaTransactionManager.setTransactionManager(atomikosTransactionManager());
        jtaTransactionManager.setUserTransaction(atomikosTransactionManager());
        return jtaTransactionManager;
    }


    /*
        JMS CONFIG
     */

    //The underlying JMS vendor's XA connection factory. XA is required for transactional correctness.
    @Bean
    public ActiveMQXAConnectionFactory actimeMqXaFactory() {
        var xaFactory = new ActiveMQXAConnectionFactory();

        xaFactory.setBrokerURL(brokerUrl);
        var r = new RedeliveryPolicy();
        r.setMaximumRedeliveries(2);
        r.setRedeliveryDelay(5000);
        r.setBackOffMultiplier(1.5);
        xaFactory.setRedeliveryPolicy(r);
        xaFactory.setMaxThreadPoolSize(3);
        return xaFactory;
    }

    //The Atomikos JTA-enabled JmsConnectionFactory, configured with the vendor's XA factory.
    @Bean(initMethod = "init", destroyMethod = "close")
    @DependsOn("embeddedBroker")
    public AtomikosConnectionFactoryBean atomikosConnectionFactory(){
        var factoryBean = new AtomikosConnectionFactoryBean();
        factoryBean.setUniqueResourceName("ACTIVEMQ_BROKER");
        factoryBean.setXaConnectionFactory(actimeMqXaFactory());
        return factoryBean;
    }

    // JMS producer using Atomikos
    @Bean
    public JmsTemplate jmsTemplate() {
         var jmsTemplate = new JmsTemplate();
         jmsTemplate.setConnectionFactory(atomikosConnectionFactory());
         jmsTemplate.setSessionTransacted(true);
         jmsTemplate.setReceiveTimeout(1000);
         return jmsTemplate;
    }

    // JMS consumer using Atomikos
    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() throws SystemException {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(atomikosConnectionFactory());
        factory.setTransactionManager(springJtaTransactionManager());
        factory.setSessionTransacted(true);
        return factory;
    }

     /*
        DB CONFIG - uses Spring "transactionManager" bean which is configured as JtaTransactionManager above
     */

}