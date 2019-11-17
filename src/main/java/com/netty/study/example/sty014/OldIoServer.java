package com.netty.study.example.sty014;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class OldIoServer {

    public static void main(String[] args) throws  Exception{
        ServerSocket serverSocket = new ServerSocket(8899);

        while(true){
            Socket socket = serverSocket.accept();


            DataInputStream inputStream = new DataInputStream(socket.getInputStream());



        }
    }
}
