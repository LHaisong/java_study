package nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class TestBlockingClient {
	public static void main(String[]args)throws IOException
	{
		SocketChannel socketChannel=SocketChannel.open(new InetSocketAddress("127.0.0.1",6666));
		FileChannel fileChannel=FileChannel.open(Paths.get("E:\\Google download\\pic1.jpg"), StandardOpenOption.READ);
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		while (fileChannel.read(buffer)!=-1){
			buffer.flip();
			socketChannel.write(buffer);
			buffer.clear();
		}
		socketChannel.shutdownOutput();
		int len;
		while ((len=socketChannel.read(buffer))!=-1){
			buffer.flip();
			System.out.println(new String(buffer.array(),0,len));
			buffer.clear();
		}
		socketChannel.close();
		fileChannel.close();
	}
}
