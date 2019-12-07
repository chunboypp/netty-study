package com.netty.study.example.sty013;

import sun.misc.Cleaner;

import javax.sound.sampled.ReverbType;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class JavaNioServer {

    private static final HashMap<String, SocketChannel> clientMap = new HashMap<>();

    /**
     * serversocketchannle 是用于监听使用
     * serversocket 是用于数据传输
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception{
        //服务器的连接 监听只使用一个线程

        Selector selector = Selector.open();

        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);

        ServerSocket serverSocket = serverSocketChannel.socket();

        serverSocketChannel.bind(new InetSocketAddress(8899));


        serverSocketChannel.register(selector, SelectionKey.OP_READ);

        while(true){
            int num = selector.select();

            Set<SelectionKey> selectionKeySet = selector.selectedKeys();

            //jdk8的写法
            selectionKeySet.forEach(selectionKey -> {

                 SocketChannel client = null;
                try {
                    if(selectionKey.isAcceptable()){
                        ServerSocketChannel serverSocketChannel1 = (ServerSocketChannel) selectionKey.channel();

                        client = serverSocketChannel.accept();

                        client.configureBlocking(false);
                        client.register(selector, SelectionKey.OP_READ);

                        String key = UUID.randomUUID().toString();

                        clientMap.put(key, client);


                    }else if(selectionKey.isReadable()){

                        ByteBuffer byteBuffer = ByteBuffer.allocate(512);
                        client = (SocketChannel) selectionKey.channel();

                        int count = client.read(byteBuffer);

                        if(count > 0){

                            byteBuffer.flip();

                            Charset cha = Charset.forName("utf-8");

                            String recvString = String.valueOf(cha.decode(byteBuffer).array());

                            System.out.println(client +":" + recvString);

                            String sendKey = "";

                            for(Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                                if(entry.getValue() == client){
                                    sendKey = entry.getKey();
                                }
                            }
                            for(Map.Entry<String,SocketChannel> entry : clientMap.entrySet()){
                                if(entry.getValue() != client){
                                    ByteBuffer writeBuffer = ByteBuffer.allocate(512);

                                    writeBuffer.put((sendKey + "=" + recvString).getBytes());

//                                    writeBuffer.flip()

                                    entry.getValue().write(writeBuffer);


                                }
                            }
                        }


                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            selectionKeySet.clear();


        }

    }
}
