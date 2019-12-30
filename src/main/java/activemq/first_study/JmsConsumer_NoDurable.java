package activemq.first_study;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2018/12/14 11:40
 * @Description:
 */
public class JmsConsumer_NoDurable {
    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageConsumer messageConsumer;
        // MessageProducer messageProducer; // 消息消费者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
        connection.setClientID("client-3");
        connection.start(); // 启动连接


        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session

        destination = session.createTopic("request_anft");
        messageConsumer = session.createConsumer(destination); //持久订阅

        messageConsumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage tm = (TextMessage)message;
                try {
                    /*MessageProducer producer = session.createProducer(message.getJMSReplyTo());
                    ReturnMqMain returnMqMain = new ReturnMqMain();
                    returnMqMain.setCommand("gateOpen");
                    returnMqMain.setCode("1");
                    returnMqMain.setMessageId("848010007571206142");
                    returnMqMain.setMsg("成功");
                    returnMqMain.setData(new HashMap(){{put("imgUrl","http://39.107.88.49:13320/group1/M00/00/00/rBHZsFvyuYSAX0pCAAO2P6lJIhg650.jpg");}});
                    String msg = JSON.toJSONString(returnMqMain);
                    TextMessage textMessage = session.createTextMessage(msg);
                    producer.send(textMessage);*/
                    System.out.println(tm.getText());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
