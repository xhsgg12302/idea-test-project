package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/1/7 11:49
 * @Description:
 */
public class ReplyTo {
    public static void main(String [] args) throws Exception{

        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session sessionToClose =null; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        //connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://111.198.53.224:61616");

        connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
        connection.start(); // 启动连接
        sessionToClose=connection.createSession(false,Session.AUTO_ACKNOWLEDGE);


        //生产者
        Topic topic1 = sessionToClose.createTopic("testTopic");
        MessageProducer producer = sessionToClose.createProducer(topic1);

        //消费者
        Destination destination2 = sessionToClose.createTemporaryTopic();
        MessageConsumer consumer = sessionToClose.createConsumer(destination2);


        //消息内容
        TextMessage textMessage = sessionToClose.createTextMessage("TextMessage:Andy");

        textMessage.setJMSReplyTo(destination2);
        producer.send(textMessage);

        Message msgBack = consumer.receive(10000);

        System.out.println(new Date());

//        String result ="";
//        if((msgBack instanceof  TextMessage)){
//            try {
//                TextMessage replyTextMessage = (TextMessage) msgBack;
////                    System.out.println("===============消息生产端得到的返回消息replyTextMessage.toString()= " + replyTextMessage.toString());
//                result=replyTextMessage.getText();
//            }catch (JMSException e){}
//        }
//
//        System.out.println(result);
    }
}
