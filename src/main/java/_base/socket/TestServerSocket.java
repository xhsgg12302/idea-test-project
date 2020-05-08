package _base.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/7/4 16:30
 * @Description:
 */
public class TestServerSocket {
    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress;
        ServerSocket server;
        try {
            inetSocketAddress = new InetSocketAddress("192.168.0.100",12302);
            server = new ServerSocket();
            server.bind(inetSocketAddress);
            Socket socket = server.accept();
            new Thread(new Ta(socket)).start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
class Ta implements Runnable{

    private Socket socket;
    public Ta(Socket socket){
        this.socket = socket;
    }
    @Override
    public void run() {
        try {
            System.out.println(socket.toString());
            socket.setKeepAlive(true);
            System.out.println(socket.getSoTimeout());
            //_base.socket.setSoTimeout(5 * 1000);
            String _pattern = "yyyy-MM-dd HH:mm:ss";
            SimpleDateFormat format = new SimpleDateFormat(_pattern);
            InputStream ips = socket.getInputStream();
            while(true){
                System.out.println("开始：" + format.format(new Date()));
                ByteArrayOutputStream bops = new ByteArrayOutputStream();
                try{
                    int data;
                    byte[] buf = new byte[36];
                    while((data = ips.read(buf)) != -1){
                        //System.out.println(data);
                        bops.write(data);
                        System.out.println(new String(buf,"UTF-8"));
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                System.out.println(Arrays.toString(bops.toByteArray()));
                Thread.sleep(1000);
                /*_12302_2019-07-04_<是否邦定>*/
                System.out.println("是否邦定:"+socket.isBound());
                /*_12302_2019-07-04_<是否关闭>*/
                System.out.println("是否关闭:"+socket.isClosed());
                /*_12302_2019-07-04_<是否连接>*/
                System.out.println("是否连接:"+socket.isConnected());
                /*_12302_2019-07-04_<是否关闭输入流>*/
                System.out.println("是否关闭输入流:"+socket.isInputShutdown());
                /*_12302_2019-07-04_<是否关闭输出流>*/
                System.out.println("是否关闭输入流:"+socket.isOutputShutdown());
                System.out.println("结束：" + format.format(new Date()));

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}