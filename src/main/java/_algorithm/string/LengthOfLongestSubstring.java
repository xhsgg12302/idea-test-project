package _algorithm.string;


import java.util.HashMap;
import java.util.Map;

/**
 *  "abcabcebb"
 *  question：求最长子字符长度，子字符不包含重复字母
 *
 *  滑动窗口
 */
public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("abba"));
    }

    public static int lengthOfLongestSubstring(String string){
        if(string.length() == 0 ) return 0;
        Map<String,Integer> ans = new HashMap<>();
        int i=0,j=1,max=0;String temp;
        while(j<=string.length()){
            temp = string.substring(i,j);
            if(!isRepeat(temp)){
                max = temp.length();

            }else{
             i++;
            }
            j++;
        }
        return max;
    }

    public static boolean isRepeat(String str){
        Map<Character,Integer> flag = new HashMap<>();
        for(int i = 0; i < str.length() ; i ++ ){
            flag.put(str.charAt(i),i);
        }
        return str.length()!=flag.size();
    }

    public static  int lengthOfLongestSubstring1(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = map.get(s.charAt(i)) + 1;
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);
        }
        return max;

    }

}
