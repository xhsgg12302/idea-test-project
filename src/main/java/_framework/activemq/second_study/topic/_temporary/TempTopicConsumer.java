package _framework.activemq.second_study.topic._temporary;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-20
 * @Desc:
 */
public class TempTopicConsumer {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("sentry02");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic01 = session.createTopic("temp_topic01");

        //TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic01, "remark..2");
        MessageConsumer consumer = session.createConsumer(topic01);

        consumer.setMessageListener(message -> {
            TextMessage textMessage = TextMessage.class.cast(message);
            Destination jmsReplyTo = null;
            try {
                jmsReplyTo = textMessage.getJMSReplyTo();
            } catch (JMSException e) {
                e.printStackTrace();
            }
            try {
                System.out.println(textMessage.getText());
                MessageProducer producer = session.createProducer(jmsReplyTo);
                producer.send(textMessage);
            } catch (JMSException e) {
                e.printStackTrace();
            }
        });
    }
}
