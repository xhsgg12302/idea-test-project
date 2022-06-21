package _base.base.object_hashcode;

import java.util.Objects;

/**
 * Copyright https://wtfu.site Inc. All Rights Reserved.
 *
 * @author 12302
 * @date 2022/6/13
 * @since 1.0
 */
public class Student {

    String Name;
    int age;

    public Student(String Name, int age) {
        this.Name = Name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "Name='" + Name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return age == student.age &&
                Objects.equals(Name, student.Name);
    }
}
