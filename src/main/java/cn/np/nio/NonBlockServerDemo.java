package cn.np.nio;

import org.junit.Test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.time.LocalDateTime;
import java.util.Iterator;

/**
 * @author np
 * @date 2018/10/10
 */
public class NonBlockServerDemo {


    @Test
    public void server() throws IOException {
        // 获取通道
        ServerSocketChannel ssChannel = ServerSocketChannel.open();
        // 切换非阻塞模式
        ssChannel.configureBlocking(false);
        // 绑定连接
        ssChannel.bind(new InetSocketAddress(9898));
        // 选择器
        Selector selector = Selector.open();
        // 将通道注册到选择器上，指定监听接收事件
        ssChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (selector.select() > 0) {
            Iterator<SelectionKey> it = selector.selectedKeys().iterator();

            while (it.hasNext()) {
                SelectionKey selectionKey = it.next();
                if (selectionKey.isAcceptable()) {
                    SocketChannel sChannel = ssChannel.accept();
                    sChannel.configureBlocking(false);
                    sChannel.register(selector, SelectionKey.OP_READ);

                }else if (selectionKey.isReadable()) {
                    SocketChannel sChannel = (SocketChannel) selectionKey.channel();

                    ByteBuffer buf = ByteBuffer.allocate(1024);
                    int len =0 ;
                    while ((len = sChannel.read(buf)) > 0) {
                        buf.flip();
                        System.out.println(new String(buf.array(), 0, len));
                        buf.clear();
                    }
                }
                it.remove();
            }
        }
    }

    @Test
    public void client() throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9898));
        sChannel.configureBlocking(false);

        ByteBuffer buf = ByteBuffer.allocate(1024);
        buf.put(LocalDateTime.now().toString().getBytes());
        buf.flip();
        sChannel.write(buf);
        buf.clear();

        sChannel.close();
    }
}
