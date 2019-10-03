thrift  底层以socket rpc的方式进行传输
    
    
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
    字段尽量使用optional 由业务控制是否必须
    
thrift不支持日期类型，需要把日期定义为字符类型，然后再进行转换。因为跨语言的特性，需要取所有语言的交集

生成代码：
thrift --gen java src/main/java/com/netty/study/example/sty009/data.thrift
trhift --gen java idl描述文件路径，默认在classpath 下会生成gen-java文件，生成的代码在次路径下

brew


#### 原理窥探
    tcompactprotocol协议层
    tframedtransport传输层
    tprocessorfactory
    
    
    
客户端和服务端由多种选择，但是需要使用相同的协议，才能正确解析内容

整合Spring：配置对应的实现类就可以了，方便

tprotocol

ttransport

网络io

### thrift的传输格式
 tbinaryprotocol   二进制格式
 tcomactprotocol  压缩格式
 jsonprotocol  json格式
 tsimplejsonprotocol 提供json只写格式，生成的文件很容易通过脚本语言解析。  协议不能解析，只有生成对应的文件后或对象后才能解析额
 tdebugprotocol  使用易懂的可读的文本格式，以便于debug调试
 
 ### thrift传输方式
 tsocket 阻塞式socket
 tframetransport 以frame为单位进行传输，非阻塞式服务中使用
 tfiletransport 以文件形式进行传输
 tmemorytransport 将内存用于i/o,java实现时内部实际使用了简单的bytearrayoutputstream  
 tziptransport 使用zlib进行压缩，与其它传输方式联合使用。当前无java实现
 
 ### thrift支持的服务模型
 
tsimpleserver 简单的单线程服务模型，常用于测试
tthreadpoolserver 多线程服务模型，使用标准的阻塞时io.使用了线程池技术

tnonblockingserver 多线程服务模型，使用非阻塞式io(需要使用tframetransport数据传输方式)

thshaserver  thsha引入了线程池取处理，其模型把读写任务放到线程池取处理；half-sync/half-async的处理模式，half-aysnc是在处理ip
事件上（accept/read/write io),hasl-sync用户handler对rpc的同步处理。需要使用tframetransport数据传输方式

    
    send_savePerson
    
## thrift对异构平台的支持 
生成代码 添加 namespace py py.thrift.generated
这样生成的代码 会有java 和python的代码
python也需要安装thrift相关依赖，下载thrift的源码，进入py python相关的包中，执行 python setup.py install
  安装相应的pyhon依赖


    



 
 