package _base.lambda.closure;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-18
 * @Desc:
 */
public  class Programmer {
    private String name;

    public Programmer() {
        super();
    }

    public Programmer(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void work() {
        String programmer =" asdf";
        System.out.println(programmer);
        System.out.println(name + "正在编程");
    }
}

interface Teachable {
    public void work();
}
