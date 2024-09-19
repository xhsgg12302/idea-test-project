package _base.modifier.test3.p0;


import _base.modifier.test3.p1.Son1;
import _base.modifier.test3.p2.Son2;

public class Father {

    protected void f(){}

    public void test01(){
        Son1 son1 = new Son1();
        son1.f();

        Son2 son2 = new Son2();
        //son2.f();
    }
}
