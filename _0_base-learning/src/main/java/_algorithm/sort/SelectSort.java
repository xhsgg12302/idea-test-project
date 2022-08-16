package _algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

public class SelectSort {

    public static void ss(int[] raw){
        for (int i = 0; i < raw.length - 1 ; i++) {
            int temp = i;
            for (int j = i + 1; j < raw.length ; j++) {
                if(raw[temp] > raw[j])
                    temp = j;
            }
            SortUtils.switchNumberByXor(raw,i,temp);
        }
    }

    public static void ssByComparator(int[] raw , Comparator<Integer> comparator){
        for (int i = 0; i < raw.length - 1 ; i++) {
            int temp = i;
            for (int j = i + 1; j < raw.length ; j++) {
                if(comparator.compare(raw[temp],raw[j]) == 1) {
                    temp = j;
                }
            }
            SortUtils.switchNumberByXor(raw,i,temp);
        }
    }


    @Test
    public void test2(){
        int[] generator = SortUtils.generatorByMathRandom(10, 0, 100);
        System.out.println(Arrays.toString(generator));
        ssByComparator(generator,((o1, o2) -> o1 - o2 > 0 ? 1 : ( o1 - o2 == 0 ? 0 : -1)));
        System.out.println(Arrays.toString(generator));
    }


    @Test
    public void test(){
        int[] generator = SortUtils.generatorByMathRandom(10, 0, 100);
        System.out.println(Arrays.toString(generator));
        ss(generator);
        System.out.println(Arrays.toString(generator));

    }

    @Test
    public void performance(){
        int[] generator = SortUtils.generatorByMathRandom(80000, 0, 800000000);
        System.out.println(SortUtils.performanceByInstant((ints)-> ss(generator)) + "ms");
    }
}
