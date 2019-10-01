thrift
    
    
    brew包管理软件
    brew install thrift
    
    
services 
 
 
 thrift本身提供客户端和服务端的api，也可以使用netty
 
 客户端和服务端可以使用不同的语言卡法，通过关联语言
 idl  interfae desciption language  
 
 不支持符号类型   
 
 数据类型：
 byte  有符号字节
 i16  16位整数
 i32
 i64
 double  64位浮点数
 string 字符串
 
 容器类型：
集合中的元素可以是除拉service之外的任何类型，包括exception

list 一系列由t类型的数据组成的有序列表，可重复
set
map

service用来描述服务接口  

thrift数据传输使用socket,数据再以特定的格式进行发送，接收方进行解析

thrift文件，由IDL生成双方语言的接口 model 生成的model以及接口中会有解码编码的代码

struct 类似c语言中的结构体
enum 类似于java中的枚举
异常 exeption,规则和struct一样
service 相当于java中的interface,通过命令生成客户端和服务端的框架代码
thrift类型定义，类似于c++中的typedef 
    typedef i32 int;
    typedef i64 long;
    
支持常量定义，const
    const i32 MAX = 10
    const string MY_STR=""
    
命名空间，定义包路径
    定义格式 namespace 语言名 路径  例如：namespace java com.netty.study
    
文件包含，类似于java中的import 使用关键字include  
注视  #  //  /**/都可以  
可选和必须按  optional required  

生成代码：

brew
    



 
 