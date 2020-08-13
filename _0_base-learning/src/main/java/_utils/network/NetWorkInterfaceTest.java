package _utils.network;

import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-13
 * @Desc:
 */
public class NetWorkInterfaceTest {
    public static void main(String args[]) throws Exception {

        Enumeration<NetworkInterface> e = NetworkInterface.getNetworkInterfaces();
        while (e.hasMoreElements()) {
            NetworkInterface ni = e.nextElement();
            byte[] mac = ni.getHardwareAddress();
            if (mac != null) {
                displayMac(mac);
                System.out.println("displayname: " + ni.getDisplayName());
                System.out.println("name: " + ni.getName());
                System.out.println("MTU: " + ni.getMTU());
                System.out.println("Loopback: " + ni.isLoopback());
                System.out.println("Virtual: " + ni.isVirtual());
                System.out.println("Up: " + ni.isUp());
                System.out.println("PointToPoint: " + ni.isPointToPoint());
                System.out.println("-----");
            }
        }

    }


    public static void displayMac(byte[] mac) {
        for (int i = 0; i < mac.length; i++) {
            byte b = mac[i];
            int intValue = 0;
            if (b >= 0) intValue = b;
            else intValue = 256 + b;
            System.out.print(Integer.toHexString(intValue));
            if (i != mac.length - 1)
                System.out.print("-");
        }
        System.out.println();
    }
}
