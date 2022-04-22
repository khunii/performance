package com.performance.monitoring;

import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/*
 * 지정한 callCount만큼 http://localhost:8080/ 을 호출하는데 10초 간격이다.
 * 물론 tomcat이나 apache같은 서버가 실행되고 있어야 한다.
 * 이것을 띄워 놓고
 * sar 명령어를 실행하여 모니터링 (네트워크) 실습
 */
public class URLCall {
    public static void main(String[] args) {
        int callCount = 60;
        URLCall call = new URLCall();
        call.callMain(callCount);
    }
    public void callMain(int callCount){
        try{
            for(int i=1; i<callCount; i++){
                URL url = new URL("http","localhost", 8080, "/");
                URLConnection connection = url.openConnection();
                connection.connect();
                Map<String, List<String>> headers = connection.getHeaderFields();
                System.out.println(headers.keySet());
                System.out.println(".");
                if (i % 10 == 0){
                    System.out.println("-" + i);
                }
                Thread.sleep(10000);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
