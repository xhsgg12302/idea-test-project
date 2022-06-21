package _draft.test;

import java.util.Scanner;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/10
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        char[] target = sc.nextLine().toCharArray();
        char ch = sc.nextLine().toUpperCase().toCharArray()[0];

        int ans = 0;
        boolean alpha = ch >= 'A' && ch <= 'z';

        for (char c : target){
            if(alpha){
                if(c == ch || c == ch + 32){ ans ++ ;}
            }else{
                if(c == ch ) { ans ++;}
            }
        }
        System.out.println(ans);

    }
}
