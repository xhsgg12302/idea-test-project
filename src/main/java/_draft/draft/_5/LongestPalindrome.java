package _draft.draft._5;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-11
 * @Desc:
 */
public class LongestPalindrome {

    public static boolean isPalindrome(String origin){
        int len = origin.length();
        for (int i = 0; i < len/2; i++) {
            if(origin.charAt(i) != origin.charAt(len - i - 1)){
                return false;
            }
        }
        return true;
    }

    public static String getPalindrome(String origin){
        String ans="";
        int maxLen = 0;
        for (int i = 0; i < origin.length() ; i++) {
            for (int j = i + 1; j <= origin.length(); j++) {
                String temp = origin.substring(i,j);
                if(isPalindrome(temp) ){
                    System.out.println(temp);
                    if(temp.length() > maxLen){
                        ans = temp;
                        maxLen = temp.length();
                    }
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String test = "ababcbab";
        System.out.println(longestPalindrome7(test));
    }

    public static String longestPalindrome7(String s) {
        int n = s.length();
        String res = "";
        boolean[] P = new boolean[n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= i; j--) {
                P[j] = s.charAt(i) == s.charAt(j) && (j - i < 3 || P[j - 1]);
                if (P[j] && j - i + 1 > res.length()) {
                    res = s.substring(i, j + 1);
                }
            }
        }
        return res;
    }

}

