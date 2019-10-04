package com.netty.study.example.sty010.grpc;

import com.netty.study.example.sty010.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

public class GrpcClient {


    public static void main(String[] args){
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 8899).usePlaintext().build();
//阻塞式
        StudentServiceGrpc.StudentServiceBlockingStub blockingStub =
                StudentServiceGrpc.newBlockingStub(managedChannel);

        StudentServiceGrpc.StudentServiceStub stub =  StudentServiceGrpc.newStub(managedChannel);
        MyResponse response = blockingStub.getRealNameByUsername(MyRequest.newBuilder().setUsername("zs").build());

        System.out.println(response.getRealname());

        System.out.println("------------------------------");

        Iterator<StudentResponse> itResponse = blockingStub.getStudentsByAge(StudentRequest.newBuilder().setAge(20).build());
        while(itResponse.hasNext()){
            System.out.println("client recv:" + itResponse.next().getAge() +";" + itResponse.next().getName() + ";" + itResponse.next().getCity());
        }

        System.out.println("=======================");


    }
}
