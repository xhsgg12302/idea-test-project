package _base.socket.mockssh;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class JavaSshServer {

    private static SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss SSS");

    private static void test() throws Exception {
        ServerSocket serverSocket = new ServerSocket(14308);

        System.out.println("Listening 14308 port ...");
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                handleSocket(socket);
            } catch (IOException e) { e.printStackTrace();}
        }
    }

    public static void handleSocket(Socket socket){
        new Thread(()->{
            try{
                Socket client = socket;
                Timer t = new Timer();
                InputStream tempStream = client.getInputStream();
                OutputStream out = client.getOutputStream();
                int n;
                byte[] bytes = new byte[1024];
                while ((n = tempStream.read(bytes)) > -1) {
                    String recv = new String(bytes,0, n, "UTF-8");
                    String rsg = "[" + format.format(new Date()) + ":" + Thread.currentThread().getName() + ":" + socket + "]:";
                    System.out.println(rsg + recv);
                    boolean isSend = false;
                    String msg = null;
                    switch (recv){
                        case "ls":
                            isSend = true;
                            msg = "checkin.sh  configure-files  daliy  practise  shell-scripts  software";
                            break;
                        case "pwd":
                            isSend = true;
                            msg = "/home/eli";
                            break;
                        default:
                            break;
                    }
                    if(isSend){ sendReply(Thread.currentThread().getName(),client,out, msg);}
                    bytes = new byte[1024];
                    t = handleHeartbeat(t,client);
                }
            }catch (Exception e){
                System.err.println(Thread.currentThread().getName() + "has died  with error :" + e.getMessage());
            }
        }).start();
    }

    private static Timer handleHeartbeat(Timer t, Socket socket) throws IOException {
        if(t != null){t.cancel();}
        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                OutputStream outputStream = null;
                try {
                    outputStream = socket.getOutputStream();
                    String msg = "hello ping";
                    outputStream.write(msg.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                } catch (IOException e) {e.printStackTrace();}
            }
        }, 26 * 1000);
        return t;
    }

    private static void sendReply(String name, Socket client, OutputStream out, String msg) throws IOException {
        out.write(msg.getBytes(StandardCharsets.UTF_8));
        out.flush();
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
