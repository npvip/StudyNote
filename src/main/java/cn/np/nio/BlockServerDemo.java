package cn.np.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author np
 * @date 2018/10/10
 *
 * NIO阻塞模拟
 *
 */
public class BlockServerDemo {

    // 客户端
    @Test
    public void client() {
        SocketChannel socketChannel = null;
        FileChannel fileChannel = null;
        try {
            // 获取通道
            socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 读取本地文件的通道
            fileChannel = FileChannel.open(Paths.get("1.png"),StandardOpenOption.READ);
            // 读取本地文件发送到服务端
            while (fileChannel.read(buffer) != -1) {
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            // 关闭通道
            try {
                if (fileChannel != null) {
                    fileChannel.close();
                }

                if (socketChannel != null) {
                    socketChannel.close();
                }

            }catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    // 服务端
    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 绑定连接
        ssChannel.bind(new InetSocketAddress(8888));

        // 获取客户端通道
        SocketChannel sChannel = ssChannel.accept();

        // 分配缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        FileChannel fileChannel = FileChannel.open(Paths.get("2.png"),StandardOpenOption.WRITE,StandardOpenOption.CREATE);


        // 接收客户端数据，保存到本地
        while (sChannel.read(buffer) != -1) {
            buffer.flip();
            fileChannel.write(buffer);
            buffer.clear();
        }


        fileChannel.close();
        sChannel.close();
        ssChannel.close();

    }
}
