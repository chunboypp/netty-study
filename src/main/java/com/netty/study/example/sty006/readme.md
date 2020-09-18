rmi 远程方法调用，两端只能都是java
client:stub
server:skeleton


rpc:remote prodedure call 远程过程调用，很多rpc框架是跨语言的
1。定义一个接口说明文件：
    描述对象（结构体） 对象成员 接口方法等一系列信息
 2通过rpc框架提供的编译器，将接口说明文件编译成具体语言文件
 3在客户端于服务器端分别引入rpc编译器编译的文件，即可像本地方法一样调用远程方法
 
 webservice 没有rpc效率高
 
 编解码速度
 压缩比
 
 
 webservice 一般是基于http
 rpc一般是基于websocket
 
 内网系统不需要https，推荐rpc方式
 
 protobuf依赖的jar包
 compile group: 'com.google.protobuf', name: 'protobuf-java', version: '3.3.1'
     compile group: 'com.google.protobuf', name: 'protobuf-java-util', version: '3.3.1'
     
     protobuf-java 核心包
     protobuf-java-util支持proto3 和json的处理
     
     
在google protobuf 官网搜索属性
    optimize_for  加快编译速度，默认是 Speed
    
protoc生成的java文件，绝对不要在此文件上进行修改，因为所有的修改会被下一次的生成所覆盖。


protoc --java_out=src/main/java src/main/java/com/netty/study/example/sty008/MyMessage.proto
 
 