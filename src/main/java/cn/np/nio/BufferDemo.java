package cn.np.nio;

import java.nio.ByteBuffer;

/**
 * @author np
 * @date 2018/9/11
 *
 * Buffer：缓冲区 负责数据的存储（读写）
 * capacity:容量
 * limit：限制
 * position：位置
 * mark:标记
 */
public class BufferDemo {

    public static void main(String[] args) {
        ByteBuffer byteBuffer=ByteBuffer.allocate(10);
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());

        byteBuffer.put("aaa".getBytes());

        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());

        System.out.println("flip");
        byteBuffer.flip();

        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());

        System.out.println("get");

        System.out.println((char)byteBuffer.get());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.mark());




    }
}
