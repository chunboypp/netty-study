package com.netty.study.example.sty007;

import com.netty.study.example.sty006.DataInfo;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtocServerInitializer extends ChannelInitializer<SocketChannel> {



    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline =  ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());

        //这里指定要转换的对象，因为这里是对 student对象的解析
        pipeline.addLast(new ProtobufDecoder(DataInfo.Student.getDefaultInstance()));

        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());

        pipeline.addLast(new ProtobufEncoder());

        pipeline.addLast(null);
    }
}
