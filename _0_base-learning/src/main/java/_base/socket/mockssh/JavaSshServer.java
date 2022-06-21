package _base.socket.mockssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/7
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class JavaSshServer {

    private static void test() throws Exception {
        ServerSocket serverSocket = new ServerSocket(14408);

        System.out.println("Listening 14408 port ...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();

                new Thread(()->{
                    try{
                        Socket temp = socket;
                        InputStream tempStream = temp.getInputStream();
                        while(true){
                            if(socket.isClosed()) break;
                            byte[] bytes = new byte[1024];
                            while (tempStream.read(bytes) > -1) {
                                System.out.println(System.currentTimeMillis() + " received message: " + new String(bytes, "UTF-8").trim());
                                bytes = new byte[1024];
                            }
                            TimeUnit.SECONDS.sleep(1);
                        }
                    }catch (Exception e){ e.printStackTrace();}
                }).start();
            } catch (IOException e) { e.printStackTrace();}
        }
    }

    private static void sendHeartbeat(Socket  socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        outputStream.write("hello".getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
