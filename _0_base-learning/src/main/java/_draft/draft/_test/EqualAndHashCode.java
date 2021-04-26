package _draft.draft._test;

import java.util.ArrayList;
import java.util.HashMap;

public class EqualAndHashCode {
    public static void main(String[] args) {
        String st1 = "通话";
        String st2 = "重地";

        String strnew = new String();
        ArrayList<Object> objects = new ArrayList<>();
        ArrayList<Object> objects1 = new ArrayList<>();

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();


        System.out.println(st1.hashCode());
        System.out.println(st2.hashCode());
        System.out.println(strnew.hashCode());
        System.out.println(objects.hashCode());
        System.out.println(objects1.hashCode());
        System.out.println(objectObjectHashMap.hashCode());
    }
}
