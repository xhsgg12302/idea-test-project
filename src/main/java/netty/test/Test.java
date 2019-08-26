package netty.test;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {

        ByteBuf byteBuf = Unpooled.copiedBuffer("$_".getBytes());

        ByteBuf byteBuf2 = Unpooled.copiedBuffer(new byte[]{0x00,0x00,0x0d,0x0a});

        System.out.println(byteBuf2);
    }

}
