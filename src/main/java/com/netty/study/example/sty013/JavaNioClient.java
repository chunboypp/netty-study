package com.netty.study.example.sty013;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ServerChannel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.time.LocalDate;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JavaNioClient {

    public static void main(String[] args) throws  Exception{
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);



        Selector selector = Selector.open();

        socketChannel.register(selector, SelectionKey.OP_ACCEPT);
        socketChannel.connect(new InetSocketAddress("127.0.0.1", 8899));

                while(true){

                    selector.select();

                    Set<SelectionKey> selectionKeySet = selector.selectedKeys();

                    selectionKeySet.forEach(selectionKey -> {
                        try {
                            if(selectionKey.isConnectable()){

                                SocketChannel client = (SocketChannel) selectionKey.channel();

                                if(client.isConnectionPending()){//在连接中

                                    client.finishConnect();//完成连接

                                    //向服务器端发送消息

                                    ByteBuffer writebuffer = ByteBuffer.allocate(512);

                                    writebuffer.put((LocalDate.now() + "联机成功").getBytes());
                                    writebuffer.flip();
                                    client.write(writebuffer);

                                    //以上动作表示已经建立连接，双向。然后 可以进行通信 输入了，一般是由用户进行输入
                                    //通过一个新的线程来进行用户的输入，不阻塞原来的连接线程

                                    ExecutorService executorService = Executors.newSingleThreadExecutor(Executors.defaultThreadFactory());
                                    executorService.submit(() -> {

                                        while (true){
                                            writebuffer.clear();
                                            InputStreamReader input = new InputStreamReader(System.in);

                                            BufferedReader br = new BufferedReader(input);

                                            String sendMsg = br.readLine();

                                            writebuffer.put(sendMsg.getBytes());

                                            writebuffer.flip();
                                            client.write(writebuffer);
                                        }
                                    });

                                    //注册读取事件
                                    client.register(selector, SelectionKey.OP_READ);

                                }else if(selectionKey.isReadable()){
                                    SocketChannel client1 = (SocketChannel) selectionKey.channel();

                                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);

                                    int count = client1.read(readBuffer);

                                    if(count > 0){
                                        String recvMsg = new String(readBuffer.array(),0,count);
                                        System.out.println(recvMsg);

                                    }


                                }



                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });

                }
    }
}
