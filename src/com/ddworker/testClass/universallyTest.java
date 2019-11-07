package com.ddworker.testClass;

import com.ddworker.entity.Address;
import com.ddworker.entity.Student;
import com.ddworker.entity.Teacher;
import com.ddworker.entity.UserModel;

public class universallyTest {

    /**
     * 通用测试方法
     * @param args
     */
    public static void main(String[] args) {
        //三特征验证
        try {
            threeRule();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证三特征：
     * 1.封装
     * 2.继承
     * 3.多态
     */
    public static void threeRule() throws CloneNotSupportedException {
        /*封装*/
        UserModel um = new UserModel("张三",1,23);
        UserModel um1 = new UserModel("李四",2,20);
        um.age = 24;
        um.setAge(25);
        Address addr = new Address();
        addr.addr = "深圳";
        addr.type = "家";
        um.addr = addr;
        UserModel um2 = (UserModel) um.clone(); // 复制

        um2.addr.type = "常住地";

        System.out.println(um.toString());
        System.out.println(um2.toString());
        System.out.println(um.getName() + "的年龄为：" + um.age);
//        System.out.println(um.getClass()); //获取class类型的对象
//        System.out.println(um.equals(um1)); //判断是否相等
//        System.out.println("um对象的哈希码为："+um.hashCode());
        /* 继承*/

        Student stud = new Student("李雷",4,13,"初一",3);
        System.out.println(stud instanceof UserModel); //判断是否是属于UserModel
        stud.doSomeThing();

        /*多态*/
        UserModel um3 = (UserModel) um1.clone();
        System.out.println(um3);
        UserModel stud1 = new Student("韩梅梅",5,13,"初一",2);
        UserModel teach = new Teacher("Tony",6,30,6000.00);
        um3.doSomeThing();
        stud1.doSomeThing();
        teach.doSomeThing();

    }

}
