package _base.thread._fork_join;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2021/12/7
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo {

    static Random random = new Random(0);

    static long random() {
        return random.nextInt(10000);
    }

    public static void main(String[] args) {
        int[] array = new int[20];
        for (int i = 0; i < array.length; i++) {
            array[i] = (int)random();
        }
        System.out.println(Arrays.toString(array));
        System.out.println(new Demo().findMax(array,3)); 
    }
    
    public int findMax(int[] array, int nThreads ){
        Solver  solver = new Solver(array, 0 , array.length);
        ForkJoinPool pool = new ForkJoinPool(nThreads);
        pool.invoke(solver);
        int result = solver.result;
        return result;
    }
}


class Solver extends RecursiveAction {
    
    public int start, end, result;
    
    private int array[];
    
    public Solver (int[] array, int start, int end){
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if(end - start == 1){
            result = array[start];
        }else{
            int mid = (start + end)/2;
            Solver solver1 = new Solver( array, start, mid );
            Solver solver2 = new Solver(array ,mid, end);
            invokeAll(solver1,solver2);
            result = Math.max(solver1.result, solver2.result);
        }
    }
}