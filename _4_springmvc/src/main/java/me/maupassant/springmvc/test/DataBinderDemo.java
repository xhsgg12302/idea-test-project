package me.maupassant.springmvc.test;

import me.maupassant.springmvc.entity.Employee;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.validation.BindException;
import org.springframework.validation.DataBinder;

import java.util.Map;

public class DataBinderDemo {
    public static void main(String[] args) throws BindException {
        Employee employee = new Employee();
        DataBinder binder = new DataBinder(employee, "employee");
        MutablePropertyValues pvs = new MutablePropertyValues();
        pvs.add("name","hello");
        pvs.add("age",19);

        binder.bind(pvs);
        Map<?, ?> close = binder.close();

        System.out.println(employee);
        System.out.println(close);
    }
}
