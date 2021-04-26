package _base.io.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocketLoop  implements  Runnable{

    @Override
    public void run() {
        try{
            ServerSocket ss = new ServerSocket(1080);
            while(!Thread.interrupted()){
                new Thread(new Handler(ss.accept())).start();
                // or, single-threaded, or a thread pool
            }
        }catch (IOException ex){}
    }


    static class Handler implements Runnable {
        final Socket socket;
        Handler(Socket s){
            socket = s ;
        }

        @Override
        public void run() {
            try{
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            }catch ( IOException ex ){}
        }

        private byte[] process(byte[] cmd){ return new byte[1];}
    }
}
