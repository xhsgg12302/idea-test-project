package _base.i18n;

import java.util.Locale;
import java.util.ResourceBundle;


public class Demo {

    public static void main(String[] args) {
        Locale localeEn = new Locale("en", "US");
        Locale localeZh = new Locale("zc", "CNN");
        ResourceBundle res = ResourceBundle.getBundle("content");
        String hello = res.getString("hello");
        System.out.println("hello = " + hello);
        System.out.println("你好");

    }
}
