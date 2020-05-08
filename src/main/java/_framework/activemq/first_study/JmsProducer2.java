package _framework.activemq.first_study;

import com.alibaba.fastjson.JSON;
import _draft.entity.MqMain;
import org.apache.activemq.ActiveMQConnectionFactory;
import _utils.utils.DateTimeUtil;
import _utils.utils.MQEnum;
import _utils.utils.Md5Test;

import javax.jms.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2018/12/14 11:31
 * @Description:
 */
public class JmsProducer2 {

    public static void main(String[] args) throws Exception {

        ConnectionFactory connectionFactory; // 连接工厂
        Connection connection = null; // 连接
        Session session; // 会话 接受或者发送消息的线程
        Destination destination; // 消息的目的地
        MessageProducer messageProducer; // 消息生产者

        // 实例化连接工厂
        //connectionFactory = new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_USER, ActiveMQConnection.DEFAULT_PASSWORD, ActiveMQConnection.DEFAULT_BROKER_URL);
        connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");

        connection = connectionFactory.createConnection(); // 通过连接工厂获取连接
        connection.start(); // 启动连接


        session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE); // 创建Session

        //destination = session.createTopic("response_anft");
        destination = session.createTopic("69");

        messageProducer = session.createProducer(destination); // 创建消息生产者

        /**
         * id   String 是 id
         * orderId		String	是	订单编号
         * carNumber		String	是	车牌号码
         * startTime		LocalDateTime	是	开始时间
         * endTime		LocalDateTime	是	结束时间
         * preferentialModel		Integer	是	优惠模式	分钟
         * operationCode		Integer	是	操作码类型	1、增加车辆信息 2、删除车辆信息 3、更新车辆信息
         *
         */

        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("unique_identify","1234567890");
        /*dataMap.put("id",System.currentTimeMillis());
        dataMap.put("couponCode",String.valueOf(IdGenerator.getInstance(1,2).getId()));
        dataMap.put("orderId", "639780826019794940");
        dataMap.put("carNumber","甘K00001");//2019-11-01 01:18:22
        dataMap.put("startTime", LocalDateTime.of(2019,11,01,9,25,04));
        dataMap.put("endTime",LocalDateTime.of(2019,11,01,12,30,59));
        dataMap.put("preferentialModel",13);
        dataMap.put("operationCode",3);
        dataMap.put("clazz",4);*/


        String sign = Md5Test.MD5Encode(MQEnum.MQMD5EncodeOriginEnum.ORIGIN_anft.getCode() + DateTimeUtil.getSignTime(), "utf-8").toUpperCase();
        MqMain mqMain = new MqMain();
        mqMain.setSign(sign);
        mqMain.setCommand("inScanRecord");
        mqMain.setMessageId("112222");
        mqMain.setTimestamp(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        mqMain.setData(dataMap);
        String msg = JSON.toJSONString(mqMain);
        for (int i = 0; i < 1; i++) {
            TextMessage message = session.createTextMessage(msg);
            messageProducer.send(message);
        }
        connection.close();
    }
}
