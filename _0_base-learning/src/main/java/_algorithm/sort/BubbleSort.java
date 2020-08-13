package _algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class BubbleSort {

    public void bs(int[] raw){
        for (int i = 1; i < raw.length ; i++) {
            for (int j = 0; j < raw.length - i ; j++) {
                if(raw[j] > raw[j+1])
                    SortUtils.switchNumberByTemp(raw,j,j+1);
            }
        }
    }

    @Test
    public void test(){
        int[] generator = SortUtils.generatorByMathRandom(10, 0, 100);
        System.out.println(Arrays.toString(generator));
        bs(generator);
        System.out.println(Arrays.toString(generator));

    }

    @Test
    public void performance(){
        int[] generator = SortUtils.generatorByMathRandom(80000, 0, 800000000);
        System.out.println(SortUtils.performanceByStopWatch((ints)-> bs(generator)) + "ms");
    }
}
