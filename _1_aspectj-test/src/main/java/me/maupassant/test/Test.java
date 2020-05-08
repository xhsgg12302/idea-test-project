package me.maupassant.test;

import me.maupassant.service.Hello;
import me.maupassant.service.World;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2020-01-09
 * @Desc:
 */
public class Test {

    public static void main(String[] args) {
        Hello hello = new Hello();
        hello.foo();
        hello.addUser("Tony","123456");
        World world = new World();
        world.bar();
    }
}
