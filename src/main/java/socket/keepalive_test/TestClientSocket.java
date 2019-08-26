package socket.keepalive_test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-07-20
 * @Desc:
 */
public class TestClientSocket {
    public static void main(String[] args) throws IOException, InterruptedException {
        Socket socket = new Socket("127.0.0.1", 12345);
        System.out.println(socket);
        socket.setSendBufferSize(8192);
        socket.setReceiveBufferSize(8192);
        InputStream is = socket.getInputStream();
        OutputStream os = socket.getOutputStream();
        os.write("get test-key".getBytes("UTF-8"));
        os.flush();
        Thread.sleep(155 * 1000L);
        os.write("get test-key".getBytes("UTF-8"));
        os.flush();
        Thread.sleep(10 * 1000L);
        if (!socket.isClosed()) {
            socket.close();
        }
    }
}
