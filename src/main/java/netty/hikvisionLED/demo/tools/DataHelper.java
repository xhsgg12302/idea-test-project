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

        //组装数据
        byte[] contents = new byte[0];
        try {
            contents = content.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        //=============================0x0E:字符内码区域=================================================
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
        byte[] characterDatas = characterCodeArea.toByteArray();

        //=============================0x21:时间节目区域=================================================
        DataRepo.TimeProgramArea timeProgramArea = new DataRepo.TimeProgramArea();
        timeProgramArea.setAreaNum((byte)0x02);
        timeProgramArea.setAreaType((byte)0x21);
        timeProgramArea.setAreaCoordinatesC(new byte[]{0x00 ,0x00 ,0x10 ,0x00 ,0x3f ,0x00 ,0x1f ,0x00});
        timeProgramArea.setAreaFontColor((byte)0x04);
        timeProgramArea.setRetain((short)0);
        timeProgramArea.setActionWay((byte)0x01);
        timeProgramArea.setRetain2((byte)0x00);
        timeProgramArea.setSpeed((byte)0x23);
        timeProgramArea.setStayTimePerPage((byte)0xff);
        timeProgramArea.setFontSize((byte)0x10);
        timeProgramArea.setTimeDataLength(15);
        timeProgramArea.setTimeUpdateType((byte)0x73);
        timeProgramArea.setShowType((byte)0x00);
        timeProgramArea.setTimeParams(0);
        timeProgramArea.setContent(new byte[]{0x25 ,0x68 ,0x30 ,0x3a ,0x25 ,0x6d ,0x3a ,0x25 ,0x73});
        timeProgramArea.setAreaDataLength();
        byte[] timeDatas = timeProgramArea.toByteArray();

        //=============================0x01:点阵位图区域=================================================
        DataRepo.BitmapArea bitmapArea = new DataRepo.BitmapArea();
        bitmapArea.setAreaNum((byte)0x03);
        bitmapArea.setAreaType((byte)0x01);
        bitmapArea.setAreaCoordinates(new byte[]{0x00 ,0x00 ,0x20 ,0x00 ,0x3f ,0x00 ,0x3f ,0x00});
        bitmapArea.setAreaPage((short)1);
        bitmapArea.setBitmapInfo(DataRepo.bitmapInfo());
        bitmapArea.setAreaDataLength();
        byte[] bitmapDatas = bitmapArea.toByteArray();

        //=============================0x2D:语音播报数据=================================================
        DataRepo.VoiceBroadcastData voiceBroadcastData = new DataRepo.VoiceBroadcastData();
        voiceBroadcastData.setAreaNum((byte)0x04);
        voiceBroadcastData.setAreaType((byte)0x2d);
        voiceBroadcastData.setRetain(new byte[]{0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00});
        voiceBroadcastData.setPlayWay((byte)0x00);
        voiceBroadcastData.setMultiplePlayIntervals(0);
        voiceBroadcastData.setPlayTimes((byte)0x01);
        voiceBroadcastData.setVoiceLength(3);
        voiceBroadcastData.setContent(new byte[]{(byte)0xbb ,(byte)0xb6 ,(byte)0xd3});
        voiceBroadcastData.setAreaDataLength();
        byte[] voiceBroadcastDatas = voiceBroadcastData.toByteArray();

        //=============================包头=================================================
        DataRepo.ProgramHeader programHeader = new DataRepo.ProgramHeader();
        programHeader.setProgramCount((byte)(0x01));
        programHeader.setProgramNum((byte)(0x01));
        programHeader.setAreaCount((byte)(0x04));
        programHeader.setRetain(new byte[]{0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00 ,0x00});
        programHeader.setPlayTimes((byte)(0x00));
        programHeader.setRetain2((short)0);
        programHeader.setAreaTypeTotalSumLength(characterDatas.length + timeDatas.length + bitmapDatas.length + voiceBroadcastDatas.length + 24);//25 为节目头信息字节数
        byte[] programDatas = programHeader.toByteArray();


        //求节目数据大小
        int programLength = programDatas.length,
            characterLength = characterDatas.length,
            timeLength = timeDatas.length,
            bitmapLength = bitmapDatas.length,
            voiceLength = voiceBroadcastDatas.length,
            totalLength = programLength + characterLength + timeLength + bitmapLength + voiceLength;

        byte[] totalDatas = new byte[totalLength];
        int currentIndex = 0;
        System.arraycopy(programDatas,0,totalDatas,currentIndex,programLength);
        currentIndex += programLength;
        System.arraycopy(characterDatas,0,totalDatas,currentIndex,characterLength);

        currentIndex += characterLength;
        System.arraycopy(timeDatas,0,totalDatas,currentIndex,timeLength);

        currentIndex += timeLength;
        System.arraycopy(bitmapDatas,0,totalDatas,currentIndex,bitmapLength);

        currentIndex += bitmapLength;
        System.arraycopy(voiceBroadcastDatas,0,totalDatas,currentIndex,voiceLength);


        int packageCount = (int)Math.floor(totalLength*100*0.01/512);
        LedRequestMessage[] ledRequestMessages = new LedRequestMessage[packageCount + 1];

        for(int i = 0; i < packageCount;i++){

            byte[] packageContent = new byte[512];
            System.arraycopy(totalDatas,i << 9,packageContent,0,512);

            LedRequestMessage sendMsg = new LedRequestMessage();
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
            sendMsg.setOperateCode(CMD.INSTANT_MESSAGE_COMMAND.getCode());
            /*<帧序号(2)>_12302_2019-08-27_*/
            sendMsg.setFrameNum((short)i);
            /*<数据总长(4)>_12302_2019-08-27_*/
            sendMsg.setAllLength(totalDatas.length);
            /*<数据长度(4)>_12302_2019-08-27_*/
            sendMsg.setDataLength(packageContent.length);
            /*<数据(0-512)>_12302_2019-08-27_*/
            sendMsg.setData(packageContent);

            ledRequestMessages[i] = sendMsg;
        }

        int remainBytes = totalLength - (packageCount << 9);
        byte[] packageContent = new byte[remainBytes];
        System.arraycopy(totalDatas,packageCount << 9,packageContent,0,remainBytes);

        LedRequestMessage sendMsg = new LedRequestMessage();
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
        sendMsg.setOperateCode(CMD.INSTANT_MESSAGE_COMMAND.getCode());
        /*<帧序号(2)>_12302_2019-08-27_*/
        sendMsg.setFrameNum((short)packageCount);
        /*<数据总长(4)>_12302_2019-08-27_*/
        sendMsg.setAllLength(totalDatas.length);
        /*<数据长度(4)>_12302_2019-08-27_*/
        sendMsg.setDataLength(packageContent.length);
        /*<数据(0-512)>_12302_2019-08-27_*/
        sendMsg.setData(packageContent);

        ledRequestMessages[packageCount] = sendMsg;

        System.out.println("<发送显示数据_SEND_DISPLAY_DATA> build data complete!");

        return ledRequestMessages;
    }




    public static void main(String[] args) {

        Component.connectAndPerform("39.107.88.49",10000,sendDisplayData("欢迎光临"));

    }
}
