package com.netty.study.example.sty005;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;

import java.nio.channels.SocketChannel;
import java.time.LocalDate;

public class WsServerHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        System.out.println("收到信息：" + msg.text());

        ctx.channel().writeAndFlush(new TextWebSocketFrame("服务器时间：" + LocalDate.now()));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        System.out.println("handlerAdded:" + ctx.channel().id().asLongText());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
            System.out.println("handlerAdded:"+ctx.channel().id().asLongText());

    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("服务端异常");
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }
}
