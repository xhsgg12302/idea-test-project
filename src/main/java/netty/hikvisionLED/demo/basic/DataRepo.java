package netty.hikvisionLED.demo.basic;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Calendar;

import static netty.hikvisionLED.demo.tools.Tool.intAnd0xtoHex;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-27
 * @Desc:
 */
public class DataRepo {

    /*<>_12302_2019-08-27_*/
    public static byte[] getDateConstructor(){
        Calendar now = Calendar.getInstance();
        byte[] bytes = new byte[]{
                (byte)intAnd0xtoHex(now.get(Calendar.YEAR) - 2000),
                (byte)intAnd0xtoHex(now.get(Calendar.DAY_OF_WEEK) - 1),
                (byte)intAnd0xtoHex(now.get(Calendar.MONTH) + 1),
                (byte)intAnd0xtoHex(now.get(Calendar.DAY_OF_MONTH) - 0),
                (byte)intAnd0xtoHex(now.get(Calendar.HOUR_OF_DAY) - 0),
                (byte)intAnd0xtoHex(now.get(Calendar.MINUTE) - 0),
                (byte)intAnd0xtoHex(now.get(Calendar.SECOND) - 0)
        };
        return bytes;
    }

    public static class CharacterCodeArea{

        /**
         * 01 --区域号(1)
         */
        private byte areaNum;

        /**
         * 22 00 00 00 --区域数据大小(4) #00000022 34 （01-->d9）共34字节
         */
        private int areaDataLength;

        /**
         *0e --区域数据类型(1)
         */
        private byte areaType;

        /**
         *00 00 00 00 3f 00 0f 00 --区域坐标(8) 6,7,8,9左上(x,y) 10,11,12,13右下(x,y)
         */
        private byte[] areaCoordinates;

        /**
         *01 --区域字体颜色(1)
         */
        private byte areaFontColor;

        /**
         *00 00--保留(2)
         */
        private short retain;

        /**
         *20 --动作方式(1)
         */
        private byte actionWay;

        /**
         *00 --保留(1)
         */
        private byte retain2;

        /**
         *23 --速度1-255(1)
         */
        private byte speed;

        /**
         *05 --每页停留时间s(1)
         */
        private byte stayTimePerPage;

        /**
         *10 --字体大小(1) 0x10:16*16;0x20:32*32
         */
        private byte fontSize;

        /**
         *08 00 00 00 --字符串长度(4)   #00000008
         */
        private int charLength;

        /**
         *bb b6 d3 ad b9 e2 c1 d9 --显示字符串(8)
         */
        private byte[] content;

        public byte[] toByteArray() {
            ByteBuffer buf = ByteBuffer.wrap(new byte[6 + this.areaCoordinates.length + 12 + this.content.length]);
            buf.order(ByteOrder.LITTLE_ENDIAN);

            buf.put(areaNum);
            buf.putInt(areaDataLength);
            buf.put(areaType);
            buf.put(areaCoordinates);
            buf.put(areaFontColor);
            buf.putShort(retain);
            buf.put(actionWay);
            buf.put(retain2);
            buf.put(speed);
            buf.put(stayTimePerPage);
            buf.put(fontSize);
            buf.putInt(charLength);
            buf.put(content);

            return buf.array();
        }


        public byte getAreaNum() {
            return areaNum;
        }

        public void setAreaNum(byte areaNum) {
            this.areaNum = areaNum;
        }

        public int getAreaDataLength() {
            return areaDataLength;
        }

        public void setAreaDataLength() {
            this.areaDataLength = 6 + this.areaCoordinates.length + 12 + this.content.length;
        }

        public byte getAreaType() {
            return areaType;
        }

        public void setAreaType(byte areaType) {
            this.areaType = areaType;
        }

        public byte[] getAreaCoordinates() {
            return areaCoordinates;
        }

        public void setAreaCoordinates(byte[] areaCoordinates) {
            this.areaCoordinates = areaCoordinates;
        }

        public byte getAreaFontColor() {
            return areaFontColor;
        }

        public void setAreaFontColor(byte areaFontColor) {
            this.areaFontColor = areaFontColor;
        }

        public short getRetain() {
            return retain;
        }

        public void setRetain(short retain) {
            this.retain = retain;
        }

        public byte getActionWay() {
            return actionWay;
        }

        public void setActionWay(byte actionWay) {
            this.actionWay = actionWay;
        }

        public byte getRetain2() {
            return retain2;
        }

        public void setRetain2(byte retain2) {
            this.retain2 = retain2;
        }

