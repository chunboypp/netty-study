传统的网络io是阻塞式的

客户端是随机选择一个不占用的端口，跟服务器进行通信。

服务器监听对应的端口号进行连接，serversocket.bin(端口号)
这里的这个端口号是用来建立连接的，并不是用来传输数据的。一个socket就是一个连接
服务端在接收到相应数据时也是找到一个服务端不占用的端口进行数据传输。
监听端口和数据传输端口是两码事

正常阻塞时网络io 会在服务器端
while(true){
    socket = serversocket.accept()
    new thread(socket)//基于每个连接启动一个线程，系统会给这个线程分配不占用的端口进行通信
}
nio 网络操作

异步编程 event

消息量不是特别大，连接特别多。一个线程可以实现多个连接


### 字符集的编码 

utf-16
utf-32

unicode 表示范围最大的

ascii  码  
    7bit表示一个字符，可表示128种字符
iso8859-1
    8bite表示一个字符，即占用一个字符。共计可表示256个字符。向下兼容 ascii
    
gb2312 
    对常用汉字进行编码。2byte表示一个字节
gbk
    包含gb2312和一些生僻词
gb18030
    最完成的汉字表示。表示的汉字最多
台湾的编码 big5(繁体)


全球各个国家的编码，unicode
    采用两个字节表示一个字符。存储成本高。
utf8

utf16

utf32

utf unicode translation format

unicode是一种编码方式，utf是一种存储方式。utf是unicode的实现方式之一
utf16 le(little endian)  utf16 be(big endian)
在文件的开头加上标示的字符 oxfeff oxfffe

utf8变长的字节表示形式
    对于中文，会通过3个字节来表示，。最多可以使用6个字节来表示
    
    
    字节序bom (byte order mark)在一个文件的其实位置上加标示，
    windows下文件够默认会有不可见的字符，解析的时候要平台区分

