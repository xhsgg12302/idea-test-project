package _draft.test;

import org.junit.Test;

import java.lang.reflect.Field;

/**
 * 1,由此得出的结论是，子类会继承父类的所有东西，而修饰符只是影响属性或者方法对外是否可见。
 *
 * 2,Java官方文档的解释：子类不能继承父类的私有属性，但是如果子类中公有的方法影响到了父类私有属性，那么私有属性是能够被子类使用的。
 *
 * 3,从内存的角度来说，父类的一切都被继承(从父类构造方法被调用就知道了，因为new一个对象，就会调用构造方法，子类被new的时候就会调用父类的构造方法，
 *   所以从内存的角度来说，子类拥有一个完整的父类)。子类对象所引用的内存有父类变量的一份拷贝。
 */
public class TestPrivateSuperFeilds {

    @Test
    public void test() throws Exception{
        Son son = new Son();

        son.call();


        Class<? extends Son> clazz = son.getClass();
        /**
         * getField 只能获取public的，包括从父类继承来的字段。
         * getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。
         * (注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
         */
        //Field name = clazz.getSuperclass().getDeclaredField("name");
        //Field name1 = clazz.getDeclaredField("name");
        Field name = clazz.getField("age");
        name.setAccessible(true);
        System.out.println(name.get(son));
    }
}


class Father{
    private String name;
    public  Integer age;

/*    public father(){
        this.name="1";
    }*/

    public void call(){
        System.out.println(name + age);
    }
}

class Son extends Father{}
