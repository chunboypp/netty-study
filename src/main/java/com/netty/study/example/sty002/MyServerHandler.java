package com.netty.study.example.sty002;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class MyServerHandler extends SimpleChannelInboundHandler<String> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        System.out.println("remote addr:"+ctx.channel().remoteAddress() + ";data:"+msg);
        ctx.writeAndFlush("server");
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
      //  super.exceptionCaught(ctx, cause);
       cause.printStackTrace();
        ctx.close();
    }
}
