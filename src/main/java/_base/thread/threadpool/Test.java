package _base.thread.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/6/18 16:08
 * @Description:
 */
public class Test {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = new ArrayList<Future<String>>();
        for(int i=0;i<10;i++){
            //使用future接受处理结果
            Future<String> future = executorService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {
                    System.out.println("线程名称"+Thread.currentThread().getName());
                    return Thread.currentThread().getName();
                }
            });
            futures.add(future);
        }
        try {
            for(Future<String> future : futures){
                //get方法会阻塞当前线程，直到任务执行完成返回结果
                System.out.println("返回结果====="+future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //开始关闭线程池
        executorService.shutdown();
        System.out.println("线程池关闭完成");

    }
}

class MyCallBackThread implements Callable<String>{
    private List<Future<String>> futures;

    public MyCallBackThread(List<Future<String>> futures){
        this.futures = futures;
    }

    @Override
    public String call() throws Exception {
        try {
            for(Future<String> future : futures){
                //get方法会阻塞当前线程，直到任务执行完成返回结果
                System.out.println("返回结果====="+future.get());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
