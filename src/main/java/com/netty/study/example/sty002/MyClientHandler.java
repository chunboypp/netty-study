package com.netty.study.example.sty002;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyClientHandler extends SimpleChannelInboundHandler<String> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("client receive ::server addr:"+ ctx.channel().remoteAddress() +",data"+msg);
        ctx.writeAndFlush("client send data");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    /**
     * 当连接成功后，主动让客户端发起链接，才会触发对应的。 channelread
     * lsof -i:8080
     * lsof -i:8899
     * 查看对应端口的情况
     * @param ctx
     * @throws Exception
     */

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush("来自客户端的请求");
    }
}
