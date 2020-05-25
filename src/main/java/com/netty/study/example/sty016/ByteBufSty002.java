package com.netty.study.example.sty016;

import com.google.common.base.Charsets;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import java.nio.charset.Charset;

public class ByteBufSty002 {

    public static void main(String[] args){
        ByteBuf byteBuf = Unpooled.copiedBuffer("hello  world", Charset.forName("utf-8"));

        //如果为真，则标示是一个堆上缓冲。
        if(byteBuf.hasArray()){
            byte[] content = byteBuf.array();
            System.out.println(new String(content, Charset.forName("utf-8")));
            //UnpooledByteBufAllocator$InstrumentedUnpooledUnsafeHeapByteBuf(ridx: 0, widx: 11, cap: 33)

            //jdk 中的容量一旦设置好，则不能扩容.netty可以

            System.out.println(byteBuf);
            System.out.println(byteBuf.arrayOffset());
            System.out.println(byteBuf.readerIndex());
            System.out.println(byteBuf.writerIndex());
            System.out.println(byteBuf.capacity());

            int len = byteBuf.readableBytes();
            System.out.println(len);

            for(int i=0;i < byteBuf.capacity();i++){
                System.out.println((char)byteBuf.readableBytes());

            }
            // hello world 加一个汉字  你hello world
            //打印指定长度的字节
            System.out.println(byteBuf.getCharSequence(0,4,Charset.forName("utf-8")));
            System.out.println(byteBuf.getCharSequence(4,6,Charset.forName("utf-8")));
        }
    }

    //AbstractByteBuf
}
