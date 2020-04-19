package com.ddworker.testClass;

import java.util.Arrays;

/**
这道题里方法中修改值都是修改形参的值，区别是一个通过形参修改堆值，而另一个仅仅只是修改形参。
	
	详细分析一下：
	
	char[] ch = {'a','b','c'};是数组，数组存放在堆中，所以当方法通过形参修改值时会去堆中修改。当成员变量ch再去访问时，堆中的值已经修改，所以输出dbc。
	而String str = new String("hello");会进行两步操作：
	1、JVM先在堆中创建一个指定的String对象"hello"，并让str引用指向该对象。
	2、JVM会在常量池中 寻找或新建 一个”hello”，并让堆中对象与之关联。
	所以当方法为形参赋值时（str="world";），只是为形参在常量池中新建一个”world”并引用，也只修改了形参，成员变量str本身未被修改。
	
	所以，当new了除封装类型以外的对象时，即便通过形参修改对象内部的值也会去堆中修改。
	
	所以，当String str = new String("hello");时产生了一个String对象，如果常量池没有"hello"常量还会产生一个"hello"常量。
	
	
	Java的栈、堆、常量池和方法区。
 * 
 * @author ddWorker
 *
 */
public class ParamsTest{
    String str = new String("hello");
    String strWorld = "world"; //字符串常量池新增"world" ,验证fun()中,形参发生变化,新建了Object
    char[] ch = {'a','b','c'};
    public void fun(String str, char ch[]){
        System.out.println(str == this.str); //it's class member str
        //change params value
        str="world";
        System.out.println("Class.member: str "+this.str); //hello
        System.out.println("The Obejct str wasn't this class member:str <" + (str == this.str)
                +"> and It's a new Object that equals class member:strWorld: <" + (str == this.strWorld)+ ">");
        ch[0]='d';
        //print class member
        System.out.println("Class.member: ch "+Arrays.toString(this.ch)); //[d,b,c]
    }
    public static void main(String[] args) {
        ParamsTest test1 = new ParamsTest();
        test1.fun(test1.str,test1.ch);
        System.out.print(test1.str + " and ");
        System.out.print(test1.ch);
    }
}