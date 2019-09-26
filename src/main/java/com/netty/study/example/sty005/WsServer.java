package com.netty.study.example.sty005;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import javax.activation.MailcapCommandMap;
import java.net.InetSocketAddress;

/**
 * websocket 双写
 * 可同时写和同时读
 * 长连接
 *
 * tcp在传输的过程中会传输头信息，往往头信息比信息体还多，浪费资源
 * webosocket也是基于tcp，一旦建立链接，双方哦对等，无区分客户端和服务端。 只发送信息，不发送头信息
 *
 */
public class WsServer {

    public static void main(String[] args) throws Exception{
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap();

            bootstrap.group(boss,work).channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new WsServerInitializer());
           // bootstrap.bind(new InetSocketAddress(88909)) 同直接绑定端口

            ChannelFuture future = bootstrap.bind(8899).sync();
            future.channel().close();
        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();

        }


    }
}
