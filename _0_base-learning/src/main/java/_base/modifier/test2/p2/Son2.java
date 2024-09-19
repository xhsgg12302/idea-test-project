package _base.modifier.test2.p2;

import _base.modifier.test2.p0.Father;
import _base.modifier.test2.p1.Son1;

public class Son2 extends Father {

    public void test01() {

        Father father = new Father();
        //father.f();

        Son1 son1 = new Son1();
        //son1.f();

        Son2 son2 = new Son2();
        son2.f();
        this.f();
    }
}
