package _base.io.nio.Scalable_IO_in_Java.classic_server_socker_loop;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/5/1
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Server implements Runnable{

    private static final int PORT = 12302;

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(PORT);
            while(!Thread.interrupted()){
                new Thread(new Handler(ss.accept())).start();
            }
        } catch (Exception e) { /* ... */}
    }

    static class Handler implements Runnable{
        private final Socket socket;
        public Handler(Socket socket){
            this.socket = socket;
        }
        @Override
        public void run() {
            try {
                byte[] input = new byte[32];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
                //socket.close();
            } catch (Exception e) { /* ... */}
        }

        private byte[] process(byte[] cmd) throws IOException{
            System.out.println(new String(cmd, StandardCharsets.UTF_8));
            return cmd;
        }
    }

    public static void main(String[] args) {
        new Thread(new Server()).start();
    }
}
