package com.netty.study.example.sty012;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NioTest3 {

    public static void main(String[] args) throws Exception{

        try {
            FileOutputStream outputStream = new FileOutputStream(new File("niotest3.txt"));

            FileChannel channel = outputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(512);

            byteBuffer.put("nio test fads".getBytes());

//            channel.read(byteBuffer);

            byteBuffer.flip();

            channel.write(byteBuffer);

            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
