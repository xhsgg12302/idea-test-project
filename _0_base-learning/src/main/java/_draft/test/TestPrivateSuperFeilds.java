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
 *
 *
 *
 * 4,父子类的关系：还可以参考 _draft.test.HelloWorld.java , 父类中使用this
 *
 */
public class TestPrivateSuperFeilds {

    @Test
    public void test() throws Exception{
        Son son = new Son();


        System.out.println(son);

        Class<? extends Son> clazz = son.getClass();
        /**
         * 经过测试完全正确,我自己都信了:
         * getField 只能获取public的，包括从父类继承来的字段。
         * getDeclaredField 可以获取本类所有的字段，包括private的，但是不能获取继承来的字段。
         * (注： 这里只能获取到private的字段，但并不能访问该private字段的值,除非加上setAccessible(true))
         */
        Field[] fields = clazz.getFields(); // email/isStrong [2]
        Field[] declaredFields = clazz.getDeclaredFields(); // email/age [2]

        // 下面的这行代码不行，是应为getField只获取public属性
        //Field name = clazz.getField("age");
        Field age = clazz.getDeclaredField("age");
        age.setAccessible(true);
        System.out.println(age.get(son));



    }

    public static void main(String[] args) throws Exception{
        //Son son = new Son("");

        //System.out.println();

        //Class<? extends Son> clazz = son.getClass();
        //Field name = clazz.getSuperclass().getDeclaredField("name");
        //name.setAccessible(true);
        //System.out.println(name.get(son));
        Son son = new Son();
        Son son1 = new Son();
    }
}


class Father{
    private String name = "is very good";//私有属性
    private char sex;
    public boolean isStrong;

    public Father(){
        System.out.println("called");
    }
    public Father(String name){
        this.name = name;
    }

    public void call(){
        this.name = "234";
        System.out.println(name+sex);
    }

    //private void speak(){
        //System.out.println(name+"is speaking!");
    //}
}

class Son extends Father{
    public String email ;
    private int age;

    public Son(){}
    public Son(String name){
        super(name);
    }
    public void ass(){
        call();
    }
}

class Son2 extends Father{
    
}
