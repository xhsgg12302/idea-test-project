package _base.socket.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2024/2/1
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Server {
    /**
     * see: http://localhost:3000/#/docs/doc/base/network?id=http-impl
     * @param args
     */
    public static void main(String[] args) {
        // You can use print statements as follows for debugging, they'll be visible when running tests.
        System.out.println("Logs from your program will appear here!");
        ServerSocket serverSocket = null;
        Socket clientSocket = null;

        try {
            serverSocket = new ServerSocket(4221);
            Socket socket1 = new Socket();
            Socket socket2 = new Socket("baidu.com", 443);
            serverSocket.setReuseAddress(true);

            while(true) {
                clientSocket = serverSocket.accept(); // Wait for connection from client.
                System.out.println("accepted new connection");

                Socket finalClientSocket = clientSocket;
                new Thread(() -> {
                    // 必须关闭，不关闭的话，容易资源泄露，并且curl 会等待。
                    try(
                        InputStream input = finalClientSocket.getInputStream();
                        OutputStream output = finalClientSocket.getOutputStream()){

                        BufferedReader in = new BufferedReader(new InputStreamReader(input));
                        String startLine = in.lines().findFirst().get();
                        String path = startLine.split(" ")[1];

                        StringBuilder response = new StringBuilder();
                        if ("/".equals(path)) {
                            response.append("HTTP/1.1 200 OK\n");
                        } else {
                            response.append("HTTP/1.1 404 Not Found\n");
                        }

                        response.append("Content-Type: text/plain\n");
                        //response.append("Connection: keep-alive\n");
                        response.append("Connection: close\n");

                        response.append("\n");
                        response.append("hello world");

                        String responseStr = response.toString();
                        output.write(responseStr.getBytes(StandardCharsets.UTF_8));
                        output.flush();

                        System.out.println("This message is sent to the client: \n" + responseStr);
                    }catch (Exception e){e.printStackTrace();}
                }).start();
            }
        } catch (Exception e) {
            System.out.println("IOException: " + e.getMessage());
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}

