package com.netty.study.example.sty006;

public class StudentTest {

    public static void main(String[] args) throws Exception{
        DataInfo.Student student =   DataInfo.Student.newBuilder().setName("ly").setAge(789).setAddress("bj").build();

        byte[] stuByte = student.toByteArray();

        DataInfo.Student copyStu = DataInfo.Student.parseFrom(stuByte);

        System.out.println(copyStu.getAddress());
        System.out.println(copyStu.getAge());
        System.out.println(copyStu.getName());
    }
}
