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
        String temp = "{\n" +
                "    \"data\":[\n" +
                "        {\n" +
                "            \"inOperatorTime\":1571219682000,\n" +
                "            \"parkingLotSeq\":\"69\",\n" +
                "            \"cardTypeName\":\"临时车\",\n" +
                "            \"discountAmount\":0,\n" +
                "            \"recordNum\":\"634086832493301760\",\n" +
                "            \"stoppingTime\":\"0小时20分钟\",\n" +
                "            \"outOperatorTime\":157122094647,\n" +
                "            \"carType\":1,\n" +
               // "            \"leaveCarLicensePicture\":\"https://wtfu12302.github.io/images/1560836610128.png\",\n" +
                "            \"correctCarNum\":\"否\",\n" +
                "            \"leaveGate\":\"出口\",\n" +
                "            \"actualReceivable\":0.5,\n" +
                "            \"leaveTime\":1571219682000,\n" +
                "            \"enterTime\":1571219582000,\n" +
                "            \"carParkName\":\"西直门内北顺城街11号院\",\n" +
                "            \"outOperatorAccout\":\"222222\",\n" +
                "            \"amountReceivable\":0.5,\n" +
                "            \"cardType\":1,\n" +
                "            \"carNum\":\"甘B12305\",\n" +
                "            \"leaveChannel\":\"1\",\n" +
                "            \"totalAmount\":0.5\n" +
                "        }\n" +
                "    ],\n" +
                "    \"sign\":\"76FCFFE873D62F729361A0FD3D14C2BD\",\n" +
                "    \"messageId\":\"6590178108844933120\",\n" +
                "    \"command\":\"outStatus5Order\",\n" +
                "    \"timestamp\":\"2019-10-16 18:00:15\"\n" +
                "}";
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
