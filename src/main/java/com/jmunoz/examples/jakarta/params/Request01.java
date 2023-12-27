package com.jmunoz.examples.jakarta.params;

import java.io.Serializable;

public class Request01 implements Serializable {

    private String name;
    private int age;
    private boolean isDeveloper;
    private double salary;
    private Request02 birthday;

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

    public boolean isDeveloper() {
        return isDeveloper;
    }

    public void setDeveloper(boolean developer) {
        isDeveloper = developer;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Request02 getBirthday() {
        return birthday;
    }

    public void setBirthday(Request02 birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "Request01{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", isDeveloper=" + isDeveloper +
                ", salary=" + salary +
                ", birthday=" + birthday +
                '}';
    }
}
