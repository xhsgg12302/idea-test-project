package netty.hikvisionLED.demo.tools;

import io.netty.channel.Channel;
import netty.hikvisionLED.demo.protocol.LedRequestMessage;

import java.util.HashMap;
import java.util.Map;

import static netty.hikvisionLED.demo.tools.Tool.bytesToInt;
import static netty.hikvisionLED.demo.tools.Tool.bytesToShort;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-26
 * @Desc:
 */
public class DataHelper {

    public static LedRequestMessage getaa(){
        LedRequestMessage sendMsg = new LedRequestMessage();
        sendMsg.setFrameHead(bytesToInt(new byte[]{0x55 ,(byte)0xAA,0x00 ,0x00},0));
        sendMsg.setAddress((byte)0x01);
        sendMsg.setRetain(bytesToShort(new byte[]{0x00 , 0x00},0));
        sendMsg.setOperateCode((byte)0xC5);
        sendMsg.setFrameNum(bytesToShort(new byte[]{0x00 , 0x00},0));
        sendMsg.setAllLength(bytesToInt(new byte[]{0x07 ,0x00 ,0x00 ,0x00},0));
        sendMsg.setRetain2(bytesToShort(new byte[]{0x00 , 0x00},0));
        sendMsg.setDataLength(bytesToInt(new byte[]{0x07 ,0x00 ,0x00 ,0x00},0));
        sendMsg.setData(new byte[]{0x19 ,0x02 ,0x08 ,0x20 ,0x17 ,0x28 ,0x06});
        sendMsg.setFrameTail(bytesToInt(new byte[]{0x00 ,0x00 ,0x0d ,0x0a},0));

        System.out.println("writeAndFlush...");
        return sendMsg;
    }


    public static void main(String[] args) {

        Map<String,Object> result = new HashMap<>();
        Component.connectAndPerform("39.107.88.49",10000,result,getaa(),getaa());
        System.out.println(result.get("msg"));
    }
}
