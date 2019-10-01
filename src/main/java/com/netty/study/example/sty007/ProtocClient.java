package com.netty.study.example.sty007;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;


public class ProtocClient {

    public static void main(String[] args){

        EventLoopGroup event = new NioEventLoopGroup();

        try {
            Bootstrap bootstrap = new Bootstrap();

            bootstrap.group(event).channel(NioSocketChannel.class).handler(new LoggingHandler(LogLevel.INFO))
                    .handler(null);

            ChannelFuture future = bootstrap.bind("localhost", 8899).sync();
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            event.shutdownGracefully();
        }
    }
}
