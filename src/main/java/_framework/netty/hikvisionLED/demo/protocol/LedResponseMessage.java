package _framework.netty.hikvisionLED.demo.protocol;

import _framework.netty.hikvisionLED.demo.tools.Tool;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class LedResponseMessage {

    /**
     * _12302_2019-08-21
     *
     * 3、校时
     *
     * response: ==========================================================================
     *
     *        帧头(4)
     * 0000   55 aa 00 00
     *
     * 	   地址(1)   保留(2)   操作码(1)	帧序号(2)	数据总长(4)
     *     01        00 00     c5           00 00       00 00 00 00
     *
     * 	   数据(0-512)
     * 	   ...
     *
     * 	   帧尾(4)
     *        00 00 0d 0a
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
     *     地址(1)
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


    public byte[] toByteArray() {

        ByteBuffer buf = ByteBuffer.wrap(new byte[ 18 + this.data.length ] );
        buf.order(ByteOrder.LITTLE_ENDIAN);

        buf.putInt(frameHead);
        buf.put(address);
        buf.putShort(retain);
        buf.put(operateCode);
        buf.putShort(frameNum);
        buf.putInt(allLength);
        buf.put(data);
        buf.putInt(frameTail);

        return buf.array();
    }


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

    public String merge(byte[] bytes,int index,int offset){
        String temp = "";
        int all = index + offset;
        for ( ;index < all; index ++ ){
            temp += Tool.supplement(Integer.toHexString(bytes[index] & 0xff),2);
        }
        return temp;
    }

    @Override
    public String toString() {
        byte[] arrstrs = toByteArray();
        return  "LedResponseMessage{" +
                "frameHead=" + merge(arrstrs,0,4) +
                ", address=" + merge(arrstrs,4,1) +
                ", retain=" + merge(arrstrs,5,2) +
                ", operateCode=" + merge(arrstrs,7,1) +
                ", frameNum=" + merge(arrstrs,8,2) +
                ", allLength=" + merge(arrstrs,10,4) +
                ", data=" + Arrays.toString(data) +
                ", frameTail=" + merge(arrstrs,arrstrs.length-4,4) +
                '}';
    }
}
