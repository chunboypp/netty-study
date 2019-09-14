package com.netty.study.example.sty003;

import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;
import sun.nio.ch.ChannelInputStream;

public class MyChatServerHandler extends SimpleChannelInboundHandler<String> {
    /**
     * 用来记录所有已经建立连接的channel
     */

    private static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
        Channel channel = ctx.channel();

        channelGroup.forEach(ch ->{
            if(ch == channel){
                ch.writeAndFlush("[自己]：" + msg+"\n");

            }else{
                ch.writeAndFlush(channel.remoteAddress()+",发送的消息：" + msg+"\n");
            }
        });


    }

    /**
     * 下线状态
     * @param ctx
     * @throws Exception
     */

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("server-client:" + channel.remoteAddress() + "下线了"+"\n");
//
    }

    /**
     * 在线状态
     * @param ctx
     * @throws Exception
     */

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("server-client:" + channel.remoteAddress() + "上线了" + "\n");
//        super.channelReadComplete(ctx);
    }

    /**
     * 建立连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        //super.handlerAdded(ctx);
        Channel channel = ctx.channel();
        /**
         * channelGroup会向其中所有的channel发送这个信息
         */
        channelGroup.writeAndFlush("server add client :" + channel.remoteAddress() + "\n");
        //不广播自己，所以，添加动作放在这里
        channelGroup.add(channel);

    }

    /**
     * 关闭连接
     * @param ctx
     * @throws Exception
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
//        super.handlerRemoved(ctx);
        Channel channel = ctx.channel();
        channelGroup.writeAndFlush("server-client leave :" + channel.remoteAddress() + "\n");
        //这里会自动调用 remove方法
//        channelGroup.remove()
    }
}
