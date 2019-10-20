package com.netty.study.example.sty012;

import com.sun.org.apache.bcel.internal.generic.Select;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioTest12 {

    /**
     * 代码有待完善
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws  Exception{
        int[] ports = new int[5];

        ports[0] = 5001;
        ports[1] = 5002;
        ports[2] = 5003;
        ports[3] = 5004;
        ports[4] = 5005;


        Selector selector = Selector.open();
        for(int port : ports){



            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            //设置非阻塞的方式进行处理
            socketChannel.configureBlocking(false);

            ServerSocket serverSocket = socketChannel.socket();

            socketChannel.bind(new InetSocketAddress(port));
            //监听连接事件

            socketChannel.register(selector, SelectionKey.OP_ACCEPT);


        }

        while(true){
            int num = selector.select();
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            Iterator<SelectionKey> iter =  selectionKeySet.iterator();

            while(iter.hasNext()){

                SelectionKey selectionKey = iter.next();

                if(selectionKey.isAcceptable()){

                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();

                    SocketChannel socketChannel = serverSocketChannel.accept();

                    socketChannel.configureBlocking(false);

                    socketChannel.register(selector, SelectionKey.OP_READ);

                    iter.remove();
                }else if(selectionKey.isReadable()){

                   SocketChannel socketChannel =  (SocketChannel) selectionKey.channel();

                   int writeint = 0;
                    while(true){
                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);

                        byteBuffer.clear();

                        int read = socketChannel.read(byteBuffer);
                        if(read <= 0){
                            break;
                        }
                        socketChannel.write(byteBuffer);
                        writeint += read;

                    }
                    iter.remove();

                }




            }
        }




    }
}
