package com.netty.study.example.sty016;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.nio.Buffer;

public class ByteBufSty001 {

    public static void main(String[] args){
        //非池化的
        ByteBuf byteBuf = Unpooled.buffer(10);
        for(int i=0;i< 10;i++){
            byteBuf.writeByte(i);
        }
        for(int i=0;i<10;i++){
            System.out.println(byteBuf.getByte(i));
        }
        //相对。每次都进行读索引加1
        for(int i = 0; i< byteBuf.capacity(); i++){
            System.out.println(byteBuf.readByte());
        }
        /**
         * 三种类型的
         * heapbytebuf
         * direactebytebuf
         * compositebytebuf -- 复合buffer
         *
         * 通过索引访问，不会改变bytebuf 的读 和写索引
         * 可以同readbbyte writebyte来改变 读写索引
         * readindex 和 writeindex 也可以改变读写索引
         */

    }
}
