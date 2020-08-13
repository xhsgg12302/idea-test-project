package _base.io.test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-07
 * @Desc:
 */
public class Test {

    public static void main1(String[] args) throws Exception{
        File file = new File("D:\\DS_TCG225_capture2\\192.168.1.100\\20191113\\20191113191049612_plate.jpg");
        InputStream  ins = new FileInputStream(file);
        BufferedInputStream buf = new BufferedInputStream(ins);
        while(buf.read()!=-1){

        }
    }

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Test test = new Test();


        reentrantLock.lock();
        reentrantLock.lock();
        reentrantLock.lock();
        reentrantLock.lock();
        reentrantLock.lock();
        System.out.println("main lock");

        test.testReentrantLock(reentrantLock);

        System.out.println("main unlock");


    }

    public void testReentrantLock(ReentrantLock reentrantLock){

        reentrantLock.lock();

        System.out.println("this is testReentrantLock");

        reentrantLock.unlock();


    }
}
