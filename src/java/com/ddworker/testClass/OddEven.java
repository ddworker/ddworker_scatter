package com.ddworker.testClass;

public class OddEven {


    /**
     * ��λ�����ж��Ƿ�Ϊ����
     *
     * @param num
     * @return
     */
    private static boolean oddEven(int num) {
        //����ture,Ϊ��������Ϊż��
        //��������Ƕ����Ʋ���
        //���� Ϊ��λ������
        //�˴��������ƶ�һλ��Ȼ�������ƶ�һλ���ж��Ƿ���ԭ�����
        //��������Ϊż������Ϊ��
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

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        int a = 10;
        System.out.println(oddEven(a));
        System.out.println(oddEven2(a));
    }

}
