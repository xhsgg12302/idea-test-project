package _framework.netty.hikvisionLED.demo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import _framework.netty.hikvisionLED.demo.protocol.LedResponseMessage;

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

        msg.setFrameHead(in.readIntLE());
        msg.setAddress(in.readByte());
        msg.setRetain(in.readShortLE());
        msg.setOperateCode(in.readByte());
        msg.setFrameNum(in.readShortLE());
        int length = in.readIntLE();
        msg.setAllLength(length);
        byte [] bytes = new byte[length];
        for(int i = 0 ; i < length ; i++){
            bytes[i] = in.readByte();
        }
        msg.setData(bytes);
        msg.setFrameTail(in.readIntLE());
        out.add(msg);
    }
}
