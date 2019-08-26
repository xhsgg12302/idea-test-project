package netty.hikvisionLED.demo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import netty.hikvisionLED.demo.protocol.LedResponseMessage;

import java.util.List;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class LedRepMessageDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        LedResponseMessage msg = new LedResponseMessage();
        msg.setFrameHead(in.readInt());
        msg.setAddress(in.readByte());
        msg.setRetain(in.readShort());
        msg.setOperateCode(in.readByte());
        msg.setFrameNum(in.readShort());
        int length = in.readInt();
        msg.setAllLength(length);
        byte [] bytes = new byte[length];
        for(int i = 0 ; i < length ; i++){
            bytes[1] = in.readByte();
        }
        msg.setData(bytes);
        msg.setFrameTail(in.readInt());
        out.add(msg);
    }
}
