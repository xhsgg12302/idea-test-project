package _base.socket;

import java.net.Socket;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/4 16:47
 * @Description:
 */
public class TestClientSocket {
    public static void main(String[] args) {
        Socket socket;
        try {
            socket = new Socket("192.168.1.45",12302);
            socket.setKeepAlive(true);
            System.out.println(socket);
            while(true && null != socket){
                Thread.sleep(10 * 1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
