package com.performance.monitoring;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/*
 * lsof -c java 로 어떤 파일을 읽고 쓰고 있는지 확인
 */
public class LsofSample {
    String fileName;
    public LsofSample(String fileName){
        this.fileName = fileName;
    }

    public static void main(String[] args) {
        LsofSample sample = new LsofSample("sample.txt");
        sample.ioJob();
    }

    private void ioJob(){
        long runCount= 0;
        while(true){
            runCount++;
            String tempFileName = runCount + "_" + fileName;
            System.out.println(tempFileName + "--> ");
            writeFile(tempFileName);
            readFile(tempFileName);
            deleteFile(tempFileName);
        }
    }
    private void writeFile(String tempFileName){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(tempFileName));){
            System.out.println("Writing...");
            for(int loop = 0; loop < 100; loop++){
                bw.write(loop);
                bw.newLine();
                Thread.sleep(100);
            }
        }catch(IOException | InterruptedException e){
            e.printStackTrace();
        }
    }

    private void readFile(String tempFileName){
        try{
            System.out.println("Reading...");
            File readFile = new File(tempFileName);
            Scanner scanner = new Scanner(readFile);
            while(scanner.hasNextLine()){
                String fileData = scanner.nextLine();
                Thread.sleep(100);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void deleteFile(String tempFileName){
        try{
            File deleteFile = new File(tempFileName);
            deleteFile.delete();
            System.out.println("Deleted");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
