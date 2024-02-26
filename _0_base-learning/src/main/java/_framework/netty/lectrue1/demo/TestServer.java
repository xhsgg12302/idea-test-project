package _framework.netty.lectrue1.demo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Author:12302
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/7 19:15
 * @Description:
 */
public class TestServer {
    public static void main(String[] args) {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup wordGroup = new NioEventLoopGroup();

        ServerBootstrap serverBootstrap = new ServerBootstrap();

        try {
            serverBootstrap.group(bossGroup,wordGroup)
                    .channel(NioServerSocketChannel.class)

                    // channel初始化器
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast("httpServerCodec",new HttpServerCodec());

                            // 在pipeline中加入自定义的解码器 handler
                            pipeline.addLast("httpServerContent", new SimpleChannelInboundHandler<HttpObject>() {
                                @Override
                                protected void channelRead0(ChannelHandlerContext channelHandlerContext, HttpObject msg) throws Exception {
                                    /*
                                        另外有一个 LastHttpContent 类型的 EMPTY_LAST_CONTENT 对象
                                        来源：HttpServerCodec 中 inboundHandler 为 HttpServerRequestDecoder ==>  HttpObjectDecoder
                                        channelRead 的时候会进行解码，追加两个对象；
                                            out.add(message);
                                            out.add(LastHttpContent.EMPTY_LAST_CONTENT);
                                        所以追加if判断
                                    */
                                    if(msg instanceof HttpRequest){
                                        ByteBuf byteBuf = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
                                        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.OK,byteBuf);
                                        response.headers().set(HttpHeaderNames.CONTENT_TYPE,"text/plain");
                                        response.headers().set(HttpHeaderNames.CONTENT_LENGTH,byteBuf.readableBytes());
                                        response.headers().set(HttpHeaderNames.SERVER,"X-eli");

                                        channelHandlerContext.writeAndFlush(response);
                                    }
                                }
                            });
                        }
                    });
            ChannelFuture channelFuture = serverBootstrap.bind(13308).sync();
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            bossGroup.shutdownGracefully();
            wordGroup.shutdownGracefully();
        }
    }
}
