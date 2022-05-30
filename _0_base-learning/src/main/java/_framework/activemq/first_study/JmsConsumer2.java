package _framework.activemq.first_study;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2018/12/14 11:40
 * @Description:
 */
public class JmsConsumer2 {
    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
       // MessageProducer messageProducer; // 消息消费者
        MessageConsumer messageConsumer;

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);

        connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
        connection.setClientID("client-2");
        connection.start(); // 启动连接


        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session

        destination = session.createTopic("69");
        messageConsumer = session.createDurableSubscriber((Topic)destination,"name_2"); //持久订阅
        //messageConsumer = session.createConsumer(destination); // 创建消息消费者
        /*int i = 0;
        while(i<2){
           TextMessage message = (TextMessage)messageConsumer.receive();
           System.out.println(message.getText());
           i++;
        }*/
        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage)message;
                try {
                    System.out.println(tm.getText());
                } catch (JMSException e) {
                }
            }
        });
    }
}
