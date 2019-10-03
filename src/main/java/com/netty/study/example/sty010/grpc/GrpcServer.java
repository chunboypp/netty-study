package com.netty.study.example.sty010.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class GrpcServer {

    private Server server;


    public void start() throws Exception{
        server = ServerBuilder.forPort(8899).addService(new StudentServiceImpl()).build().start();
        System.out.println("grpc server started");

        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.out.println("关闭jvm");
                GrpcServer.this.stop();
            }
        });
    }


    public  void  stop(){
        if(null != server){
            server.shutdown();
        }
    }
    //等待被终止，grpc需要通过代码进入等待状态，要不启动之后代码执行完就自动终止了
    public void awaitTerminate() throws Exception{
        if(null != server){
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception{
        GrpcServer server = new GrpcServer();

        server.start();
        server.awaitTerminate();

    }
}
