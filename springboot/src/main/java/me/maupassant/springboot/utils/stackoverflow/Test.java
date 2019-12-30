package me.maupassant.springboot.utils.stackoverflow;

import org.apache.commons.net.telnet.TelnetClient;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-13
 * @Desc:
 */
public class Test {
    public static void main1(String[] args) throws Exception{
        /*NetworkStatusThread netStatus = new NetworkStatusThread();
        netStatus.addNetworkListener(this);
        Thread t = new Thread(netStatus);
        t.Start();*/

        /*InetAddress[] addresses = InetAddress.getAllByName("www.baidu.com");
        for (InetAddress address : addresses) {
                if(address.isReachable(timeout)){
                    System.out.println("123456576");
                }
        }*/

        new Thread(NetworkStatusThread.getInstance()).start();


        Thread.sleep(2* 1000l);
        NetworkStatusThread.getInstance().addNetTask(new NetTask("39.107.88.49", new NetworkListener() {
            @Override
            public void sendNetworkStatus(String status) {
                System.out.println("39.107.88.49 \t" + status);
            }
        }));



        Thread.sleep(4 * 1000l);
        NetworkStatusThread.getInstance().addNetTask(new NetTask("127.0.0.1", new NetworkListener() {
            @Override
            public void sendNetworkStatus(String status) {
                System.out.println("127.0.0.1 \t" + status);
            }
        }));

        Thread.sleep(6 * 1000l);
        NetworkStatusThread.getInstance().addNetTask(new NetTask("192.168.1.2", new NetworkListener() {
            @Override
            public void sendNetworkStatus(String status) {
                System.out.println("192.168.1.2 \t" + status);
            }
        }));

        Thread.sleep(8 * 1000l);
        NetworkStatusThread.getInstance().addNetTask(new NetTask("192.168.1.2", new NetworkListener() {
            @Override
            public void sendNetworkStatus(String status) {
                System.out.println("127.0.0.1 \t" + status);
            }
        }));

        /*Thread.sleep(10 * 1000l);
        NetworkStatusThread.getInstance().addNetTask(new NetTask("111.198.53.224", new NetworkListener() {
            @Override
            public void sendNetworkStatus(String status) {
                System.out.println("111.198.53.224" + status);
            }
        }));*/
    }

    public static void main2(String[] args) throws Exception{
        InetAddress address = InetAddress.getByName("111.198.53.224");

        InetSocketAddress inetSocketAddress = new InetSocketAddress(address,123545);
        if (address.isReachable(5000)) {
            System.out.println("_12302");
        } else {
            System.out.println("_continue_detect_network_situation...\t" );
        }
    }

    public static void main(String[] args) {
        TelnetClient client = new TelnetClient();

        client.setDefaultTimeout(3000);

        try {
            client.connect("111.198.53.224", 8080);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
