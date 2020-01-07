package com.netty.study.example.sty012;


import io.opencensus.internal.DefaultVisibilityForTesting;



import java.nio.IntBuffer;
import java.security.SecureRandom;

public class NioTest1 {

    public static void main(String[] args){
       IntBuffer intBuffer = IntBuffer.allocate(10);

        //random 加强版，提供更加分散的数字
        SecureRandom secureRandom = new SecureRandom();



        for(int i=0;i< intBuffer.capacity() ;i++){
            intBuffer.put(secureRandom.nextInt(20));
        }
        //状态转换。由写转换为读
        intBuffer.flip();

        while (intBuffer.hasRemaining()){
            System.out.println(intBuffer.get());
        }




    }
//    @Test
    public void test1(){
        IntBuffer intBuffer = IntBuffer.allocate(10);

        //random 加强版，提供更加分散的数字
        SecureRandom secureRandom = new SecureRandom();



        for(int i=0;i< 3 ;i++){
            intBuffer.put(secureRandom.nextInt(20));
        }
        System.out.println("before flip limit :" + intBuffer.limit());
        //状态转换。由写转换为读
        intBuffer.flip();
        System.out.println("after flip limit :" + intBuffer.limit());
        while (intBuffer.hasRemaining()){

            System.out.println(intBuffer.limit());
            System.out.println(intBuffer.position());
            System.out.println(intBuffer.capacity());
            System.out.println(intBuffer.get());


        }
    }
}
