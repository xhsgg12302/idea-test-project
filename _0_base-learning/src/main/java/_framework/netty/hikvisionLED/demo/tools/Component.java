package _framework.netty.hikvisionLED.demo.tools;

import io.netty.channel.Channel;
import _framework.netty.hikvisionLED.demo.basic.NettyEntity;
import _framework.netty.hikvisionLED.demo.protocol.LedRequestMessage;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc: 文档上面建议使用短连接操作
 */
public class Component {

    public static void connectAndPerform(String ip, Integer port,
                                         LedRequestMessage... ledRequestMessages) {

        final CommandEntity commandEntity = new CommandEntity();
        commandEntity.setObjs(ledRequestMessages);
        commandEntity.setIndex(0);

        NettyEntity nettyEntity = NettyEntity.getInstance();
        nettyEntity.getIndex().put("curr",commandEntity);
        try {
            Channel channel = nettyEntity.getBootstrap().connect(ip,port).sync().channel();
            channel.writeAndFlush(NettyEntity.location(commandEntity));
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //nettyEntity.getClientGroup().shutdownGracefully();
        }
    }
}
