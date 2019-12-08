nio读取文件的步骤：
1从fileinputstreamj对象中获取channel对象
2创建buffer
3将数据从channel中读取到buffer对象中

0<=mark<positon<limit<capacity  
flip  进行读写状态转换；
    将limit设置为当前的position
    将postion设置为0  
    
clear方法。
    将limit设置为capactiy
    将postion设置为0
    这里并没有清除数据，只是改变来数据指针。
    
compact 方法  
    1 将所有未读的数据复制到buffer的起始位置
    2将position设置为最后一个未读元素的后面
    3将limit设置为capacity
    4现在的bugger准备好来，但是不会覆盖未读的数据 
    
malloc c语言，

mapperbufferfile  

java nio中，关于direacbuffer heapbuffer的疑问

rednaxelafx


===============
heap 
direat
composite buffer 复合bytebuf

heap bybuffer

 存储在jvm堆内存中，底层使用byte  array使用存储，
 优点：在jvm中可以快速创建和释放，并提供直接访问内存内部字节数的方法
 缺点：读写数据，需要将数据复制到直接缓冲区再进行网络传输。
 
 direct buffer
 
 在jvm以外空间，占用操作系统本地内存进行数据分配。
 优点：socket数据传输性能好，没有堆到直接内存的拷贝
 缺点：不同于 堆的管理，堆外内存的释放和管理更加复杂，速度要慢一些 
 netty通过提供内存池来解决这个问题。直接缓冲区并支持通过字节数组的方式来访问数据。
 
 对于日常使用，对于本地消息的编解码，推荐使用heapbuffer
 对于网络通信，推荐使用directbuffer
 composit byffer
 
 =================================================
 jdk 和 netty  bytebuffer比对
 1 netty提供读写索引，一个初始化未有任何数据的bytebuf 的读写索引都为0
 2读索引到达写索引的位置，再接着读，就会抛异常，idexoutofboundexception
 2任何读写操作分别维护对应的读索引和写索引 。jdk的容量定后，不能扩容，netty可以扩容，最大为Integer.MAX_VALUE
 
 
 jdk和netty 都是继承 抽象类 Buffer
 
 jdk bytebuffer 
 底层 是final byte[] bb   final修饰，不可变，一旦容量指定好，就不能修改。
    如果存储的数据大于bytebuffer则会失败(异常 索引越界)，解决方案是从新建立一个大的bytebuffer，把原来的bytebuffer拷贝过来。需要开发者自己使用
 bytebuffer只使用一个position来标示位置信息，进行读写切换时，需要尽心flip方法调用。
   
 netty bytebuf 
 1 底层储存数组是动态的。可以动态扩容。最大容量为Integer.MAX_VALUE,动态性体现在write，当写入容量不足的时候，可动态扩容 
 2 维护写索引和读索引 。扩容是2的指数，即是2的新容量是2的倍数。netty自动实现
 
 
 ========引用技术==============netty对于bytebuf的管理采用引用计数。  
 referencecounted  引用计数 
 retail 加1 底层通过 cas实现引用加1 
 release也是，但是多了一个释放的步骤，如果引用计数为0的时候  
 ======
 atomicintegerfieldupdater 总结：
 1 只能更新int字段，不能包装其它包装类型
 2更新字段必须被volatile修饰，确保变量的可见性。
 3更新变量不能是static,必须是实例变量 因为Usafe.objectFieldOffset()不支持静态变量-cas操作本质上是通过对象实例
 的偏移量来进行赋值  
 4 更新器只能更改它可见范围内的变量，因为更新器是通过反射进行更新的，如果不可见则不能更新 
  
  如果更新的是包装类型，可以通过atomicReferenceFieldUpdate 进行更新 
  
  为什么没有使用automicInteger ，这个实质上是对integer的包装，如果引用都用atomic,包装对象比较大，有性能损耗。
  atomicintegerfieldupdater是全局的，只有一个，只是用此就可以更新所有的引用变量  
  
  
  
  netty.io/wiki/reference-counted-objects.html
  
  java-Dio.netty.detection.level= disable ;simple;advance;paranoid  
  
  ===========
  netty处理器：
  1 入站处理器 channelinboundhandler
  
   出站处理器：channeloutboundhandler
   2数据编解码本质上就是处理器
   3 codec
   编码-网络传输的是字节流，数据转换为字节流的过程为编码
    解码 字节流转换为原本的形式为解码，
   
   编码实际上是一种出站处理器
   解码实际上是一种入站处理器  
   
   在netty中大部分编码器通常以***Encoder命名的，大部分解码器是以***Decoder命名的  
   
   
   