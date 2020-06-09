package _algorithm.sort;

import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

public class SortUtils {
    private static Random random = new Random();

    public static int[] generator(int n ,int min, int max){
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = random.nextInt(max-min) + min;
        }
        return ints;
    }

    public static int[] generator2(int n ,int min, int max){
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = (int)(Math.random() * (max - min)) + min;
        }
        return ints;
    }


    public static void switchNumber1(int[] raw,int x, int y){
        int temp = raw[x];
        raw[x] = raw[y];
        raw[y] = temp;
    }

    public static void switchNumber(int[] raw, int x, int y ){
        raw[x] = raw[x] ^ raw[y];
        raw[y] = raw[x] ^ raw[y];
        raw[x] = raw[x] ^ raw[y];
    }

    @Test
    public void test(){
        System.out.println(Arrays.toString(generator(10,1,100)));
    }

    @Test
    public void test2(){
        System.out.println(Arrays.toString(generator2(10, 5, 8)));
    }



}
