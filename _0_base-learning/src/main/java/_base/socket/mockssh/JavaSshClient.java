package _base.socket.mockssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        Socket socket = new Socket("wtfu.site", 14308);

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
        InputStream inputStream = socket.getInputStream();
        new Thread(()->{
            try {
                handleInputStream(inputStream,outputStream);
            } catch (IOException e) { e.printStackTrace();}
        }).start();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            try {
                outputStream.write(message.getBytes("UTF-8"));
                outputStream.flush();
            } catch (IOException e) { e.printStackTrace();}
        }
    }

    private static void handleInputStream(InputStream in,OutputStream out) throws IOException {
        int n;
        byte[] bytes = new byte[1024];
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss SSS");
        while ( (n = in.read(bytes)) > -1) {
            String recv = new String(bytes,0, n, "UTF-8");
            System.out.println(format.format(new Date()) + ": " + recv);
            bytes = new byte[1024];
            String msg = null;
            boolean isSend = false;
            switch (recv){
                case "hello ping":
                    isSend = true;
                    msg = "hello pong";
                    break;
                default:
                    break;
            }
            if(isSend){ sendReply(out, msg);}
        }
    }

    private static void sendReply(OutputStream outputStream, String msg) throws IOException {
        outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public static void main(String[] args) throws Exception {
        test();
    }



}
