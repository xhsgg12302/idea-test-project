package _utils.network.stackoverflow;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import java.util.Vector;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-13
 * @Desc:
 */
public class NetworkStatusThread implements Runnable {

    private List tasks = new Vector<NetTask>();

    private final Object mutex = new Object();

    private static class getInstance {
        private static NetworkStatusThread singleton = new NetworkStatusThread();
    }

    public static NetworkStatusThread getInstance() {
        return getInstance.singleton;
    }

    public void addNetTask(NetTask netTask) {
        synchronized (mutex) {
            tasks.add(netTask);
            System.out.println("_base.thread are going on notify -addNetTask-");
            mutex.notifyAll();
        }
    }

    public void run() {
        while (true) {
            synchronized (mutex) {
                try {
                    int timeout = 2000;

                    while (tasks.isEmpty()) {
                        System.out.println("_base.thread are going on wait -isEmpty-");
                        mutex.wait();
                    }
                    NetTask curr = NetTask.class.cast(tasks.get(0));
                    InetAddress address = InetAddress.getByName(curr.getIp());


                    if (address.isReachable(timeout)) {
                        curr.getNetworkListener().sendNetworkStatus("ok");
                        System.out.println("deleting " + curr);
                        tasks.remove(curr);
                        System.out.println("_base.thread are going on wait - isReachable- ");
                        mutex.wait();
                    } else {
                        System.out.println("_continue...\t" + curr);
                    }

                    Thread.sleep(2000);
                } catch (InterruptedException sleep) {
                    //
                    new Thread(this).start();
                } catch (UnknownHostException name) {
                    //
                } catch (IOException reach) {

                }
            }
        }
    }
}
