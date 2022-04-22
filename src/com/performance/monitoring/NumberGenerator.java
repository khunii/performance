package com.performance.monitoring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

/*
 * Java VisualVM으로 분석하는 중 프로그램이 자주 종료된다면 run()에 있는 for 루프의 수행 횟수를 늘리면 된다.
 * 또는 jstat으로 보게 되면 minorGC 보는게 재미있는 예제이다.
 */
public class NumberGenerator {
    public void generateNumbers(){
        Random random = new Random();
        ArrayList<HashSet<Integer>> list = new ArrayList<>(1000);
        for(int loop = 1; loop <= 1000; loop++){
            HashSet<Integer> numberSet = new HashSet<Integer>();
            while(numberSet.size() < 6){
                numberSet.add(random.nextInt(45));
            }
            list.add(numberSet);
        }
        System.out.println(list.get(random.nextInt(1000)));
    }

    public void run(){
        try {
            System.out.println("Started NumberGenerator...");
            for(int loop = 1; loop <= 1000; loop++){
                Thread.sleep(500);
                System.out.println("Run count="+loop);
                generateNumbers();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        NumberGenerator generator = new NumberGenerator();
        generator.run();
    }
}
