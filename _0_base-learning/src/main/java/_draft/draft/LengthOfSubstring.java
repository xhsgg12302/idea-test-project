package _draft.draft;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-02-09
 * @Desc:
 */
public class LengthOfSubstring {
    public static int lengthOfLongestSubstring(String s) {
        int length = 0;
        while(s.length() >= length){
            int effect = findEffect(s);
            if(length < effect){
                length = effect;
            }
            s = s.substring(length);
        }
        return length;
    }

    public static int findEffect(String originstr){
        char[] origin = originstr.toCharArray();
        int length = origin.length;
        Map map = new HashMap<>();
        for(int i = 0 ;i < origin.length ;i ++){
            map.put(origin[i],""); // i=0 ,size=1  i=1;size=2 ;i=2;size=3
            if(map.size()<=i){
                length = map.size();
                break;
            }
        }
        return length;
    }

    public static void main(String[] args) {
        String temp = "abcabcbzxvb";
        //String temp = " ";
        //System.out.println(temp.length());
        //System.out.println(temp.substring(1));
        System.out.println(lengthOfLongestSubstring(temp));

    }
}
