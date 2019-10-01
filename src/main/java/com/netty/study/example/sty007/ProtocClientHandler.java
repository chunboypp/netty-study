package com.netty.study.example.sty007;

import com.netty.study.example.sty006.DataInfo;
import com.oracle.deploy.update.UpdateInfo;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class ProtocClientHandler extends SimpleChannelInboundHandler<DataInfo.Student> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, DataInfo.Student msg) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        DataInfo.Student student =  DataInfo.Student.newBuilder().setAddress("bj").setAge(999).setName("wb").build();

        ctx.channel().writeAndFlush(student);
    }
}
