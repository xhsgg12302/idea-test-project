package netty.hikvisionLED.demo.codec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import netty.hikvisionLED.demo.protocol.LedResponseMessage;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class LedRepMessageEncoder extends MessageToByteEncoder<LedResponseMessage> {
    @Override
    protected void encode(ChannelHandlerContext ctx, LedResponseMessage msg, ByteBuf out) throws Exception {

    }
}
