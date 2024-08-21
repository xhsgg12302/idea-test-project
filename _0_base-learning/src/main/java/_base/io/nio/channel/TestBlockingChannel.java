package _base.io.nio.channel;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/4/16
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class TestBlockingChannel {

    /**
     * ref https://juejin.cn/s/java%20socket%20%E9%9D%9E%E9%98%BB%E5%A1%9E
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {

        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        socketChannel.connect(new InetSocketAddress("test.wtfu.site", 13307));

        while (!socketChannel.finishConnect()) {
            // 等待连接完成
        }

        ByteBuffer buf = ByteBuffer.allocate(1024);
        int bytesRead = socketChannel.read(buf); // 读取数据
        while (bytesRead != -1) {
            buf.flip(); // 切换到读模式
            while (buf.hasRemaining()) {
                System.out.print((char) buf.get()); // 处理读取的数据
            }
            buf.clear(); // 清空缓冲区
            bytesRead = socketChannel.read(buf); // 继续读取数据

            System.out.println("read over");
        }

        socketChannel.close();
    }
}
