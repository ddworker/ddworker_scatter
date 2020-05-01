package com.ddworker.entity;

import java.util.Objects;

public class Student extends UserModel{
    public String grade;
    public int classes;


    public Student() {
    }

    public Student(String name, int num, int age, String grade, int classes) {
        super(name, num, age);
        this.grade = grade;
        this.classes = classes;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public int getClasses() {
        return classes;
    }

    public void setClasses(int classes) {
        this.classes = classes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Student)) return false;
        if (!super.equals(o)) return false; //判断父类元素是否相等
        Student student = (Student) o;
        return classes == student.classes &&
                Objects.equals(grade, student.grade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), grade, classes);
    }

    @Override
    public String toString() {
        return "Student{" +
                "grade='" + grade + '\'' +
                ", classes=" + classes +
                ", name='" + name + '\'' +
                ", num=" + num +
                ", age=" + age +
                ", addr=" + addr.toString() +
                '}';
    }
   @Override
   public void doSomeThing(){
       System.out.println("I'm a student and I like study.");
    }
}
