package site.wtfu.framework.web.test;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;

public class WebDataBinderDemo {

    public static void main(String[] args) throws BindException {

        Person person = new Person();
        WebDataBinder binder = new WebDataBinder(person, "personName");


        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();

        mutablePropertyValues.add("!name","hello");
        mutablePropertyValues.add("age",19);
        mutablePropertyValues.add("!age",23);

        binder.bind(mutablePropertyValues);

        //Map<?, ?> map = binder.close();

        System.out.println(person);

        //System.out.println(map);

    }

}
