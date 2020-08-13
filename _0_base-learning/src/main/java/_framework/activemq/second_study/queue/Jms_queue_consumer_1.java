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
public class Jms_queue_consumer_1 {

    private final static String url = "failover:(tcp://localhost:61615)";

    private final static String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws Exception{

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);

        Connection connection = activeMQConnectionFactory.createConnection();
        connection.start();

        /*<

            * 对于 consumer来说，CLIENT_ACKNOWLEDGE ,如果不手动签收，则默认消息可以重复消费。
            * 对于 producer来说，签收模式采用哪种没什么影响，除了 transaction外，因为这个如果param1 = false,会有异常。


            1,  消费者不可持久化
            2,  消费者开启事务，不提交->(只会使用消息，且可以一直使用，并未消费);提交->(broker 确定消费完成，删除消息)消费者开启事务，不提交->(只会使用消息，且可以一直使用，并未消费);提交->(broker 确定消费完成，删除消息)
            3,  消费者应答模式：
                    AUTO_ACKNOWLEDGE  ,  自动签收，使用消息就算签收了，
                    CLIENT_ACKNOWLEDGE,  手动签收，使用message.acknowledge();



        >_12302_2019-12-11_*/
        Session session = connection.createSession(true, Session.CLIENT_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);

        MessageConsumer consumer = session.createConsumer(queue);


        consumer.setMessageListener((message) ->{
            TextMessage textMessage = TextMessage.class.cast(message);
                try {
                    System.out.println(textMessage.getText());
                    session.commit();
                    //message.acknowledge();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
        });
    }

}
