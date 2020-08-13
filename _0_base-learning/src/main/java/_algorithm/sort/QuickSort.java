package _algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {


    public void quickSort(int[] raw, int left, int right) {
        if (left >= right) return;
        int value = raw[left];
        int left_index = left;
        int right_index = right;

        boolean flag = true;

        while (left_index != right_index) {
            if (flag) {
                //先填right_index；
                if (value > raw[right_index]) {
                    raw[left_index] = raw[right_index];
                    left_index++;
                    flag = false;
                } else {
                    right_index--;
                }
            } else {
                if (value < raw[left_index]) {
                    raw[right_index] = raw[left_index];
                    right_index--;
                    flag = true;
                } else {
                    left_index++;
                }
            }
        }
        raw[left_index] = value;
        quickSort(raw, left, left_index - 1);
        quickSort(raw, left_index + 1, right);
    }


    @Test
    public void test() {
        int[] ints = SortUtils.generatorByMathRandom(5, 1, 100);
        System.out.println(Arrays.toString(ints));
        quickSort(ints, 0, ints.length - 1);
        System.out.println(Arrays.toString(ints));
    }

    @Test
    public void performance() {
        int[] generator = SortUtils.generatorByMathRandom(80000, 0, 800000000);
        System.out.println(SortUtils.performanceByStopWatch((ints) -> quickSort(generator, 0, generator.length - 1)) + "ms"); //12 ms
    }
}
