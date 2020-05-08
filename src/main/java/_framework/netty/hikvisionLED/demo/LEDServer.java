package _framework.netty.hikvisionLED.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import io.netty.util.CharsetUtil;

import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-08-21
 * @Desc:
 */
public class LEDServer {

    public static void main(String[] args) throws Exception{

        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            bootstrap.group(bossGroup,workGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast("idle",new IdleStateHandler(6,0,0, TimeUnit.SECONDS))
                                    /*_12302_2019-07-27_<
                                       添加delimiterHandler意味着你要在发的消息后面追加'\r\n'||'\r',
                                       不然服务器接收到数据不会触发channelRead0(),
                                       而是将数据缓存起来，超过最大长度触发TooLongFrameException()异常
                                    >*/
                                    .addLast(new DelimiterBasedFrameDecoder(4096, Delimiters.lineDelimiter()))
                                    .addLast(new StringDecoder(CharsetUtil.UTF_8))
                                    .addLast(new StringEncoder(CharsetUtil.UTF_8))
                                    .addLast("userEvent",new ChannelInboundHandlerAdapter(){
                                        @Override
                                        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                            System.out.println(msg);
                                        }

                                        @Override
                                        public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
                                            if(evt instanceof IdleStateEvent){
                                                IdleStateEvent idleStateEvent = (IdleStateEvent) evt;
                                                String eventStr = null;
                                                switch (idleStateEvent.state()){
                                                    case READER_IDLE:
                                                        eventStr = "读空闲";
                                                        break;
                                                    case WRITER_IDLE:
                                                        eventStr = "写空闲";
                                                        break;
                                                    case ALL_IDLE:
                                                        eventStr = "读写空闲";
                                                        break;
                                                    default:break;
                                                }
                                                System.out.println(eventStr);

                                                /*_12302_2019-07-27_<当时忘写这一句，和老师视频中的效果不一致，导致浪费时间排查>*/
                                                ctx.channel().close();
                                            }
                                        }
                                    });
                        }
                    });

            ChannelFuture channelFuture = bootstrap.bind(12302).sync();
            channelFuture.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
