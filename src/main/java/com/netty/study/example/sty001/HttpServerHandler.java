package com.netty.study.example.sty001;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpHeaderNames;
import io.netty.handler.codec.http.HttpObject;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.util.CharsetUtil;

public class HttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if(msg instanceof HttpRequest){
            ByteBuf content  =Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse rsp = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
                    HttpResponseStatus.OK,
                    content);
            rsp.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            rsp.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());
            //flush 之后才会返回。只是write并不会返回
            ctx.writeAndFlush(rsp);
        }
    }
}
