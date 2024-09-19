package _base.modifier.test1.p1;

import _base.modifier.test1.p0.Father1;

public class Son1 extends Father1 {

    @Override
    protected void f() {
        try {
            super.f();
            this.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

}
