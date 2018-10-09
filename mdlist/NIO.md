# Java IO  
## IO模型  
* **阻塞IO**  
* **非阻塞IO**  
* **多路复用IO**  
* **信号驱动IO**  
* **异步IO**  

1. 阻塞IO  
线程发起一个IO请求，直到有结果返回，否则一直阻塞等待。如常见的网络IO、阻塞数据库操作等。  
**缺点**：当有大量IO请求时，线程的创建和销毁、线程间的切换、线程占用的资源会耗费大量的时间，会造成响应时间长甚至服务挂起。  

2. 非阻塞IO  
线程发起一个IO请求，不会一直阻塞等待直到有数据，而是不断检查是否有数据（类似轮询），若有数据则读取数据。  
**缺点**：由于需要不断的轮询，效率也不高。  

3. 多路复用IO  
线程发起一个IO请求，会将其注册到一个单独管理IO请求的线程，之后该IO的相关操作的通知状态都由这个管理IO请求的线程处理。  
Java NIO的模型就是多路复用IO的应用。  

4. 信号驱动IO  
线程发起一个IO请求，会注册一个信号函数，内核在确认数据可读了，便給响应的线程发通知，让其进行具体的IO操作。  
实际应用不多，主要使用UDP协议。  

5. 异步IO  
以上四种都是同步IO，因为它们在读写时都是阻塞的。异步IO的特点是在读写时也是非阻塞的。  
用户线程在发起一个IO请求的时候，除了给内核线程传递具体的IO请求外，还会给其传递数据缓冲区，回调函数通知等内容，然后用户线程就继续执行，等到内核线程发
起相应通知的时候，说明数据已经准备就绪，用户线程直接使用即可，无需再阻塞从内核拷贝数据到用户线程。  

***

# Java NIO

## **Java NIO简介**  
从java 1.4版本开始引用的一套新的IO API，可以替代标准java IO API，NIO和原来的IO有相同的作用和目的，但使用方式完全不同，NIO支持面向缓冲区的、基于通道
的IO操作，以更高效的方式进行文件的读写操作。  
Java NIO在高并发领域应用较多，如常见的`Netty`,`Mina`等框架都是基于Java NIO实现的。  

## **NIO与IO的区别**  
1. IO是面向流（Stream Oriented）,NIO是面向缓冲区（Buffer Oriented）；  
2. IO是阻塞的（Blocking IO），NIO是非阻塞的（Non Blocking IO）；   
3. NIO有独特的选择器（Selectors）
  
## Java NIO架构  
核心由`Selector`,`Channel`,`Buffer` 组成。关系图：  
![Java NIO架构](https://github.com/npvip/StudyNote/blob/master/img/nio.png)   

### Selector  
`Selector`（选择器）可以看成对应多路复用IO模型中的**监管角色** ，负责统一管理IO请求，监听响应的IO事件，并通知对应的线程进行处理，
是Java NIO的核心所在。  

### Channel  
`Channel`（通道）可以看成是数据通道，是双向的（可以接收数据，也可以写数据）。  
 主要由以下四种：    
 * FileChannel:读写文件时使用的通道  
 * DatagramChannel:传输UDP连接数据时的通道  
 * SocketChannel:传输TCP连接数据时的通道  
 * ServerSocketChannel:监听套接字连接时的通道  
   
### Buffer  
`Buffer`（缓冲区）是数据的缓冲区，负责数据的存取，底层数据结构是数组，用于存储不同数据类型的数据。
需要对应着某一个Channel，从中读取数据或写数据。包含直接缓冲区和非直接缓冲区：  

* 直接缓冲区  
 直接分配在系统内存中，可以提升效率，不受Java GC管理，创建和销毁的成本高。通过allocateDirect()方法分配直接缓冲区。  
   
* 非直接缓冲区  
 分配在Java JVM内存中，受Java GC管理，不需要额外的维护，创建成本相对低。通过allocate()方法分配缓冲区  
   
```
// 非直接缓冲区
ByteBuffer byteBuffer=ByteBuffer.allocate(1024);
// 直接缓冲区
ByteBuffer byteBuffer=ByteBuffer.allocateDirect(1024);
```      
         
常见缓冲区包括`ByteBuffer`,`CharBuffer`,`ShortBuffer`,`IntBuffer`,`LongBuffer`,`FloatBuffer`,`DoubleBuffer`。    
 *  capacity:容量，表示缓冲区最大的存储数据的容量，声明后不可修改  
 *  limit:界限，表示缓冲区中可以操作数据大小，limit后面的数据不能读写  
 *  position:位置，表示缓冲区中正在操作数据的位置  
 *  mark:标记，表示记录当前position，通过reset()恢复到mark位置  
 *
 *  0 <= mark <= position <= limit <= capacity  
  
# 参考  
* Java IO之NIO：https://juejin.im/post/5ab642b56fb9a028c97a025f  
* Java IO初探：https://juejin.im/post/59f6e53951882578d84ebdc7    