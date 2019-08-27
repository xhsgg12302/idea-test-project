package netty.hikvisionLED.demo.tools;

import netty.hikvisionLED.demo.basic.DataRepo;
import netty.hikvisionLED.demo.enums.CMD;
import netty.hikvisionLED.demo.protocol.LedRequestMessage;

import java.io.UnsupportedEncodingException;
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

    /**
     * _12302_2019-08-27
     *
     * 校对时间指令_TIME_CHECK_COMMAND
     *
     */
    public static LedRequestMessage timeCheckCommand(){
        LedRequestMessage sendMsg = new LedRequestMessage();

        byte[] data = DataRepo.getDateConstructor();

        /*<帧头(4)>_12302_2019-08-27_*/
        sendMsg.setFrameHead(bytesToInt(new byte[]{0x55 ,(byte)0xAA,0x00 ,0x00},0));

        /*<地址(1)>_12302_2019-08-27_*/
        sendMsg.setAddress((byte)0x01);

        /*<保留(2)>_12302_2019-08-27_*/
        sendMsg.setRetain(bytesToShort(new byte[]{0x00 , 0x00},0));

        /*<保留(2)>_12302_2019-08-27_*/
        sendMsg.setRetain2(bytesToShort(new byte[]{0x00 , 0x00},0));

        /*<帧尾(4)>_12302_2019-08-27_*/
        sendMsg.setFrameTail(bytesToInt(new byte[]{0x00 ,0x00 ,0x0d ,0x0a},0));

        /*<操作码(1)>_12302_2019-08-27_*/
        sendMsg.setOperateCode(CMD.TIME_CHECK_COMMAND.getCode());

        /*<帧序号(2)>_12302_2019-08-27_*/
        sendMsg.setFrameNum((short)0);

        /*<数据总长(4)>_12302_2019-08-27_*/
        sendMsg.setAllLength(data.length);

        /*<数据长度(4)>_12302_2019-08-27_*/
        sendMsg.setDataLength(data.length);

        /*<数据(0-512)>_12302_2019-08-27_*/
        sendMsg.setData(data);

        System.out.println("<校对时间指令_TIME_CHECK_COMMAND> build data complete!");
        return sendMsg;
    }

    /**
     * _12302_2019-08-27
     *
     * 终止即时信息指令_STOP_INSTANT_MSG_CMD
     *
     */
    public static LedRequestMessage stopInstantMsgCmd(){
        LedRequestMessage sendMsg = new LedRequestMessage();

        /*<帧头(4)>_12302_2019-08-27_*/
        sendMsg.setFrameHead(bytesToInt(new byte[]{0x55 ,(byte)0xAA,0x00 ,0x00},0));

        /*<地址(1)>_12302_2019-08-27_*/
        sendMsg.setAddress((byte)0x01);

        /*<保留(2)>_12302_2019-08-27_*/
        sendMsg.setRetain(bytesToShort(new byte[]{0x00 , 0x00},0));

        /*<保留(2)>_12302_2019-08-27_*/
        sendMsg.setRetain2(bytesToShort(new byte[]{0x01 , 0x01},0));

        /*<帧尾(4)>_12302_2019-08-27_*/
        sendMsg.setFrameTail(bytesToInt(new byte[]{0x00 ,0x00 ,0x0d ,0x0a},0));

        /*<操作码(1)>_12302_2019-08-27_*/
        sendMsg.setOperateCode(CMD.STOP_INSTANT_MSG_CMD.getCode());

        /*<帧序号(2)>_12302_2019-08-27_*/
        sendMsg.setFrameNum((short)0);

        /*<数据总长(4)>_12302_2019-08-27_*/
        sendMsg.setAllLength(1);

        /*<数据长度(4)>_12302_2019-08-27_*/
        sendMsg.setDataLength(2);

        /*<数据(0-512)>_12302_2019-08-27_*/
        sendMsg.setData(new byte[]{ 0x00, 0x00 });

        System.out.println("<终止即时信息指令_STOP_INSTANT_MSG_CMD> build data complete!");
        return sendMsg;
    }

    /**
     * _12302_2019-08-27
     *
     * 即使信息指令_INSTANT_MESSAGE_COMMAND
     *
     */
    public static LedRequestMessage[] sendDisplayData(String content){

        //定义一些全局属性

        LedRequestMessage sendMsg1 = new LedRequestMessage();

        /*<帧头(4)>_12302_2019-08-27_*/
        sendMsg1.setFrameHead(bytesToInt(new byte[]{0x55 ,(byte)0xAA,0x00 ,0x00},0));

        /*<地址(1)>_12302_2019-08-27_*/
        sendMsg1.setAddress((byte)0x01);

        /*<保留(2)>_12302_2019-08-27_*/
        sendMsg1.setRetain(bytesToShort(new byte[]{0x00 , 0x00},0));

        /*<保留(2)>_12302_2019-08-27_*/
        sendMsg1.setRetain2(bytesToShort(new byte[]{0x00 , 0x00},0));

        /*<帧尾(4)>_12302_2019-08-27_*/
        sendMsg1.setFrameTail(bytesToInt(new byte[]{0x00 ,0x00 ,0x0d ,0x0a},0));

        /*<操作码(1)>_12302_2019-08-27_*/
        sendMsg1.setOperateCode(CMD.INSTANT_MESSAGE_COMMAND.getCode());


        //组装数据
        byte[] contents = new byte[0];
        try {
            contents = content.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        DataRepo.CharacterCodeArea characterCodeArea = new DataRepo.CharacterCodeArea();
        characterCodeArea.setAreaNum((byte)0x01);

        characterCodeArea.setAreaType((byte)0x0e);
        characterCodeArea.setAreaCoordinates(new byte[]{0x00 ,0x00 ,0x00 ,0x00 ,0x3f ,0x00 ,0x0f ,0x00});
        characterCodeArea.setAreaFontColor((byte)0x01);
        characterCodeArea.setRetain((short)0);
        characterCodeArea.setActionWay((byte)(0x20));
        characterCodeArea.setRetain2((byte)(0x00));
        characterCodeArea.setSpeed((byte)(0x23));
        characterCodeArea.setStayTimePerPage((byte)(0x05));
        characterCodeArea.setFontSize((byte)(0x10));
        characterCodeArea.setCharLength(contents.length);
        characterCodeArea.setContent(contents);

        characterCodeArea.setAreaDataLength();

        byte[] data = characterCodeArea.toByteArray();

        /*<帧序号(2)>_12302_2019-08-27_*/
        sendMsg1.setFrameNum((short)0);

        /*<数据总长(4)>_12302_2019-08-27_*/
        sendMsg1.setAllLength(data.length);

        /*<数据长度(4)>_12302_2019-08-27_*/
        sendMsg1.setDataLength(data.length);

        /*<数据(0-512)>_12302_2019-08-27_*/
        sendMsg1.setData(data);




        System.out.println("<发送显示数据_SEND_DISPLAY_DATA> build data complete!");

        return new LedRequestMessage[]{sendMsg1};
    }




    public static void main(String[] args) {

        Component.connectAndPerform("39.107.88.49",10000,sendDisplayData("欢迎光临"));

    }
}
