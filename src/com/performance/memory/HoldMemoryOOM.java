package com.performance.memory;

import java.util.HashMap;

/*
 * OOM시 Heap dump 추출을 위한 실습
 * java -Xms128m -Xmx128m -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=/tmp HoldMemoryOOM
 * java -XX:OnOutOfMemoryError="gcore -o gcore.dump %p" HoldMemoryOOM
 * 위에서 %p 는 자바 프로세스 아이디를 의미하는 예약어이다.
 */
public class HoldMemoryOOM {
    private final static HashMap<String, String> leakMap = new HashMap<>();
    private final static String STORE_DATA = "STORE DATA";

    public static void main(String[] args){
        HoldMemoryOOM holdMemoryOOM = new HoldMemoryOOM();
        holdMemoryOOM.addObject(50000);
        try{
            System.out.println("Holding Memory. It will be stopped after 10 min.");
            Thread.sleep(600000); //waiting for 10 min
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    StringBuffer sb = new StringBuffer();
    public void addObject(int objectCount){
        int mapSize = leakMap.size();
        int maxCount = mapSize + objectCount;
        for(int loop = mapSize; loop < maxCount; loop++){
            leakMap.put(STORE_DATA + loop, STORE_DATA + loop*5);
            sb.append(STORE_DATA);
        }
    }
}
