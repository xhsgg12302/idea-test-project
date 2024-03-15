package _base.str.test;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @date 2024/3/11
 *                          @since  1.0
 *                          @author 12302
 *
 */
public class StringTest {
    public static void main(String[] args) {
        String str2 = new String("str")+new String("01"); // 创建string对象 0x02912。
        str2.intern(); // jdk1.7及以下。 str2.intern()如果常量池中没有的话，会创建 str2 0x02912。 . 所以 str2.intern() == str2
        String str1 = "str01"; // 现在常量池中有了，直接返回引用 0x02912。
        System.out.println(str2==str1); // 所以相等


        String str3 = new String("hel") + new String("lo");

        String str4 = new String("he") + new String("llo");

        String str3Tmp = str3.intern();
        String str4Tmp = str4.intern();
        System.out.println();
        System.out.println(str3.equals(str4));
    }
}
