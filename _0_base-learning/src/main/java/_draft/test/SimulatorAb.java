package _draft.test;

import _utils.http.HttpUtil;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/10
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class SimulatorAb {

    private static int count = 100;

    public static void main(String[] args) {

        cycleTest();

    }

    public static void cycleTest(){
        for (int i = 0; i < count; i++) {
            new Thread(()-> {
                while(true){
                    String s = HttpUtil.requestGetBak("http://localhost:8080/servlet-demo03/bbs/findAll");
                    if(s != null){System.out.println(Thread.currentThread().getName() + " " + s );}
                    try {
                        TimeUnit.MILLISECONDS.sleep(RandomUtils.nextInt(100,1000));
                    } catch (InterruptedException e) { e.printStackTrace(); }
                }
            }).start();
        }
    }

    public void concurrentTest(){
        CyclicBarrier cyclicBarrier = new CyclicBarrier(count);
        AtomicInteger remian = new AtomicInteger(count);

        for (int i = 0; i < count; i++) {
            new Thread(()-> {
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
                String s = HttpUtil.requestGetBak("http://localhost:8080/servlet-demo03/bbs/findAll");
                System.out.println(remian.addAndGet(-1) + " " + s);
            }).start();
        }
    }




}
