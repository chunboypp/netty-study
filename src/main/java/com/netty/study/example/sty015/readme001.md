  视频74需要着重看下。里面的总结很经典。
  
  future
  promise
  
  channelfutue 
  
  future 中的状态是由prosemise进行写入的的，进行状态变更的。promise只能写一次，之后就不能再写了。
  promise写入状态后，然后触发对应的回调  
  
  
  
  inboundhandler
  outboundhandler  
  
  
   适配器模式。不用实现所有的方法，只重写自己感兴趣的方法。
  channelinboundhandlerAdapter
  
  //让我们只关心消息的处理
  SimpleChannelInboundHandler 继承ChannleInboundHandlerAdapter
  
  
  //模版设计模式。
  底层会释放对应的资源，子层是一个bytebuffer池。索引不应该存储资源的引用，因为
  对象会被释放。
  
  
  ctx.channel.writeAndFlush 消息会从最后一个handler 一直到第一个handler
  如果直接调用ctx.writeAndFlush 则只会把消息传递给下一个handler,一直到第一个。当前
  ctx 之前的handler则不会进行调用
  
  netty两种消息发送方式：
  channle.writeandflush()直接写入到channel-消息会从ChannelPipeline的末尾开始流动
  ctx.writeandflush()只写写入到ChannleHandlerContext-消息会从ChannelPipeline下一个hanler开始执行,也意味着会代码的调用路径更短，
    性能更高，前提是不需要流经所有的handler
  
  一个channle会关联一个channelpipeline,
  一个channelhandler 对应一个channlehandlercontext -关联关系不会改变，可进行绑定
  channlepipeline 
  
  
  oio old input output  跟nio的代码编写流程是一样的，这种强大的抽象。
  传统io 是同步的
  nio是异步的
  两者差异很大。
  利用sockettimeout 参数来统一两者的。
  指定一个参数，同步oio肯定会超时，如果超时的化，则在异常里将当前次处理记录起来，等到eventloop下一次执行，
  再次重试
  
  oio 一个channel只会对应一个eventloop 
  nio 一个eventloop对应多个channel 
  
  服务器端 既可以是客户端，也可以是服务端
  a客户端 调用b服务端
  b服务端 调用 c服务端
  这里 b即是 客户端也是 服务端。这时候 eventloog 可以被客户端和服务端复用。
  a 到b  b到c 产生的两个channle可以共用一个eventloop 性能提升
  
  为代码：
  public void channleActive(ChannelHanlerContext ctx){
    BootStrap bootStrap = '''
    bootStrap.channle(NioSocketChannel.class).handler('
            
            bootstrap.group(ctx.channle().eventloop());
            bootstrap.connnect();//连接服务器a。这里复用eventloop
    
    )
  }
  
  