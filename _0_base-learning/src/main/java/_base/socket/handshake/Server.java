package _base.socket.handshake;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-07
 * @Desc:
 */
public class Server {

    public static void main(String[] args) {
        InetSocketAddress inetSocketAddress;
        ServerSocket server;
        try {
            inetSocketAddress = new InetSocketAddress("192.168.0.100",12302);
            server = new ServerSocket();
            server.bind(inetSocketAddress);
            while(true){
                Socket socket = server.accept();
                InputStream ips = socket.getInputStream();
                new Thread(()->{
                    try{
                        boolean isException = false;
                        while(!isException){
                            try{
                                int data;
                                byte[] buf = new byte[36];
                                while((data = ips.read(buf)) != -1){
                                    System.out.println(new String(buf,0,data,"UTF-8"));
                                }
                            }catch (Exception e){ isException = true; e.printStackTrace();}
                        }
                    }catch (Exception e){}
                    /*finally {
                        if(ips != null) {
                            try {
                                ips.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        if(_base.socket != null){
                            try {
                                _base.socket.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }*/

                }).start();
            }
        } catch (IOException e) {}
    }
}
