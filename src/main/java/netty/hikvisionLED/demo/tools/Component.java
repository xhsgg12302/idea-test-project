package netty.hikvisionLED.demo.tools;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc: 文档上面建议使用短连接操作
 */
public class  Component{

    public static Map<String,Object> connectAndPerform(String ip, Integer port ,final Map<String,Object> result ,
                                                       LedRequestMessage ... ledRequestMessages) {

        CommandEntity commandEntity = new CommandEntity();


        //commandEntity.setObjs(ledRequestMessages);

        EventLoopGroup clientGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
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
                                            result.put("msg", msg);
                                        }

                                        @Override
                                        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                                            ctx.close();
                                        }
                                    });
                        }
                    });
            ChannelFuture channelFuture = bootstrap.connect(ip, port).sync();
            Channel channel = channelFuture.channel();
            channel.writeAndFlush("");
            channel.closeFuture().sync();
        }catch ( Exception e){
            e.printStackTrace();
        } finally {
            clientGroup.shutdownGracefully();
        }
        return result;
    }

    public synchronized static Object location(Map<Integer,List<Object>> params){

        return null;
    }
}
