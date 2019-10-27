package com.netty.study.example.sty013;

import javax.activation.MailcapCommandMap;
import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.util.Base64;

public class DeOrEnCoder {
    //编码或解码
    public static void main(String[] args) throws  Exception{

        String inputFile = "";

        String outFile = "";

        RandomAccessFile inputRan = new RandomAccessFile("", "r");

        RandomAccessFile outRan = new RandomAccessFile("", "rw");

        long inpuLen = new File(inputFile).length();

        FileChannel inputFileChannel = inputRan.getChannel();
        FileChannel outFileChannel = outRan.getChannel();

        MappedByteBuffer inputData = inputFileChannel.map(FileChannel.MapMode.READ_WRITE, 0, inpuLen);

        Charset charset = Charset.forName("utf-8");

        CharsetDecoder charsetDecoder = charset.newDecoder();
        CharsetEncoder charsetEncoder = charset.newEncoder();

        CharBuffer charBuffer = charsetDecoder.decode(inputData);

        ByteBuffer outByteBuffer = charsetEncoder.encode(charBuffer);

        outFileChannel.write(outByteBuffer);

        outRan.close();

        Charset.availableCharsets().forEach((k,v) ->{
            System.out.println(k + ":" + v);
        });





    }
}
