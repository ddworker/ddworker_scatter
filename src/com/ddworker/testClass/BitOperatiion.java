package com.ddworker.testClass;

public class BitOperatiion {

    public static void main(String[] args) {
        int a = 5;
        System.out.println(7 & 7);
        System.out.println(oddEven(a));
        System.out.println(oddEven2(a));
    }


    /**
     * 移位运算判断是否为奇数
     *
     * @param num
     * @return
     */
    private static boolean oddEven(int num) {
        //返回ture,为奇数，否为偶数
        //计算机都是二进制操作
        //》《 为移位操作符
        //此处先向左移动一位，然后向右移动一位，判断是否与原数相等
        //如果相等则为偶，否则为奇
        //1001 -> 1  --> 100
        // 100 <- 1  --> 1000
        //10001 != 1000
        return num >> 1 << 1 != num;
    }

    /**
     * @param num
     * @return
     */
    private static boolean oddEven2(int num) {
        return (num & 1) == 1;
    }

}
