package activemq;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2018/12/14 11:31
 * @Description:
 */
public class JmsProducer3 {

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageProducer messageProducer; // 消息生产者

        // 实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        //connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://192.168.1.2:61616");

        connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
        connection.start(); // 启动连接


        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session

        //destination = session.createTopic("response_anft");
        destination = session.createTopic("request_anft");

        messageProducer = session.createProducer(destination); // 创建消息生产者

        //messageProducer.setDeliveryMode(DeliveryMode.PERSISTENT);

        //ReturnMqMain returnMqMain = new ReturnMqMain();
        //String command, EnumCarOwnerPaySyncStatus syncSuccess, String messageId, String remark

        /*String sign = Md5Test.MD5Encode(MQEnum.MQMD5EncodeOriginEnum.ORIGIN_anft.getCode() + DateTimeUtil.getSignTime(), "utf-8").toUpperCase();
        MqMain mqMain = new MqMain();
        mqMain.setSign(sign);
        mqMain.setCommand("syncCoupon");
        mqMain.setMessageId("112222");
        mqMain.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        mqMain.setData(obtainData());
        String msg = JSON.toJSONString(mqMain);*/
        String temp = "";
        //String temp = "{\"command\":\"upCarSpaces\",\"data\":[{\"totalParkingSpace\":250,\"areaName\":\"\",\"parkingLotSeq\":\"69\",\"totalNormalParkingSpace\":\"\",\"totalFixedVipParkingSpace\":\"\",\"totalRemainingParkingSpace\":241,\"fixedVipParkingSpaceRemaining\":\"\",\"totalNormalParkingSpaceRemaining\":\"\",\"type\":1}],\"messageId\":\"6562870326156460032\",\"sign\":\"D61642D40A05705010406728863E391B\",\"timestamp\":\"1564710217992\"}";
        /*ReturnMqMain returnMqMain = new ReturnMqMain();
        returnMqMain.setCommand("upCarSpaces");
        returnMqMain.setMsg("成功");
        returnMqMain.setCode("0");
        returnMqMain.setMessageId("921844571271467008");
        String temp = JSON.toJSONString(returnMqMain);*/
        for (int i = 0; i < 1; i++) {
            TextMessage message = session.createTextMessage(temp);
            messageProducer.send(message);
        }
        connection.close();
    }
}
