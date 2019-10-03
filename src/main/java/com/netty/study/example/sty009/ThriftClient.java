package com.netty.study.example.sty009;

import io.netty.handler.codec.haproxy.HAProxyProxiedProtocol;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public static void main(String[] args){

        TTransport transport = new TFramedTransport(new TSocket("localhost",8899),600);//超时事件

        TProtocol protocol = new TCompactProtocol(transport);

        PersonService.Client client = new PersonService.Client(protocol);

        try {
            transport.open();
            Person person = client.getPersonByUserName("zs");

            System.out.println(person.getUsername());
            System.out.println(person.getAge());
            System.out.println(person.isMarried());

            System.out.println("==========");
            Person pson2 = new Person();
            pson2.setAge(100);
            pson2.setUsername("lsw");
            pson2.setMarried(true);

            client.savePerson(pson2);
        } catch (TException e) {
            transport.close();
        }
    }
}
