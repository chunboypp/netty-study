package com.netty.study.example.sty001;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;


public class ServerTest {

    public static void main(String[] args) throws Exception{
        /**
         * 事件循环组，其实一个组就可以。
         * 这里定义两个组，一个用来接收消息，一个用来处理消息
         */
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();

        //服务快速启动
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            /**
             * NioServerSocketChannel 通过反射的方式进行创建
             * childHandler中的对象每次都需要new一个不要复用一个，或定义成单例
             */
            bootstrap.group(boos, work)
                    .channel(NioServerSocketChannel.class)
//                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ServerChildHandler());
            ChannelFuture future = bootstrap.bind(8090 ).sync();
            future.channel().closeFuture().sync();
        } finally {
            boos.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
