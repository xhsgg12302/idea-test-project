package _framework.activemq.first_study;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-11-08
 * @Desc:
 */
public class ReplyConsumer {

    public static void main(String[] args) throws Exception{

        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        final MessageConsumer messageConsumer;
        // MessageProducer messageProducer; // 消息消费者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
        connection.setClientID("client-3");
        connection.start(); // 启动连接


        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session

        destination = session.createTopic("sea-third-_draft.test");
        messageConsumer = session.createConsumer(destination); //持久订阅

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage)message;
                try {
                    System.out.println(tm.getText());

                    /*Destination jmsReplyTo = message.getJMSReplyTo();
                    jmsTemplate.send(jmsReplyTo, new MessageCreator() {
                        @Override
                        public Message createMessage(Session session) throws JMSException {
                            ReturnMqMain caculate = caculate(enter);
                            String remain = JSON.toJSONString(caculate);
                            TextMessage textMessage = session.createTextMessage(remain);
                            return textMessage;
                        }
                    });*/
                    Destination jsmReply = message.getJMSReplyTo();
                    MessageProducer messageProducer = session.createProducer(jsmReply);

                    TextMessage msg = session.createTextMessage("success" + tm.getText());
                    messageProducer.send(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
