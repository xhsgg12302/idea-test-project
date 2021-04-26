package _base.lambda.stream.model;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Objects;

/**
 * Copyright 2018 ...com Inc. All Rights Reserved.
 *
 * @author: 12302
 * @Date: 2019-10-01
 * @Desc:
 */
public class Person {

    private Long id;

    private String name;

    private int age;

    private BigDecimal forceValue;

    private int numberOfWar;

    private String address;

    public Person() {
    }

    public Person(Long id, String name, int age, BigDecimal forceValue, int numberOfWar, String address) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.forceValue = forceValue;
        this.numberOfWar = numberOfWar;
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                numberOfWar == person.numberOfWar &&
                Objects.equals(id, person.id) &&
                Objects.equals(name, person.name) &&
                Objects.equals(forceValue, person.forceValue) &&
                Objects.equals(address, person.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age, forceValue, numberOfWar, address);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", forceValue=" + forceValue.setScale(2,BigDecimal.ROUND_HALF_UP) +
                ", numberOfWar=" + numberOfWar +
                ", address='" + address + '\'' +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public BigDecimal getForceValue() {
        return forceValue;
    }

    public void setForceValue(BigDecimal forceValue) {
        this.forceValue = forceValue;
    }

    public int getNumberOfWar() {
        return numberOfWar;
    }

    public void setNumberOfWar(int numberOfWar) {
        this.numberOfWar = numberOfWar;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress(Person person) {
        return null;
    }

    public String getAddress(HashMap o) {
        return null;
    }
}
