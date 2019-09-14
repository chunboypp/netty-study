package com.netty.study.example.sty003;

import com.netty.study.example.sty002.MyServerInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 实现一个聊天服务
 *
 * handlerAdded 表示服务器与客户端已经连接上了 实现连接
 */
public class MyChatServer {

    public static void main(String[] args) throws Exception{

        EventLoopGroup boss = null;
        EventLoopGroup work = null;

        try {
            boss = new NioEventLoopGroup();
            work = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss,work)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new MyChatServerInitializer());
            ChannelFuture future =  serverBootstrap.bind(8899).sync();
            future.channel().closeFuture().sync();

        } finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
        }

    }
}
