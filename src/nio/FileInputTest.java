package nio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class FileInputTest {
	public static void main(String[]args)throws IOException {
		FileInputStream fis=new FileInputStream("J:\\emptyFile.txt");
		FileOutputStream fos=new FileOutputStream("J:\\copyTest.txt");
		FileChannel fisChannel=fis.getChannel();
		FileChannel fosChannel=fos.getChannel();
        //创建缓冲区，将数据读到buffer
//		ByteBuffer buffer=ByteBuffer.allocate(1024);
//		while (fisChannel.read(buffer)!=-1){
//			buffer.flip();
//			while(buffer.hasRemaining()){
//				System.out.print((char)buffer.get());
//			}
//			fosChannel.write(buffer);
//			buffer.clear();
//		}
		fos.write(fis.read());
		System.out.println("yes");
		fis.close();
		fos.close();
		fisChannel.close();
		fosChannel.close();
	}
}
