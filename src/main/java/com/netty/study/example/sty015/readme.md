### nio 

nio jdk中有一个空轮询的bug，netty进行了规避

excute   

命令模式 
工厂模式

future


不要调用await方法 ，因为chnnalehandler 由 i o调用，会造成死锁
init方法
完成属性的设定
将生成的channel对象的pipeline添加到

bind 和sync

reactor

netty为什么要有两个eventloopgroup  


reactor模式

proactor模式

内核空间还是用户空间的划分
netty整体架构是reactor模式的完整体现。反应器模式

文档1:

scalable io in java

http://gee.cs.oswego.edu


文档2：
reactor 
an object behavioral pattern for demultiplexing and dipath

reactor-siemens


reactor 
    reactor模式的角色构成（reactor模式一共有5种角色构成）
1。handle-句柄或描述符：本质上表示一种资源，是由操作系统提供的；该资源用于表示一个个的事件，比如说文件描述符，或是针对网络编程种的socket  
事件可以来自于外部，也可以来自于内部；外部事件比如客户端的连接请求，客户端发送过来数据等；内部事件比如说操作产生的定时器事件等。  
它本质上就是一个文件描述符。handle是事件的发源地。  
2 synchronous event demultiplecer(同步事件分离器)：它本身是操作系统的一个调用，用于等待事件的发生（事件可能是单个，也可能是多个），调用方  
在调用它的时候会被阻塞，一直阻塞到同步事件分离器上有事件产生为止。对于linux来说，同步事件分离器指的就是长度哦的i/o多路复用机制，比如说  
select poll epoll 等。在java nio中，对应的组件是selector,对应的阻塞方法是select方法。  
3 event handle-事件处理器：本身又多个回调方法构成，这些回调方法构成了与应用相关的某个事件反馈机制。netty相比于java nio,在事件  
处理器这个角色上进行了升级，它为我们开发者提供了大量的回调方法，供我们在特定事件产生时实现相应的回调方法进行业务逻辑的处理。  
4 concret envet handler-具体事件处理器：是事件处理器的实现。它本身实现了事件处理器所提供的哥哥回调方法。从而实现了特定业务的逻辑。  
它本质上就是我们所编写的一个个的处理器的实现 。  
5 initiation dispathcher(初始分发器)：事件是就是reactor角色。它本身定义了一些规范，这些规范用于控制事件的调度方式，同时又提供  
了应用进行事件处理器的注册 删除等设置。它本身是整个事件处理器的核心所在，initiation dispatcher 回通过同步事件分离器来恩哥爱事件的发生。  
一旦事件发生，initiation dispathcher 首先回分离出每一个事件，然后调用事件处理器，最后调用相关的回调方法来处理这些事件。  

reactor模式的流程：
1当应用向 inition dispathchar注册具体事件处理器时，应用会标识出事件处理器希望 inition dispathchar在某个事件发生时向其通知的该事件，该事件与handle关联  
2 inition dispathchar会要求每个事件处理器向其传递内部的handle.该handle向操作系统标识了事件处理器。
3 当所有的事件处理器注册完毕后，应用会调用handle events方法类启动inition dispathchar的事件循环。这时，inition dispathchar会将每个注册的事件管理器  
的handle合并起来，并使用同步事件分离器等待这些事件的发生。比如说，tcp协议层会使用select同步事件分离器操作类等待客户端发送的数据到达连接的  
socket handle上  
4 当与某个事件源对应的handle变为ready状态时（比如说 tcp socket 变为等待读状态时），同步事件分离器就会通知inition dispathchar。  
5inition dispathchar会触发事件处理器的回调方法，从而响应这个处于ready状态的handle.当事件发生时，inition dispathchar 会将被事件激活的handle  
作为 kwy 来寻找并分发给恰当的事件处理器的回调方法。  
6inition dispathchar 会回调事件处理的handle_events回调方法来执行特定与应用的功能（开发者自己所编写的功能），从而相应这个事件。所发生事件类型可  
以作为该方法参数并被该方法内部使用来执行额外的特定于服务的分离与分发

netty中通过 accpttor 将 boss work和 sub work关联起来  




