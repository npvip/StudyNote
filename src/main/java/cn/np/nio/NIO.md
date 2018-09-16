**NIO简介**  
从java 1.4版本开始引用的一套新的IO API，可以替代标准java IO API,NIO和原来的IO有相同的作用和目的，但使用方式完全不同，NIO支持面向缓冲区的、基于通道
的IO操作，以更高效的方式进行文件的读写操作。  
  
NIO的核心在于：通道（Channel）和缓冲区（Buffer）。   
Channel负责传输，Buffer负责存储。    

  
**NIO与IO的区别**  
1、IO是面向流（Stream Oriented）,NIO是面向缓冲区（Buffer Oriented）；  
2、IO是阻塞的（Blocking IO），NIO是非阻塞的（Non Blocking IO）；   
3、NIO有独特的选择器（Selectors）   
   

**缓存区Buffer**     
在Java NIO中负责数据的存取。底层是数组，用于存储不同数据类型的数据。  
包含：    
ByteBuffer,CharBuffer,ShortBuffer,IntBuffer,LongBuffer,FloatBuffer,DoubleBuffer  
 *  capacity:容量，表示缓冲区最大的存储数据的容量，声明后不可修改  
 *  limit:界限，表示缓冲区中可以操作数据大小，limit后面的数据不能读写  
 *  position:位置，表示缓冲区中正在操作数据的位置  
 *  mark:标记，表示记录当前position，通过reset()恢复到mark位置  
 *
 *  0 <= mark <= position <= limit <= capacity  
  
**直接缓冲区和非直接缓冲区**  
非直接缓冲区：通过allocate()方法分配缓冲区，将缓冲区建立在JVM内存中  
直接缓冲区：通过allocateDirect()方法分配直接缓冲区，将缓冲区建立在物理内存中，可以提高效率  

**通道**  
表示IO源与目标打开的连接，负责缓冲区中的数据的传输。  
java.nio.channels.Channel接口:  
  |--FileChannel  
  |--SocketChannel  
  |--ServerSocketChannel  
  |--DatagramChannel    

**通道间数据传输**  
transferFrom,transferTo  

**分散(Scatter)和聚集(Gather)**  
分散读取：从Channel中读取的数据分散到多个Buffer中  
聚集写入：将多个Buffer聚集到Channel中  