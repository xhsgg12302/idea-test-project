package _framework.spring.cycle_reference;

/**
 * Copyright 2022 wtfu.site Inc. All Rights Reserved.
 *
 * @author: 12302
 * @date: 2022-05-12
 */
public class Student {

    private Teacher  teacher;

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}
