package netty.hikvisionLED.demo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.hikvisionLED.demo.protocol.LedRequestMessage;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class LedReqMessageEncoder extends MessageToByteEncoder<LedRequestMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, LedRequestMessage msg, ByteBuf out) throws Exception {

        System.out.println("encode codec...");

        out.writeInt(msg.getFrameHead());
        out.writeByte(msg.getAddress());
        out.writeShort(msg.getRetain());
        out.writeByte(msg.getOperateCode());
        out.writeShort(msg.getFrameNum());
        out.writeInt(msg.getAllLength());
        out.writeShort(msg.getRetain2());
        out.writeInt(msg.getDataLength());
        out.writeBytes(msg.getData());
        out.writeInt(msg.getFrameTail());
    }
}
