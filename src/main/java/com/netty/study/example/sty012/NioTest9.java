package com.netty.study.example.sty012;

import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;

public class NioTest9 {
    /**
     * 内存映射  使用的是堆外内存
     * 将磁盘文件映射到堆外内存，由操作系统进行操作
     * 查看下jdik文档
     * @param args
     */
    public static void main(String[] args) throws  Exception{

        RandomAccessFile randomAccessFile = new RandomAccessFile("NioTest9.txt", "rw");

        FileChannel fileChannel = randomAccessFile.getChannel();


        fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, 5);


    }
}
