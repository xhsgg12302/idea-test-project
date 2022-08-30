package _draft.test;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/8/26
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Demo {
    public static void main(String[] args) {

        String path = "UDDDUDUU";

        String path2 = "DDUUUUDD";


        System.out.println(count(path2));
        System.out.println(countingValleys(path2));
    }


    // 可以使用数组来实现
    // DU,DDUU,DDDUUU记为山谷,
    // UD,UUDD,UUUDDD记为山峰
    public static int countingValleys(String path){

        List<Character> list = new ArrayList<Character>();



        int result = 0;
        char[] chars = path.toCharArray();




        for (int i = 0; i < chars.length; i++) {
            if (list.size() == 0){
                list.add(chars[i]);
            }else{
                // 如果遍历得到的下一个与最后一个相同的话，继续追加到list
                if(chars[i] == list.get(list.size() -1 )){
                    list.add(chars[i]);
                }else{
                    // 如过是相对的话，记录是U-D，还是D-U，也就是 山峰或山谷
                    if(chars[i] == 'U'){
                        // 另外一个为D，表示山谷 result++;list 回退一个
                        result++;

                        // 还得处理 多对值的情况。。
                        list.remove(list.size() - 1);
                        if(list.size() == 0 && i == chars.length -1){ break;}
                    }else {
                        // 直接回退
                        list.remove(list.size() -1);
                    }
                }
            }
        }

        return result;
    }

    public static int count(String path){
        boolean flag = false;
        int result = 0;

        for (int i = 0; i < path.length(); i++) {
            if(flag && path.charAt(i) == 'U'){
                result ++;
                flag = false;
            }
            if(path.charAt(i) == 'D'){
                flag = true;
            }
        }
        return result;
    }
}
