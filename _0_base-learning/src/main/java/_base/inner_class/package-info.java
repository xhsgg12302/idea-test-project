/**
 *
 * 内部类种类和使用方式
 * <p>
 *      一,静态内部类 {@link _base.inner_class.TestStaticClass }.
 *          1，定义时加上static 关键字
 *          2，不能和外部类名字相同
 *          3，被编译成一个完全独立的class文件（OuterClass$InnerClass.class)
 *          4，可以访问外部类静态方法和成员，包括私有的
 *          5，生成方式：OutClass.InnerClass inner = new OutClass.InnerClass();
 * </p>
 * <p>
 *      二,成员内部类 {@link _base.inner_class.TestMemberClass }
 *          1，静态内部类去掉static关键字就是成员内部类
 *          2，成员内部类和静态内部类可以类比非静态的成员变量和静态的成员变量
 *          3，成员内部类就像一个实例变量
 *          4，它可以访问它的外部类的所有成员变量和方法，不管时静态的还是非静态的
 *          5，在外部类里面创建成员内部类的实例： this.new InnerClass();
 *          6，在外部类之外创建内部类的实例： (new OuterClass()).new InnerClass();
 * </p>
 * <p>
 *      三,局部内部类 {@link _base.inner_class.TestLocalClass }
 *          1，局部内部类定义在方法中，比方法的范围还小。是内部类种种那个最少用到的一种类型。
 *          2，像局部变量一样，不能被public，protected，private，static修饰
 *          3，只能访问方法中定义的final类型的局部变量
 *          4，局部内部类在方法中定义，所以只能在方法中使用，即只能在方法中生成局部内部类实例并且调用其方法
 * </p>
 * <p>
 *
 *      四，匿名内部类 {@link }
 *          1，使用比较多的是作为一个方法参数
 *          2，生成OutClass$1.class,数字根据第几个匿名类生成
 * </p>
 *
 *
 *
 *  java内部类可以实现多继承
 *  1,当某个类除了它的外部类，不再被其他的类使用时
 *  2，解决一些非面向对象的语句块
 *  3，多算法场合
 *  4，适当使用，使代码更加灵活，可扩展性强
 *
 *
 *
 */
package _base.inner_class;