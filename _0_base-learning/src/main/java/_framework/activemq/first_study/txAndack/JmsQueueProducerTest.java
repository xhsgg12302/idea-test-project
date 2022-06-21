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

public class JmsQueueProducerTest {

    public static final String QUEUE_NAME = "request_queue_69";

    public static final String TOPIC_NAME = "request_topic_69";

    public static final String USER_NAME = "wtfu";

    public static final String PASSWORD = "broker12302";

    public static final String BROKER_URL = "tcp://wtfu.site:13316";


    @Test
    public void testFalseAuto() throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination queue = session.createQueue(QUEUE_NAME);
        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message : testFalseAuto " + i + ": has been produce");
            producer.send(message);
        }
        session.close();
        connection.close();
    }

    @Test
    public void testFalseCli() throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();

        Session session = connection.createSession(false, Session.CLIENT_ACKNOWLEDGE);

        Destination queue = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message : testFalseCli  " + i + ": has been produce");
            producer.send(message);
        }

        session.close();
        connection.close();
    }

    @Test
    public void testTrueAuto() throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();

        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);

        Destination queue = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message : testTrueAuto " + i + ": has been produce");
            producer.send(message);
        }

        session.commit();
        session.close();
        connection.close();
    }

    @Test
    public void testTrueCli() throws JMSException {

        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(USER_NAME, PASSWORD, BROKER_URL);
        Connection connection = factory.createConnection();

        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);

        Destination queue = session.createQueue(QUEUE_NAME);

        MessageProducer producer = session.createProducer(queue);

        for (int i = 0; i < 3; i++) {
            TextMessage message = session.createTextMessage("message : testTrueCli " + i + ": has been produce");
            producer.send(message);
        }

        session.commit();
        session.close();
        connection.close();
    }
}
