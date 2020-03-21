package socket.handshake;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.UUID;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-07
 * @Desc:
 */
public class Client {
    public static void main(String[] args) {
        Socket socket = null;
        OutputStream outputStream = null;
        try {
            socket = new Socket("192.168.0.100", 12302);
            socket.setKeepAlive(true);
            System.out.println(socket);
            outputStream = socket.getOutputStream();
            int count = 0;
            while (true && null != socket && count != 2) {
                Thread.sleep(3 * 1000);
                outputStream.write(UUID.randomUUID().toString().getBytes());
                count++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
