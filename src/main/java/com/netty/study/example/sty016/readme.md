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
 
 =================================================
 jdk 和 netty  bytebuffer比对
 1 netty提供读写索引，一个初始化未有任何数据的bytebuf 的读写索引都为0
 2读索引到达写索引的位置，再接着读，就会抛异常，idexoutofboundexception
 2任何读写操作分别维护对应的读索引和写索引 。jdk的容量定后，不能扩容，netty可以扩容，最大为Integer.MAX_VALUE
 