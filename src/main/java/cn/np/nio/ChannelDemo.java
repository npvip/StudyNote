package cn.np.nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * @author np
 * @date 2018/9/16
 */
public class ChannelDemo {

    /**
     * 复制文件(通道)
     * @param inPath
     * @param outPath
     * @throws Exception
     */
    public static void copyFile(String inPath,String outPath) throws Exception {

        FileInputStream fis=null;
        FileOutputStream fos=null;
        FileChannel inChannel=null;
        FileChannel outChannel=null;
        try{
            fis=new FileInputStream(inPath);
            fos=new FileOutputStream(outPath);
            //打开通道
            inChannel=fis.getChannel();
            outChannel=fos.getChannel();

            ByteBuffer buffer=ByteBuffer.allocate(1024);

            while (inChannel.read(buffer) != -1){
                buffer.flip();
                outChannel.write(buffer);
                buffer.clear();
            }


        }catch (IOException e){
            System.out.println(e);
        }finally {
            if(null != inChannel){
                inChannel.close();
            }
            if(null != outChannel){
                outChannel.close();
            }

            if(null != fos){
                fos.close();
            }

            if(null != fis){
                fis.close();
            }
        }

    }

    public static void copyFileByChannel(String inPath,String outPath) throws IOException {
        FileChannel inChannel=FileChannel.open(Paths.get(inPath),StandardOpenOption.READ);
        FileChannel outChannel=FileChannel.open(Paths.get(outPath),StandardOpenOption.WRITE,StandardOpenOption.CREATE_NEW);

        inChannel.transferTo(0,inChannel.size(),outChannel);

        inChannel.close();
        outChannel.close();
    }

    public static void main(String[] args) throws Exception {
      /*String inPath="1.png";
        String outPath="2.png";
        copyFile(inPath,outPath);*/

      String inPath="1.png";
      String outPath="3.png";

      copyFileByChannel(inPath,outPath);

    }
}
