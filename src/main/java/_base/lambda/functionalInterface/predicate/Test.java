package _base.lambda.functionalInterface.predicate;

import java.util.function.Predicate;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-02
 * @Desc:
 */
public class Test {
    public static void main(String[] args) {
        Predicate<String> p = o -> o.equals("_draft/test");
        Predicate<String> g = o -> o.startsWith("t");

        /**
         * negate: 用于对原来的Predicate做取反处理；
         * 如当调用p._draft.test("_draft.test")为True时，调用p.negate()._draft.test("_draft.test")就会是False；
         */
        //Assert.assertFalse();
        System.out.println(p.negate().test("_draft/test"));

        /**
         * and: 针对同一输入值，多个Predicate均返回True时返回True，否则返回False；
         */
        //Assert.assertTrue(p.and(g)._draft.test("_draft.test"));
        System.out.println(p.and(g).test("_draft/test"));

        /**
         * or: 针对同一输入值，多个Predicate只要有一个返回True则返回True，否则返回False
         */
        //Assert.assertTrue(p.or(g)._draft.test("ta"));
        System.out.println(p.or(g).test("ta"));
        //Assert.isTrue(p.negate()._draft.test("_draft.test"),"test不为真");
    }
}
