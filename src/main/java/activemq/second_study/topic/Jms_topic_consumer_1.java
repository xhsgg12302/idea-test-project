package activemq.second_study.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-10
 * @Desc:
 */
public class Jms_topic_consumer_1 {
    public static void main(String[] args) throws Exception{
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.setClientID("sentry02");
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Topic topic01 = session.createTopic("topic01");

        TopicSubscriber durableSubscriber = session.createDurableSubscriber(topic01, "remark..2");

        /*<

            TOPIC:

                *
                *   topic 有普通订阅，持久化订阅概念

                1,
                2,
                3,


        >_12302_2019-12-11_*/
        durableSubscriber.setMessageListener((message -> {
            TextMessage textMessage = TextMessage.class.cast(message);
            try {
                System.out.println(textMessage.getText());
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }));
    }
}
