package activemq.second_study.topic._temporary;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-20
 * @Desc:
 */
public class TempTopicProducer {

    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(ActiveMQConnectionFactory.DEFAULT_BROKER_BIND_URL);
        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);


        Topic topic01 = session.createTopic("temp_topic01");
        TemporaryTopic temporaryTopic = session.createTemporaryTopic();


        MessageProducer producer = session.createProducer(topic01);
        MessageConsumer consumer = session.createConsumer(temporaryTopic);

        producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
        connection.start();

        for (int i = 0; i < 1; i++) {
            TextMessage textMessage = session.createTextMessage("client-temp_topic-" + topic01 + "-" + i);
            textMessage.setJMSReplyTo(temporaryTopic);
            producer.send(textMessage);
        }

        //0.一直连接
        //1.超时连接


        /*consumer.setMessageListener((Message message)->{
            if(message instanceof TextMessage){
                TextMessage textMessage = TextMessage.class.cast(message);
                try {
                    System.out.println(textMessage.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });*/
        Message receive = consumer.receive(100000);
        if(receive instanceof TextMessage){
            TextMessage reply = TextMessage.class.cast(receive);
            System.out.println(reply);
        }


        //session.commit();
        producer.close();
        consumer.close();
        session.close();
        connection.close();

    }
}
