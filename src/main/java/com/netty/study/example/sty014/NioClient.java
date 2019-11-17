package com.netty.study.example.sty014;

import java.io.FileInputStream;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;

public class NioClient {

    public static void main(String[] args) throws Exception{
        SocketChannel sock = SocketChannel.open();

        sock.connect(new InetSocketAddress("localhost",8899));
        sock.configureBlocking(true);//如果设置阻塞会尽量多的读取字节

        String fileName = "";

        FileChannel fil = new FileInputStream(fileName).getChannel();

        long starTime = System.currentTimeMillis();

        long fileCount = fil.transferTo(0, fil.size(), sock);

        System.out.println("读取文件传输时间："+ (System.currentTimeMillis() - starTime) +"长度：" + fileCount);

        fil.close();


    }
}
