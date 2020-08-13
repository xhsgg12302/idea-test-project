package _draft.test;

import java.util.concurrent.TimeUnit;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-17
 * @Desc:
 */
public class Test_Sleep_CPU {

    private static  int varibal = 0;

    private static boolean flag;

    public static void main(String[] args) {


        new Thread(()->{

            while(true){
                try {
                    TimeUnit.SECONDS.sleep(3);
                    varibal ++;
                    System.out.println("this is _base.thread-1");
                    System.out.println(flag);

                }catch (Exception e){

                }
            }

        },"_base.thread-1").start();

        new Thread(()->{

            while(true){
                try {
                    TimeUnit.SECONDS.sleep(2);
                    varibal ++;
                    System.out.println("this is _base.thread-2");
                    System.out.println(flag);
                }catch (Exception e){

                }
            }

        },"_base.thread-2").start();


    }

}
