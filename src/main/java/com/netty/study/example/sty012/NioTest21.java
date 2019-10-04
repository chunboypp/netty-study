package com.netty.study.example.sty012;

import javax.activation.MailcapCommandMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest21 {

    public static void main(String[] args){
        try {
            FileInputStream fileInputStream = new FileInputStream(new File("niotest2.txt"));

            FileChannel channel = fileInputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(512);

            channel.read(byteBuffer);
            //读写状态转换，由写转换为读
            byteBuffer.flip();
            while(byteBuffer.remaining() > 0){
                System.out.println((char)byteBuffer.get());
            }
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
