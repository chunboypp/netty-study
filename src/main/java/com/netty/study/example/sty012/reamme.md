原本的io只能写或者读，不能同时 读或者写

节点流：依赖于具体设备

过滤流：带有缓冲的流，依赖于节点流，包装节点流


通过装饰模式对流对象进行扩展。区别于继承，通过组合的方式实现对象类的扩展

java.io最为核心的概念是流stream，面向流编程。java中一个流要么是深入流要么是输出流

java.nio中的3个概念： selector channel buffer.在nio中是面向block 编程的或着缓冲区buffer编程的
    buffer本身是一块内存，底层是一个数组，读写都是通过buffer来实现的。
除来数组之外，buffer还提供来对于数组的结构化访问方式，并且可以追踪到系统的读写过程。

8中基本类型中只有七种的buffer .没有booleanbuffer类型

channel 可以写入数据或从中读取数据的对象，所有数据的读写都是通过buffer进行的，不存在直接从channel读写数据的情况
    与stream不同的是，channel是双向的，可读 可写 或同时读写。stream 只能是input或output.channel更能真实反应底层操作系统的情况，例如linux中
    文件的读写就是双向的。
    

### 中的
position 无法可读可写的第一个元素的索引，不会超过limit
limit  无法可读或可写的第一个元素的索引， 不会超过 capacity
capacity  就是一个buffer的容量

capacity不变，
limit  position是变化的
每次flip改变读写状态的时候，limit 重置为position的位置  position置0，

marking 标记
reseting  reset后会到被标记的位置

 if (mark > position) mark = -1;

0《=mark<=position<=limit<=capacity

clear 将 limit设置为 capacity ,将position设置为0
remind limit保持不变，position重置为0
buffer本身不是线程安全的

direact buffer


nio读取文件的3个步骤：
   1 从fileinputstream 获取filechannle
   2创建buffer
   3将数据从filechannel写入到buffer
   
 相对方法：考虑 positont 和limit
 绝对方法： 忽律 position 和limit