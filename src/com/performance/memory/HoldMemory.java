package com.performance.memory;

import java.util.HashMap;

/*
 * memory 단면 추출해서 실습할 때 사용
 * java -agentlib:hprof=heap=dump,format=b,file=heapdump.hprof.doe=n HoldMemory
 * 이렇게 실행 후 Ctrl+break or Ctrl+\ (윈도우즈에서) 키를 누르면 메모리 단면 파일 생성
 * 유닉스나 리눅스 기반 시스템에서는 새로운 터미널 창을 열고, ps 나 jps 로 프로세스 id를 확인하고
 * kill -3 을 수행하는 방법으로 메모리 단면을 만들 수 있다.
 * ex) java -agentlib:hprof=heap=dump,format=b,file=heapdump.hprof.doe=n HoldMemory
 *
 * 이외에도 jmap의 옵션을 바꿔 가면서 jmap으로 메모리 단면을 얻는 것도 실습
 */
public class HoldMemory {
    private final static HashMap<String, String> leakMap = new HashMap<>();
    private final static String STORE_DATA = "STORE DATA";

    public static void main(String[] args){
        HoldMemory holdMemory = new HoldMemory();
        holdMemory.addObject(50000);
        try{
            System.out.println("Holding Memory. It will be stopped after 10 min.");
            Thread.sleep(600000); //waiting for 10 min
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void addObject(int objectCount){
        int mapSize = leakMap.size();
        int maxCount = mapSize + objectCount;
        for(int loop = mapSize; loop < maxCount; loop++){
            leakMap.put(STORE_DATA + loop, STORE_DATA);
        }
    }
}
