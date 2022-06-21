package _base.base.object_hashcode;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 * 
 * @date 2022/6/13
 *                          @since  1.0
 *                          @author 12302
 * 
 */
public class Test {

    public static void main(String[] args) {
        removeRepeat();
    }


    public static void demoEquals(){
        Student s1 = new Student("小方",12);
        Student s2 = new Student("小方",12);
        System.out.println(s1.equals(s2));
    }

    public static void removeRepeat(){
        Set<Student> set = new HashSet<>();

        Student s1 = new Student("小方",12);
        Student s2 = new Student("小方",12);
        set.add(s1);
        set.add(s2);
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        set.add(new Student("小方",12));
        System.out.println(set.size());
        for(Student s:set){
            System.out.println(s);
        }
        System.out.println(s1.equals(s2));
    }
}
