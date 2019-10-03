package com.netty.study.example.sty010.grpc;

import com.netty.study.example.sty010.MyRequest;
import com.netty.study.example.sty010.MyResponse;
import com.netty.study.example.sty010.StudentServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {


    public static void main(String[] args){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();

        StudentServiceGrpc.StudentServiceBlockingStub blockingStub =
                StudentServiceGrpc.newBlockingStub(managedChannel);

        MyResponse response = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zs").build());

        System.out.println(response.getRealname());


    }
}
