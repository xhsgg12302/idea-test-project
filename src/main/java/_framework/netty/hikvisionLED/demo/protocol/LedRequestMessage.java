package _framework.netty.hikvisionLED.demo.protocol;

import java.util.Arrays;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class LedRequestMessage {
    /**
     * _12302_2019-08-21
     *
     * 3、校时
     * request：==========================================================================
     *
     * 	   帧头(4)
     * 0000   55 aa 00 00
     *
     * 	   地址(1)   保留(2)   操作码(1)    帧序号(2)    数据总长(4)	   保留(2)
     * 	   01        00 00     c5           00 00        07 00 00 00       00 00
     *
     * 	   数据长度(4)
     *        07 00 00 00
     *
     * 	   数据(0-512)
     * 	   19 02 08 20 17 28 06
     *
     * 	   帧尾(4)
     * 	   00 00 0d 0a
     *
     */

    /**
     * _12302_2019-08-21
     * <p>
     *     帧头(4)
     * </p>
     */
    private int frameHead;
    /**
     * _12302_2019-08-21
     * <p>
     *     地址(1) 地址域为RS485地址，其它通讯方式设为0x01
     * </p>
     */
    private byte address;
    /**
     * _12302_2019-08-21
     * <p>
     *     保留(2)
     * </p>
     */
    private short retain;
    /**
     * _12302_2019-08-21
     * <p>
     *     操作码(1)
     * </p>
     */
    private byte operateCode;
    /**
     * _12302_2019-08-21
     * <p>
     *     帧序号(2)
     * </p>
     */
    private short frameNum;
    /**
     * _12302_2019-08-21
     * <p>
     *     数据总长(4)
     * </p>
     */
    private int allLength;
    /**
     * _12302_2019-08-21
     * <p>
     *     保留(2)
     * </p>
     */
    private short retain2;
    /**
     * _12302_2019-08-21
     * <p>
     *     数据长度(4)
     * </p>
     */
    private int dataLength;
    /**
     * _12302_2019-08-21
     * <p>
     *     数据(0-512)
     * </p>
     */
    private byte[] data;
    /**
     * _12302_2019-08-21
     * <p>
     *     帧尾(4)
     * </p>
     */
    private int frameTail;



    public int getFrameHead() {
        return frameHead;
    }

    public void setFrameHead(int frameHead) {
        this.frameHead = frameHead;
    }

    public byte getAddress() {
        return address;
    }

    public void setAddress(byte address) {
        this.address = address;
    }

    public short getRetain() {
        return retain;
    }

    public void setRetain(short retain) {
        this.retain = retain;
    }

    public byte getOperateCode() {
        return operateCode;
    }

    public void setOperateCode(byte operateCode) {
        this.operateCode = operateCode;
    }

    public short getFrameNum() {
        return frameNum;
    }

    public void setFrameNum(short frameNum) {
        this.frameNum = frameNum;
    }

    public int getAllLength() {
        return allLength;
    }

    public void setAllLength(int allLength) {
        this.allLength = allLength;
    }

    public short getRetain2() {
        return retain2;
    }

    public void setRetain2(short retain2) {
        this.retain2 = retain2;
    }

    public int getDataLength() {
        return dataLength;
    }

    public void setDataLength(int dataLength) {
        this.dataLength = dataLength;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public int getFrameTail() {
        return frameTail;
    }

    public void setFrameTail(int frameTail) {
        this.frameTail = frameTail;
    }

    @Override
    public String toString() {
        return "LedRequestMessage{" +
                "frameHead=" + frameHead +
                ", address=" + address +
                ", retain=" + retain +
                ", operateCode=" + operateCode +
                ", frameNum=" + frameNum +
                ", allLength=" + allLength +
                ", retain2=" + retain2 +
                ", dataLength=" + dataLength +
                ", data=" + Arrays.toString(data) +
                ", frameTail=" + frameTail +
                '}';
    }
}
