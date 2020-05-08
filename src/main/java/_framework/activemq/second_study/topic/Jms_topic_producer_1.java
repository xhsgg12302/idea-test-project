package _framework.activemq.second_study.topic;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-10
 * @Desc:
 */
public class Jms_topic_producer_1 {
    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Topic topic01 = session.createTopic("topic01");

        MessageProducer producer = session.createProducer(topic01);


        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        //connection.start();

        /*<
            TOPIC:

                *
                *   delivery : 指的是mq重启后消息丢不丢失
                *   PERSISTENT : 会为持久订阅保存，重启丢失
                *   NON_PERSISTENT: 不会保存，重启丢失
                *
                *   对于topic来说，有持久订阅，默认是持久化的（3，7）——发送——（5，9）—— 重启——（5，9）
                *

                1,  生产者生产持久化消息，如上注释
                2,  生产者开启事务，不提交->(没有消息，只有创建的topic);提交->(出现消息)
                3,  生产者应答模式：无效

        >_12302_2019-12-11_*/
        for (int i = 0; i < 2; i++) {

            TextMessage textMessage = session.createTextMessage("client-topic-" + topic01 + "-" + i);
            producer.send(textMessage);

        }
        //session.commit();
        producer.close();
        session.close();
        connection.close();

    }
}
