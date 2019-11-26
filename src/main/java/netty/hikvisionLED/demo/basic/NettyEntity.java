package netty.hikvisionLED.demo.basic;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import netty.hikvisionLED.demo.codec.LedRepMessageDecoder;
import netty.hikvisionLED.demo.codec.LedReqMessageEncoder;
import netty.hikvisionLED.demo.protocol.LedRequestMessage;
import netty.hikvisionLED.demo.protocol.LedResponseMessage;
import netty.hikvisionLED.demo.tools.CommandEntity;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-09-02
 * @Desc:
 */
public class NettyEntity {

    private static volatile NettyEntity nettyEntity = null;

    private Bootstrap bootstrap;

    private EventLoopGroup clientGroup;

    private final Map<String, CommandEntity> index = new ConcurrentHashMap<>();

    public Bootstrap getBootstrap() {
        return bootstrap;
    }

    public Map<String, CommandEntity> getIndex() {
        return index;
    }

    private NettyEntity(){
        clientGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(clientGroup)
                .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) {
                        ByteBuf delimiter = Unpooled.copiedBuffer(new byte[]{0x00, 0x00, 0x0d, 0x0a});
                        ch.pipeline()
                                .addLast("frameTail", new DelimiterBasedFrameDecoder(1024, false, delimiter))
                                .addLast("ledReqEncoder", new LedReqMessageEncoder())
                                .addLast("ledRepDecoder", new LedRepMessageDecoder())
                                .addLast(new SimpleChannelInboundHandler<LedResponseMessage>() {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, LedResponseMessage msg) throws Exception {
                                        System.out.println(msg);

                                        Thread.sleep(50l);
                                        Object temp = location(index.get("curr"));
                                        if (temp instanceof LedRequestMessage) {
                                            ctx.writeAndFlush(temp);
                                        } else {
                                            ctx.close();
                                        }
                                    }

                                    @Override
                                    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                        ctx.close();
                                    }
                                });
                    }
                });
        this.bootstrap = bootstrap;
    }

    public static NettyEntity getInstance(){
        if (null == nettyEntity) {
            synchronized (NettyEntity.class) {
                if (null == nettyEntity) {
                    nettyEntity = new NettyEntity();
                }
            }
        }
        return nettyEntity;
    }

    public static Object location(CommandEntity commandEntity) {
        synchronized (commandEntity) {
            List<Object> cmds = commandEntity.getObjs();
            int index = commandEntity.getIndex();
            commandEntity.setIndex(index + 1);
            Object obj = cmds.get(index);
            return obj;
        }
    }
}
