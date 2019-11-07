package com.ddworker.entity;

import java.util.Objects;

public class Teacher extends  UserModel {

    public double salary;

    public Teacher(String name, int num, int age, double salary) {
        super(name, num, age);
        this.salary = salary;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teacher)) return false;
        if (!super.equals(o)) return false;
        Teacher teacher = (Teacher) o;
        return Double.compare(teacher.salary, salary) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), salary);
    }

    @Override
    public void doSomeThing(){
        System.out.println("I'm a teacher and my name is " + super.name);
    }
}
