package com.netty.study.example.sty010.grpc;

import com.netty.study.example.sty010.*;
import io.grpc.stub.StreamObserver;

public class StudentServiceImpl extends StudentServiceGrpc.StudentServiceImplBase {

    @Override
    public void getRealNameByUsername(MyRequest request, StreamObserver<MyResponse> response) {

        System.out.println("接收客户端的信息：" + request.getUsername());
        //请求发出
        response.onNext(MyResponse.newBuilder().setRealname("ldk").build());
        //请求结束
        response.onCompleted();

    }

    @Override
    public void getStudentsByAge(StudentRequest request, StreamObserver<StudentResponse> responseObserver) {

        System.out.println("receive client data: " + request.getAge());


        responseObserver.onNext(StudentResponse.newBuilder().setAge(20).setCity("bhj").setName("zs").build());
    }
}
