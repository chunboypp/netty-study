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
        responseObserver.onNext(StudentResponse.newBuilder().setAge(30).setCity("sh").setName("fds").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(40).setCity("gz").setName("dfd").build());
        responseObserver.onNext(StudentResponse.newBuilder().setAge(50).setCity("wh").setName("fdd").build());

        responseObserver.onCompleted();
    }

    @Override
    public StreamObserver<StudentRequest> getStudentsWrapperByAges(StreamObserver<StudentResponseList> responseObserver) {
        return super.getStudentsWrapperByAges(responseObserver);
    }

    @Override
    public StreamObserver<StreamRequest> biTalk(StreamObserver<StreamResponse> responseObserver) {
        return super.biTalk(responseObserver);
    }
}
