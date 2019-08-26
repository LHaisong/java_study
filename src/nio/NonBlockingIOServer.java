package nio;

import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;

public class NonBlockingIOServer {
	private static ServerSocketChannel serverSocketChannel;
	private static Selector selector;
	private static ByteBuffer r_buffer= ByteBuffer.allocate(1024);
	private static ByteBuffer w_buffer;
	public static void main(String[]args){
		int port=2016;
		System.out.println("等待端口"+port+"的连接.......");
		try{
			serverSocketChannel= ServerSocketChannel.open();
			InetSocketAddress address=new InetSocketAddress(port);
			serverSocketChannel.bind(address);
			serverSocketChannel.configureBlocking(false);
			selector=Selector.open();
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			while (true){
				 selector.select();
				Iterator<SelectionKey>sk=selector.selectedKeys().iterator();
				while (sk.hasNext()){
					SelectionKey key=sk.next();
					switch (key.interestOps()){
						case SelectionKey.OP_ACCEPT :
							dealwithAccept(key);
							break;
						case SelectionKey.OP_CONNECT :
							break;
						case SelectionKey.OP_READ :
							dealwithRead(key);
							break;
						case SelectionKey.OP_WRITE :
							break;
					}
					//移除，避免重复处理
					sk.remove();
				}
			}
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	/**
	 * 处理读事件
	 * @param key
	 */
	private static void dealwithRead(SelectionKey key) {
		try {
			SocketChannel sc = (SocketChannel) key.channel();
			System.out.println("读入数据.........");
			r_buffer.clear();
			sc.read(r_buffer);
			r_buffer.flip();
			String msg= Charset.forName("UTF-8").decode(r_buffer).toString();
			if(msg.equalsIgnoreCase("bye")){
				sc.write(ByteBuffer.wrap("已断开.....".getBytes("UTF-8")));
				sc.close();
			}
			else {
				sc.write(ByteBuffer.wrap(msg.getBytes("UTF-8")));
			}
			System.out.println(msg);
			System.out.println("处理完毕........");
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}

	private static void dealwithAccept(SelectionKey key) {
		System.out.println("新的连接请求..........");
		try {
			ServerSocketChannel sChannel = (ServerSocketChannel) key.channel();
			SocketChannel socketChannel = sChannel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(selector,SelectionKey.OP_READ);
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
		System.out.println("客户端连接成功..........");
	}
}
