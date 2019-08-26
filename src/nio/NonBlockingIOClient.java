package nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

public class NonBlockingIOClient {
	public static void main(String[]args){
		SocketChannel sc;
		ByteBuffer w_buffer;
		ByteBuffer r_buffer=ByteBuffer.allocate(1024);
		try{
			InetSocketAddress address=new InetSocketAddress("localhost",2016);
			sc=SocketChannel.open();
			sc.connect(address);
			if(sc.finishConnect()){
				System.out.println("连接已建立...........");
			}
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			String line;
			while ((line=br.readLine())!=null){
				w_buffer=ByteBuffer.wrap(line.getBytes());
				sc.write(w_buffer);
				System.out.println("数据已发送........");
				w_buffer.clear();
			}
			r_buffer.clear();
			sc.read(r_buffer);
			r_buffer.flip();
			String msg= Charset.forName("UTF-8").decode(r_buffer).toString();
			System.out.println(msg);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
