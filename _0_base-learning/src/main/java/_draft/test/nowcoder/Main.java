package _draft.test.nowcoder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/13
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Main {


    /**
     * 4 15 8 17 12 20 9 11 7 5 24 36 2
     * 4 15 8 17 12 20 9 11 5 7 24 36 2
     * @param args
     */
    public static void main(String[] args) {


        List<String> nums = new ArrayList<>();
        nums.add("5");
        nums.add("3");
        nums.add("1");
        nums.add("6");
        nums.add(0,"4");
        nums.remove(1);

        System.out.println(nums);
        Scanner scanner = new Scanner(System.in);
        int count,line = count = scanner.nextInt();
        int target[][] = new int[line][3];
        while(line-- > 0){
            for (int i = 0; i < 3; i++) {
                target[count - line - 1][i] = scanner.nextInt();
            }
        }
        Arrays.stream(target).forEach(it -> System.out.println(Arrays.toString(it)));
        int ans = 3001;
        for (int o = 0; o < 3; o++) {
            ans = Math.min(ans, solve(target, true, target[0][o], 0, o));
        }
        System.out.println(ans);
    }

    public static int solve(int[][] target, boolean isHead, int origin, int  row, int column){

        int ans = 3001;

        int des = isHead ? origin : target[row][column];

        if(row == target.length -1)  { return target[row][column];}
        int[] temp = target[row + 1];
        for (int i = 0; i < temp.length; i++) {
            if( column != i){
                if(row < target.length -1){
                    int solve = solve(target, false, temp[i], row + 1, i);
                    ans = Math.min(ans, des + solve);
                }else{
                    ans = temp[i];
                }
            }
        }
        return ans;
    }

}


class Test{
    static int x = 10;
    static{ x += 5;}

    public static void main(String[] args) {
        System.out.println("x=" + x);
        String classFile = "com.jd.".replaceAll("\\.","/") + "MyClass.class";
        System.out.println(classFile);
    }

    static{ x/=3;}
}
