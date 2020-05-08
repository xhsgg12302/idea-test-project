package _base.thread;

import java.io.IOException;
import java.net.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-02
 * @Desc:
 */
public class TestCPUCOREs {

    public static void main(String[] args) {

        start1();
        start2();
        /*start3();
        start4();
        start5();*/

    }

    public static void start1(){
        new Thread(() -> {
            while (true) {
                Socket socket = new Socket();
                try {
                    InetAddress addr = InetAddress.getByName("192.168.1.102");
                    socket.connect(new InetSocketAddress(addr, 8200), 3000);
                    //_base.socket.setSoTimeout(1000);
                } catch (ConnectException e) {
                    System.out.println(e.getMessage());
                } catch (SocketTimeoutException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownHostException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "_draft.test-3").start();
    }

    public static void start2(){
        new Thread(() -> {
            while (true) {
                Socket socket = new Socket();
                try {
                    InetAddress addr = InetAddress.getByName("192.168.1.103");
                    socket.connect(new InetSocketAddress(addr, 8200), 3000);
                    //_base.socket.setSoTimeout(1000);
                } catch (ConnectException e) {
                    System.out.println(e.getMessage());
                } catch (SocketTimeoutException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownHostException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "_draft.test-4").start();
    }

    public static void start3(){
        new Thread(() -> {
            while (true) {
                Socket socket = new Socket();
                try {
                    InetAddress addr = InetAddress.getByName("192.168.1.104");
                    socket.connect(new InetSocketAddress(addr, 8200), 3000);
                    //_base.socket.setSoTimeout(1000);
                } catch (ConnectException e) {
                    System.out.println(e.getMessage());
                } catch (SocketTimeoutException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownHostException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "_draft.test-5").start();
    }

    public static void start4(){
        new Thread(() -> {
            while (true) {
                Socket socket = new Socket();
                try {
                    InetAddress addr = InetAddress.getByName("192.168.1.105");
                    socket.connect(new InetSocketAddress(addr, 8200), 3000);
                    //_base.socket.setSoTimeout(1000);
                } catch (ConnectException e) {
                    System.out.println(e.getMessage());
                } catch (SocketTimeoutException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownHostException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "_draft.test-6").start();
    }

    public static void start5(){
        new Thread(() -> {
            while (true) {
                Socket socket = new Socket();
                try {
                    InetAddress addr = InetAddress.getByName("192.168.1.106");
                    socket.connect(new InetSocketAddress(addr, 8200), 3000);
                    //_base.socket.setSoTimeout(1000);
                } catch (ConnectException e) {
                    System.out.println(e.getMessage());
                } catch (SocketTimeoutException e) {
                    System.out.println(e.getMessage());
                } catch (UnknownHostException e) {
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }, "_draft.test-7").start();
    }

    public static void whiletrue(){
        new Thread(()->{
            while(true){

            }
        }).start();
    }
    public static void whiletruesys(){
        new Thread(()->{
            while(true){
                System.out.println();
            }
        }).start();
    }

    public static void main2(String[] args) {
        whiletrue();
        whiletrue();
        //whiletruesys();
    }

    public static void main4(String[] args) {
        new Thread(()->{
            long startTime = 0l;
            while(true){
                try {

                    //Thread.sleep(3 * 1000l);
                    Socket socket = new Socket();
                    InetAddress addr = InetAddress.getByName("192.168.1.106");
                    //System.out.println("----------start-----------");
                    startTime = System.currentTimeMillis();
                    socket.connect(new InetSocketAddress(addr, 8200), 3000);

                }catch (Exception e){
                    System.out.println((System.currentTimeMillis()-startTime)/1000);
                    //System.out.println("----------end-----"+ e.getMessage() +"------");
                }
            }
        }).start();
    }
}