        public byte getSpeed() {
            return speed;
        }

        public void setSpeed(byte speed) {
            this.speed = speed;
        }

        public byte getStayTimePerPage() {
            return stayTimePerPage;
        }

        public void setStayTimePerPage(byte stayTimePerPage) {
            this.stayTimePerPage = stayTimePerPage;
        }

        public byte getFontSize() {
            return fontSize;
        }

        public void setFontSize(byte fontSize) {
            this.fontSize = fontSize;
        }

        public int getCharLength() {
            return charLength;
        }

        public void setCharLength(int charLength) {
            this.charLength = charLength;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }

    }

    private class TimeProgramArea{

        /**
         * 01 --区域号(1)
         */
        private byte areaNum;

        /**
         * 22 00 00 00 --区域数据大小(4) #00000022 34 （01-->d9）共34字节
         */
        private int areaDataLength;

        /**
         *0e --区域数据类型(1)
         */
        private byte areaType;

        /**
         *00 00 00 00 3f 00 0f 00 --区域坐标(8) 6,7,8,9左上(x,y) 10,11,12,13右下(x,y)
         */
        private byte[] areaCoordinates;

        /**
         *01 --区域字体颜色(1)
         */
        private byte areaFontColor;

        /**
         *00 00--保留(2)
         */
        private short retain;

        /**
         *20 --动作方式(1)
         */
        private byte actionWay;

        /**
         *00 --保留(1)
         */
        private byte retain2;

        /**
         *23 --速度1-255(1)
         */
        private byte speed;

        /**
         *05 --每页停留时间s(1)
         */
        private byte stayTimePerPage;

        /**
         *10 --字体大小(1) 0x10:16*16;0x20:32*32
         */
        private byte fontSize;

        /**
         *0f 00 00 00 --时间数据长度(4) 从时间更新类型到时间数据格式字符
         */
        private int timeDataLength;

        /**
         *73 --时间更新的类型(1)
         */
        private byte timeUpdateType;

        /**
         * 00 --显示类型(1) {0x00:日历时间(28->31),0x01:倒计时(28->31)}
         */
        private byte showType;

        /**
         * 00 00 00 00 --时间参数(4) --32
         */
        private int timeParams;

        /**
         * 25 68 30 3a 25 6d 3a 25 73 --内容(9) (%h0:%m:%s)
         */
        private byte[] content;


        public byte getAreaNum() {
            return areaNum;
        }

        public void setAreaNum(byte areaNum) {
            this.areaNum = areaNum;
        }

        public int getAreaDataLength() {
            int length = 6 + this.areaCoordinates.length + 8 + 4 + 6 + this.content.length;
            return length;
        }

        public void setAreaDataLength(int areaDataLength) {
            this.areaDataLength = areaDataLength;
        }

        public byte getAreaType() {
            return areaType;
        }

        public void setAreaType(byte areaType) {
            this.areaType = areaType;
        }

        public byte[] getAreaCoordinatesC() {
            return areaCoordinates;
        }

        public void setAreaCoordinatesC(byte[] areaCoordinates) {
            this.areaCoordinates = areaCoordinates;
        }

        public byte getAreaFontColor() {
            return areaFontColor;
        }

        public void setAreaFontColor(byte areaFontColor) {
            this.areaFontColor = areaFontColor;
        }

        public short getRetain() {
            return retain;
        }

        public void setRetain(short retain) {
            this.retain = retain;
        }

        public byte getActionWay() {
            return actionWay;
        }

        public void setActionWay(byte actionWay) {
            this.actionWay = actionWay;
        }

        public byte getRetain2() {
            return retain2;
        }

        public void setRetain2(byte retain2) {
            this.retain2 = retain2;
        }

        public byte getSpeed() {
            return speed;
        }

        public void setSpeed(byte speed) {
            this.speed = speed;
        }

        public byte getStayTimePerPage() {
            return stayTimePerPage;
        }

        public void setStayTimePerPage(byte stayTimePerPage) {
            this.stayTimePerPage = stayTimePerPage;
        }

        public byte getFontSize() {
            return fontSize;
        }

        public void setFontSize(byte fontSize) {
            this.fontSize = fontSize;
        }

        public int getTimeDataLength() {
            return timeDataLength;
        }

        public void setTimeDataLength(int timeDataLength) {
            this.timeDataLength = timeDataLength;
        }

        public byte getTimeUpdateType() {
            return timeUpdateType;
        }

        public void setTimeUpdateType(byte timeUpdateType) {
            this.timeUpdateType = timeUpdateType;
        }

