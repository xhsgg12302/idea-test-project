package _base.thread;

import jdk.nashorn.internal.codegen.CompilerConstants;

import java.util.concurrent.*;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @Email: xhsgg12302@gmail.com
 * @Date: 2019/3/19 10:55
 * @Description:
 */
public class StatisTest {
    public static int count = 0;
    private static void add(){
        count++;
    }
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10 ; i++) {
           /* executorService.execute(() -> {
                add();
                System.out.println(count);
            });
            executorService.submit(()->{
                System.out.println(1);
            });
            executorService.submit(()->{
                System.out.println( );
                return 1 + 1 ;
            });*/
            final int out = i;  int result= 3 ;
            Future<Integer> rst = executorService.submit(()->{ int vab = out * 2; },result);
            System.out.println(rst.get());

        }
}}

class abc implements Callable{


    @Override
    public Object call() throws Exception {
        return null;
    }
}


