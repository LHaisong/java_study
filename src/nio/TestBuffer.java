package nio;

import java.nio.ByteBuffer;

public class TestBuffer {
	public static void main(String[]args)
	{
		ByteBuffer buffer=ByteBuffer.allocate(1024);
		System.out.println("初始化：limit="+buffer.limit());
		System.out.println("初始化：position="+buffer.position());
		System.out.println("初始化：capacity="+buffer.capacity());
		System.out.println("初始化：mark="+buffer.mark());
		System.out.println("-----------------------");
		String s="test buffer";
		buffer.put(s.getBytes());
		System.out.println("使用后：limit="+buffer.limit());
		System.out.println("使用后：position="+buffer.position());
		System.out.println("使用后：capacity="+buffer.capacity());
		System.out.println("使用后：mark="+buffer.mark());
		buffer.flip();
		byte[]bytes=new byte[buffer.limit()];
		buffer.get(bytes);
		System.out.println(new String(bytes,0,bytes.length));
		System.out.println("flip后：limit="+buffer.limit());
		System.out.println("flip后：position="+buffer.position());
		System.out.println("flip后：capacity="+buffer.capacity());
		System.out.println("flip后：mark="+buffer.mark());
	}
}
