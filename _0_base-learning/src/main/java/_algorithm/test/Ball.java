package _algorithm.test;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/6/18
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class Ball {

    public static void main1(String[] args) {
        int[][] points = new int[][]{ {10,16}, {2,8}, {1,6},{7,12}};
        points = new int[][]{ {1,2}, {2,3}, {3,4},{4,5}};

        // sort points
        int[] min; int pos;
        for (int i = 0; i < points.length; i++) {
            min = points[i];
            pos = i;

            for (int j = i + 1; j < points.length; j++) {
                if(min[1] > points[j][1]){
                    min = points[j];
                    pos = j;
                }
            }

            int[] temp = points[i];
            points[i] = min;
            points[pos] = temp;
        }

        System.out.println();
        int ans = reversePoints(points);
        System.out.println(ans);
    }

    private static int reversePoints(int[][] points) {
        if(points.length == 0) return 0;

        int pos = points[0][1];
        int[][] _new = new int[points.length][2]; int cur = 0;
        for (int i = 1; i < points.length; i++) {
            if(points[i][0] > pos){
                _new[cur] = points[i];
                cur++;
            }
        }

        int[][] array = Arrays.stream(_new).filter(it -> it[0] != 0 && it[1] != 0).toArray(int[][]::new);
        return 1 + reversePoints(array);
    }

    public static int findMinArrowShots(int[][] points) {
        Arrays.sort(points, Comparator.comparingLong(a -> a[1]));

        if(points.length == 0) return 0;
        int count = 1;

        int posi = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if(points[i][0] > posi){
                count++;
                posi = points[i][1];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] points = new int[][]{ {10,16}, {2,8}, {1,6},{7,12}};
        //points = new int[][]{ {1,2}, {2,3}, {3,4},{4,5}};
        System.out.println(findMinArrowShots(points));
    }
}
