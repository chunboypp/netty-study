package com.netty.study.example.sty016;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;

import java.util.Iterator;

public class ByteBufSty003 {

    public static void main(String[] args){
        CompositeByteBuf compositeByteBuf = Unpooled.compositeBuffer();

        ByteBuf heepBuf = Unpooled.buffer(10);
        ByteBuf direacBuf = Unpooled.directBuffer(8);

        compositeByteBuf.addComponents(heepBuf, direacBuf);
        compositeByteBuf.removeComponent(0);

        Iterator<ByteBuf> it = compositeByteBuf.iterator();

        while(it.hasNext()){
            System.out.println(it.next());

        }





    }
}
