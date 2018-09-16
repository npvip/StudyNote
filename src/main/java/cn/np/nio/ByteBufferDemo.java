package cn.np.nio;

import java.nio.ByteBuffer;

/**
 * @author np
 * @date 2018/9/16
 *  1、存取数据的方法
 *  put():存入数据到缓冲区
 *  get();从缓冲区取出数据
 *
 *  2、核心属性
 *  capacity:容量，表示缓冲区最大的存储数据的容量，声明后不可修改
 *  limit:界限，表示缓冲区中可以操作数据大小，limit后面的数据不能读写
 *  position:位置，表示缓冲区中正在操作数据的位置
 *  mark:标记，表示记录当前position，通过reset()恢复到mark位置
 *
 *  0 <= mark <= position <= limit <= capacity
 *
 */
public class ByteBufferDemo {

    public static void practice(){
        ByteBuffer byteBuffer=ByteBuffer.allocate(1024);

        System.out.println(byteBuffer.position()); //0
        System.out.println(byteBuffer.limit()); //1024
        System.out.println(byteBuffer.capacity()); //1024

        System.out.println("---put()-----");
        String s="good";
        byteBuffer.put(s.getBytes());
        System.out.println(byteBuffer.position()); //4
        System.out.println(byteBuffer.limit()); //1024
        System.out.println(byteBuffer.capacity()); //1024

        System.out.println("---flip()----");
        byteBuffer.flip();
        System.out.println(byteBuffer.position()); //0
        System.out.println(byteBuffer.limit()); //4
        System.out.println(byteBuffer.capacity()); //1024

        System.out.println("---get()----");
        byte[] r=new byte[byteBuffer.limit()];
        byteBuffer.get(r);
        System.out.println(new String(r,0,r.length));
        System.out.println(byteBuffer.position()); //4
        System.out.println(byteBuffer.limit()); //4
        System.out.println(byteBuffer.capacity()); //1024

        System.out.println("---rewind()----");
        byteBuffer.rewind();
        byte[] rr=new byte[byteBuffer.limit()];
        byteBuffer.get(rr);
        System.out.println(new String(rr,0,rr.length));

        byteBuffer.clear();

    }

    public static void markTest(){
        ByteBuffer buffer=ByteBuffer.allocate(16);

        buffer.put(new String("abcdef").getBytes());

        buffer.flip();

        byte[] b1=new byte[2];
        buffer.get(b1,0,2);
        System.out.println(new String(b1,0,b1.length));//ab

        buffer.mark();

        System.out.println("position:"+buffer.position());//2

        byte[] b2=new byte[3];
        buffer.get(b2,0,3);
        System.out.println(new String(b2,0,b2.length));//cde

        System.out.println("position:"+buffer.position());//5

        buffer.reset();
        System.out.println("position:"+buffer.position());//2


    }

    public static void main(String[] args) {
        //practice();
        markTest();
    }
}
