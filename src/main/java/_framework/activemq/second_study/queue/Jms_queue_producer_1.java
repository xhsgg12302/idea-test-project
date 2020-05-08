package _framework.activemq.second_study.queue;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-10
 * @Desc:
 */
public class Jms_queue_producer_1 {

    private final static Logger logger = LoggerFactory.getLogger(Jms_queue_producer_1.class);
    private final static String url = "failover:(tcp://localhost:61615)";

    private final static String QUEUE_NAME = "queue01";

    public static void main(String[] args) throws Exception{

        logger.info("1234");

        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(url);

        Connection connection = activeMQConnectionFactory.createConnection();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Queue queue = session.createQueue(QUEUE_NAME);


        MessageProducer producer = session.createProducer(queue);

        //producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

        /*<

            *   生产者 不用 connection.start()好使，，，猜测:有可能在send 的时候连接了
            *   多个消费者时，平等消费，（1,3,5）|(2,4,6)

            1， 生产者生产持久化消息，就是可否将内存中的消息，在外部备份。mq重启后还在。
            2， 生产者开启事务，不提交->(没有消息，只有创建的queue);提交->(出现消息)
            3， 生产者应答模式：几乎没影响

        >_12302_2019-12-11_*/

        for (int i = 0; i < 3; i++) {

            TextMessage textMessage = session.createTextMessage("transaction-false;acknowledge-auto" + QUEUE_NAME + "-" + i);

            producer.send(textMessage);

        }

        //session.commit();
        producer.close();
        session.close();
        connection.close();
    }
}
