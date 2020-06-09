package _algorithm.sort;

import java.util.Arrays;

public class BubbleSort {

    public static void main(String[] args) {

        int[] raw = SortUtils.generator(6,1,10);
        System.out.println(Arrays.toString(raw));
        bs(raw);
        System.out.println(Arrays.toString(raw ));
    }

    public static void bs(int[] raw){
        for (int i = 1; i < raw.length ; i++) {
            for (int j = 0; j < raw.length - i ; j++) {
                if(raw[j] > raw[j+1])
                    SortUtils.switchNumber(raw,j,j+1);
            }
        }
    }
}
