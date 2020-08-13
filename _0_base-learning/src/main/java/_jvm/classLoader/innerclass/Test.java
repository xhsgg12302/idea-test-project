package _jvm.classLoader.innerclass;

import java.lang.reflect.Field;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-12-06
 * @Desc:
 */
public class Test {
    public static void main(String[] args) {
        Test test = new Test();
        test.test("hello I am a judgement!");
    }

    public void test(String words){
        Person person = new Person() {
            @Override
            public void say(String word) {
                System.out.println(words);
            }
        };
        Class clazz = person.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //fields = clazz.getFields();
        for (Field field : fields) {
            //field.setAccessible(true);
            try {
                Object object = field.get(person);

                //System.out.println(field.toString());

                System.out.println("获取到的feild, name=" + field.getName() + ",   value=" + object);
                //打印内容
                    /*
                    * 获取到的feild, name=version,   value=iphone6s[是手机不是吃的苹果]
                      获取到的feild, name=date,   value=生产日期 2017-06-13
                    * */
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
