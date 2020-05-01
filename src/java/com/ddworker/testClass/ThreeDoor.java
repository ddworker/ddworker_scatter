package com.ddworker.testClass;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;


public class ThreeDoor {

    /**
     * 有三个门A、B、C，三个门中有一个门后面有一辆汽车，另外两个门后面一无所有。现在让一个人来选，如果他选的门后面有汽车，他将得到汽车；如果他选择的门后面一无所有，他将一无所得。
     * 现假定张三选了C门（不论C门后面有否汽车，A、B两门后总有一门后面没有汽车），主持人知道了每个门后面的情况，于是他打开了A、B两门中的一个，设为B，门后没有汽车；
     * 对于主持人来说，没有告诉张三任何信息。但主持人又告诉张三，选择者现在还可能改变选择，即在C门和未打开的A门之间选择，问，张三应不应该改变他的选择？
     *
     */
    @Test
    public void threeDoorRate() {
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入要循环的次:");

        int loops = Integer.valueOf(sc.next());
        loops = loops >= 100 ? loops : 100;

        final List<String> doors = Arrays.asList("A","B","C");
//        final String[] doors = {"A","B","C"};
        int goal,firstChoose,subOneDoor ,targetChoose = -1;
//        List<String> noC1Door = new ArrayList<>();
//        List<String> lastDoor = new ArrayList<>();
        int i = 0,j = 0,n = 0;
        double rate;
        do {
            goal = (int) (Math.random() * doors.size()); //汽车所在门
            firstChoose = (int) (Math.random() * doors.size()); //选手选中门

            //去掉选中门
            do {
                subOneDoor = (int) (Math.random() * doors.size());
            }while (subOneDoor == goal || subOneDoor == firstChoose);

            //当第一次选择等于目标车辆后，下一次换门必定没有车（此时概率 1/3）
            //当第一次未选中目标车辆，下一次换门必定右侧（去掉了一年没有的），此时概率（3/2）
            if(goal != firstChoose){
                n++;
            }

            //替换选择
//            while(targetChoose == subOneDoor || targetChoose == firstChoose || targetChoose == -1){
//                targetChoose = (int) (Math.random() * doors.length);
//                if(targetChoose == goal){
//                    j++;
//                }
//            }

            i++;
        } while (i < loops);

        rate = Math.round(n*100/loops) /100.0;
        System.out.println(loops);
        System.out.println(rate);
    }
}
