package _draft.test;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class TestSPI {

    public static void main1(String[] args) {

        //System.out.println("hello world");

        //System.out.println(getMap().get("KEY").toString());

        String s = new String("hello");
        modify(s);
        System.out.println(s);

    }


    public static Map<String, String> getMap() {
        Map<String, String> map = new HashMap<String, String>();
        map.put("KEY", "INIT");

        try {
            map.put("KEY", "TRY");
            return map;
        }
        catch (Exception e) {
            map.put("KEY", "CATCH");
        }
        finally {
            map.put("KEY", "FINALLY");
            System.out.println(map.hashCode());
            map = null;
        }

        return map;
    }

    public static void modify (String s){
         s +=" world";
    }

    public static String output = "";
    public static void foo(int i){
        try{
            if(i==1){
                throw new Exception();
            }
            output += "1";
        }catch (Exception e){
            output += "2";
            return;
        }finally {
            output += "3";
        }
        output +="4";
    }


    public static void main(String[] args) {

        Enumeration<Driver> drivers = DriverManager.getDrivers();


        foo(0);
        System.out.println(output);
        foo(1);
        System.out.println(output);
    }

}
