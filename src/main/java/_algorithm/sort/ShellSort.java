package _algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class ShellSort {

    //交换式
    public void shellSort(int []raw){
        for(int gaps = raw.length/2; gaps > 0; gaps /=2 ){
            for (int i = gaps; i < raw.length ; i++) {
                for (int j = i - gaps; j >=0 ; j-= gaps) {
                    if(raw[j] > raw[j + gaps]){
                        SortUtils.switchNumberByXor(raw,j,j+gaps);
                    }
                }
            }
        }
    }

    //移位式
    public void shellSort2(int [] raw){
        for(int gaps = raw.length/2; gaps > 0; gaps /=2 ){
            for (int i = gaps; i < raw.length ; i++) {
                int index = i;
                int value = raw[i];
                while(index - gaps >= 0 && value < raw[index-gaps]){
                    raw[index] = raw[index - gaps];
                    index -= gaps;
                }
                raw[index] = value;
            }
        }
    }

    @Test
    public void test(){
        int[] raw = {8,9,1,7,2,3,5,4,6,0};
        shellSort2(raw);
        System.out.println(Arrays.toString(raw));
    }

    @Test
    public void performance(){
        int[] ints = SortUtils.generatorByMathRandom(80000, 0, 800000000);
        System.out.println(SortUtils.performanceByInstant((ints1)->shellSort(ints)));  // 4980ms
        //System.out.println(SortUtils.performanceByInstant((ints1)->shellSort2(ints))); // 15ms
    }
}
