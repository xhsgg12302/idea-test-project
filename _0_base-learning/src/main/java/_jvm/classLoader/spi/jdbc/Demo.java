package _jvm.classLoader.spi.jdbc;

import _utils.thread.ThreadUtil;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/7/4
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo {

    /**
     * -XX:+TraceClassLoading
     * @param args
     */
    public static void main(String[] args) {
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while(drivers.hasMoreElements()) {
            System.out.println(drivers.nextElement());
        }
        ThreadUtil.keepRunning();
    }
}