        public byte getShowType() {
            return showType;
        }

        public void setShowType(byte showType) {
            this.showType = showType;
        }

        public int getTimeParams() {
            return timeParams;
        }

        public void setTimeParams(int timeParams) {
            this.timeParams = timeParams;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }
    }

    private class BitmapArea{
        /**
         * 01 --区域号(1)
         */
        private byte areaNum;

        /**
         * 22 00 00 00 --区域数据大小(4) #00000022 34 （01-->d9）共34字节
         */
        private int areaDataLength;

        /**
         *0e --区域数据类型(1)
         */
        private byte areaType;

        /**
         *00 00 00 00 3f 00 0f 00 --区域坐标(8) 6,7,8,9左上(x,y) 10,11,12,13右下(x,y)
         */
        private byte[] areaCoordinates;

        /**
         *01 00 --区域页数(2)
         */
        private short areaPage;

        /**
         *01 ff 0a ff --第一页位图信息(4)
         */
        private byte[] bitmapInfo;


        public byte getAreaNum() {
            return areaNum;
        }

        public void setAreaNum(byte areaNum) {
            this.areaNum = areaNum;
        }

        public int getAreaDataLength() {
            int length = 6 + this.areaCoordinates.length + 2 + this.bitmapInfo.length;
            return length;
        }

        public void setAreaDataLength(int areaDataLength) {
            this.areaDataLength = areaDataLength;
        }

        public byte getAreaType() {
            return areaType;
        }

        public void setAreaType(byte areaType) {
            this.areaType = areaType;
        }

        public byte[] getAreaCoordinates() {
            return areaCoordinates;
        }

        public void setAreaCoordinates(byte[] areaCoordinates) {
            this.areaCoordinates = areaCoordinates;
        }

        public short getAreaPage() {
            return areaPage;
        }

        public void setAreaPage(short areaPage) {
            this.areaPage = areaPage;
        }

        public byte[] getBitmapInfo() {
            return bitmapInfo;
        }

        public void setBitmapInfo(byte[] bitmapInfo) {
            this.bitmapInfo = bitmapInfo;
        }
    }

    private class VoiceBroadcastData{
        /**
         * 01 --区域号(1)
         */
        private byte areaNum;

        /**
         * 22 00 00 00 --区域数据大小(4) #00000022 34 （01-->d9）共34字节
         */
        private int areaDataLength;

        /**
         *0e --区域数据类型(1)
         */
        private byte areaType;

        /**
         *00 00 00 00 00 00 00 00 00 00 --保留(10)
         */
        private byte[] retain;

        /**
         *00 --播放控制方式
         */
        private byte playWay;

        /**
         *00 00 00 00 --多次播放间隔时间
         */
        private int multiplePlayIntervals;

        /**
         *01 --循环播放次数
         */
        private byte playTimes;

        /**
         *03 00 00 00 --语音字符串长度
         */
        private int voiceLength;

        /**
         *bb b6 d3 --语音播放字符串数据（gbk）(欢_)
         */
        private byte[] content;


        public byte getAreaNum() {
            return areaNum;
        }

        public void setAreaNum(byte areaNum) {
            this.areaNum = areaNum;
        }

        public int getAreaDataLength() {
            int length = 6 + this.retain.length + 10 + this.content.length;
            return length;
        }

        public byte getAreaType() {
            return areaType;
        }

        public void setAreaType(byte areaType) {
            this.areaType = areaType;
        }

        public byte[] getRetain() {
            return retain;
        }

        public void setRetain(byte[] retain) {
            this.retain = retain;
        }

        public byte getPlayWay() {
            return playWay;
        }

        public void setPlayWay(byte playWay) {
            this.playWay = playWay;
        }

        public int getMultiplePlayIntervals() {
            return multiplePlayIntervals;
        }

        public void setMultiplePlayIntervals(int multiplePlayIntervals) {
            this.multiplePlayIntervals = multiplePlayIntervals;
        }

        public byte getPlayTimes() {
            return playTimes;
        }

        public void setPlayTimes(byte playTimes) {
            this.playTimes = playTimes;
        }

        public int getVoiceLength() {
            return voiceLength;
        }

        public void setVoiceLength(int voiceLength) {
            this.voiceLength = voiceLength;
        }

        public byte[] getContent() {
            return content;
        }

        public void setContent(byte[] content) {
            this.content = content;
        }
    }

    public static void main(String[] args) {
        byte [] bytes = getDateConstructor();
        for (byte aByte : bytes) {
            System.out.println(Integer.toHexString((int)aByte));
        }
    }
}
