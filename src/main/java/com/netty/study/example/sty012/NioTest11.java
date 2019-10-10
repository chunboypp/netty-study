package com.netty.study.example.sty012;

import java.io.RandomAccessFile;
import java.net.InetSocketAddress;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;

public class NioTest11 {
    /**
     * buffer的 scatting （分散） 与 gatting(合并)
     *
     * scatting将来自于一个channel数据，按照顺序读入到多个buffer中
     *
     * gattiong 将多个buffer数据按顺序写入到channel中
     *
     * 使用客户端程序进行测试
     *
     * @param args
     */
    public static void main(String[] args) throws  Exception{

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        InetSocketAddress inetSocketAddress = new InetSocketAddress(8899);

        serverSocketChannel.socket().bind(inetSocketAddress);

        //自己巧下  mac 下nc 工具 nc  localhost 8899
        //windows下 telnet




    }
}
