package _framework.activemq.second_study.queue;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-10
 * @Desc:
 */
public class Jms_queue_consumer_2 {

    private final static String url = "tcp://127.0.0.1:61616";

    private final static String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);

        consumer.setMessageListener((message) ->{
            TextMessage textMessage = TextMessage.class.cast(message);
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        });
    }

}
