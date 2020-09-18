package com.netty.study.example.sty012;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest8 {
    /**
     * 堆内存 和堆外内存
     *
     * bytebuffer wrap(传入一个 byte[]数组) 返回一个bytebuffer
     * @param args
     *
     *
buffer对象中的属性。是diractbuytebuffer的父类
    // Used only by direct buffers
    // NOTE: hoisted here for speed in JNI GetDirectBufferAddress
    long address; 记录堆外内存的地址

     为什么使用堆外内存？
     因为在io的时候，堆上的数据 会拷贝到堆外的某一块区域中。再进行操作
     如果直接使用堆外内存，则不会有堆到堆外内存的拷贝，俗称零拷贝

     堆内存：
     操作系统为什么不访问堆内存呢，为什么要拷贝一份。通过jni访问堆内存，如果访问的过程中，发生了gc,有些垃圾收集器，会导致数据空间内数据的移动，
     这样就乱套了。 除非 数据不移动或不发生gc，这种假设几乎不存在。所以要拷贝数据饿到堆外内存进行操作。 数据再从堆内存拷贝到堆外内存的过程中
     jvm保证这个过程不会发生gc.堆外内存怎么回收，当堆上数据在被回收时，会找到对应的堆外内存进行回收，也就不存在内存泄漏


     */
    public static void main(String[] args) throws Exception{


        FileInputStream inputStream = new FileInputStream(new File(""));

        FileOutputStream outputStream = new FileOutputStream(new File(""));


        FileChannel inputChannel = inputStream.getChannel();
        ByteBuffer inputBytebuffer = ByteBuffer.allocateDirect(512);

        FileChannel outChannel = outputStream.getChannel();
        /**
         * 分配的是堆外内存
         */
        ByteBuffer outBytebuffer = ByteBuffer.allocateDirect(512);
        /**
         * 分配的堆内存
         */
        ByteBuffer.allocate(64);



    }

}
