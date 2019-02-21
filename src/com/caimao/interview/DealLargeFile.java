package com.caimao.interview;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 新网银行面试题2019年2月，java处理40G文件，每行可读取
 * 思路：使用NIO的API将文件分隔成小文件，再依次处理（可用多线程）
 */
public class DealLargeFile {
    private static AtomicInteger atomicInteger = new AtomicInteger();

    public static void splitLargeFile(String filePath, String destPath) throws IOException {
        long beginTime = System.currentTimeMillis();
        FileInputStream fin = new FileInputStream(filePath);
        FileChannel fch = fin.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(128*1024*1024);//分割成128M大小的文件
        while(true){
            buffer.clear();
            int flag = fch.read(buffer);
            if(flag == -1){
                break;
            }
            buffer.flip();
            FileOutputStream fout = new FileOutputStream(destPath+"\\"+atomicInteger.incrementAndGet()+".txt");
            FileChannel fchannelOut = fout.getChannel();
            fchannelOut.write(buffer);
        }
        System.out.println("splitLarge cost:"+(System.currentTimeMillis()-beginTime)+" millSec");
    }

    public static void dealWithSubFile(String subFilePath){
        long beginTime = System.currentTimeMillis();
        File file = new File(subFilePath);
        if(!file.isDirectory()){
            System.out.println("文件");
            System.out.println("path="+file.getPath());
            System.out.println("AbsolutePath="+file.getAbsolutePath());
            System.out.println("name="+file.getName());
        }else if(file.isDirectory()){
            System.out.println("文件夹");
            String[] fileList = file.list();
            for(int i=0; i<fileList.length; i++){
                File subFile = new File(subFilePath+"\\"+fileList[i]);
                if(!subFile.isDirectory()){
                    System.out.println("path="+subFile.getPath());
                    System.out.println("AbsolutePath="+subFile.getAbsolutePath());
                    System.out.println("name="+subFile.getName());
                    BufferedReader in = null;
                    try{
                        String inputfile = subFilePath+"\\"+subFile.getName();
                        BufferedInputStream bis = new BufferedInputStream(
                                new FileInputStream(new File(inputfile)));
                        in = new BufferedReader(new InputStreamReader(bis, "utf-8"), 50*1024*1024);
                        while(in.ready()){
                            System.out.println(in.readLine());
                        }
                    }catch(IOException e){
                        e.printStackTrace();
                    }finally {
                        try{
                            in.close();
                        }catch(IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        System.out.println("dealWithSubFile cost:"+(System.currentTimeMillis()-beginTime)+" millSec");
    }
}
