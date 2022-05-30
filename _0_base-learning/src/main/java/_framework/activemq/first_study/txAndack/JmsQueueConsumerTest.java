package _framework.activemq.first_study.txAndack;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

import javax.jms.*;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-07
 */

public class JmsQueueConsumerTest {

    public static final String QUEUE_NAME = "request_queue_69";

    public static final String USER_NAME = "wtfu";

    public static final String PASSWORD = "broker12302";

    public static final String BROKER_URL = "tcp://wtfu.site:13316";


    @Test
    public void testFalseAuto() throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            if(message instanceof TextMessage){
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        keepRunning(Thread.currentThread(), 5);
    }

    /**
     * 需要ack
     * @throws Exception
     */
    @Test
    public void testFalseCli() throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);
        Destination queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            if(message instanceof TextMessage){
                try {
                    System.out.println(((TextMessage) message).getText());
                    message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        keepRunning(Thread.currentThread(), 0);
    }

    @Test
    public void testTrueAuto() throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Destination queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            if(message instanceof TextMessage){
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //上面的处理方式不行

        /*Message receive = consumer.receive();
        while(null !=  receive){

            try {
                System.out.println(((TextMessage) receive).getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }

            receive = consumer.receive(5 * 1000L);
        }

        session.commit();
        session.close();
        connection.close();*/
        keepRunning(Thread.currentThread(), 0);
    }

    @Test
    public void testTrueCli() throws Exception {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);
        Destination queue = session.createQueue(QUEUE_NAME);
        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener(message -> {
            if(message instanceof TextMessage){
                try {
                    System.out.println(((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });
        keepRunning(Thread.currentThread(), 5);
    }





    private void  keepRunning(Thread thread, int sec) throws InterruptedException {
        synchronized (thread){
            thread.wait(sec *  1000);
        }
    }
}

