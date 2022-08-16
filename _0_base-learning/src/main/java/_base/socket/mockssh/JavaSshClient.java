package _base.socket.mockssh;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/7
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class JavaSshClient {

    private static void test() throws Exception {
        Socket socket = new Socket("127.0.0.1", 14408);

        /*System.out.println(socket.getKeepAlive());
        socket.setKeepAlive(true);
        System.out.println(socket.getKeepAlive());
        socket.setKeepAlive(false);
        System.out.println(socket.getKeepAlive());*/

        System.out.println(socket);
        //ThreadUtil.keepRunning();
/*
        new Thread(() -> {
            try{
                InputStream tempStream = socket.getInputStream();
                while(true){
                    byte[] bytes = new byte[1024];
                    while (tempStream.read(bytes) > -1) {
                        System.out.println(System.currentTimeMillis() + " received message: " + new String(bytes, "UTF-8").trim());
                        bytes = new byte[1024];
                    }
                    TimeUnit.SECONDS.sleep(1);
                }
            }catch (Exception e){ e.printStackTrace();}
        }).start();
*/
        OutputStream outputStream = socket.getOutputStream();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            try {
                outputStream.write(message.getBytes("UTF-8"));
                outputStream.flush();
            } catch (IOException e) { e.printStackTrace();}
        }
    }


    public static void main(String[] args) throws Exception {
        test();
    }



}
