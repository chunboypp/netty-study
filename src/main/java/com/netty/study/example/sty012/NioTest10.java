package com.netty.study.example.sty012;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NioTest10 {
    /**
     * 文件锁的演示
     * @param args
     */
    public static void main(String[] args) throws  Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();


        fileChannel.lock(3,6,true);


    }
}
