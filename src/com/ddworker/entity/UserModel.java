package com.ddworker.entity;

import java.util.Objects;

/**
 * 用户实体类
 */

public class UserModel implements Cloneable {
    public String name;
    public int num;
    public int age;
    public double salary;
    public Address addr;

    public UserModel() {
    }

    public UserModel(String name, int num, int age, double salary) {
        this.name = name;
        this.num = num;
        this.age = age;
        this.salary = salary;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        /**
         * 验证地址是否相同
         */
        if (this == o) return true;
        /**
         * 验证是否为null ，两个对象类型是否一致
         */
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        /**
         * 返回对象中的属性对比结果
         */
        return this.num == userModel.num &&
                this.age == userModel.age &&
                Double.compare(userModel.salary, this.salary) == 0 &&
                this.name.equals(userModel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, num, age, salary, addr);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "name='" + name + '\'' +
                ", num=" + num +
                ", age=" + age +
                ", salary=" + salary +
                ", address=" + addr.toString() +
                '}';
    }

    /**
     *  克隆方法仅支持标准数据类型，浅克隆
     *  实体类中的非基本数据类型，则克隆的是指向的地址，因此改变一个值（非基本类）会导致克隆和非克隆的数据同时发生变化
     *  现在需要深备份，将非基本数据类型是手动实现克隆方法
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public Object clone() throws CloneNotSupportedException {
        Object obj = super.clone();
        Address cloneAddress=(Address) this.addr.clone();
        ((UserModel) obj).addr = cloneAddress;
        return obj;
    }

}

