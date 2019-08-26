package nio;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 使用线程池的方式实现服务器端
 * 优点：在短连接情况下效率很高，但是在长连接时由于
 * 连接长时间占用线程导致效率降低
 */
public class ThreadPoolIOServer {
	public static void main(String[]args) {
		ThreadPoolExecutor pool = new ThreadPoolExecutor(3, 5, 0L,
				TimeUnit.SECONDS, new ArrayBlockingQueue<>(3));
		int port = 2019;
		try {
			ServerSocket serverSocket = new ServerSocket(port);
			Socket socket=serverSocket.accept();
			pool.submit(new ServerHandler(socket));
		}catch (Exception e){
			System.out.println(e.getMessage());
		}
	}
}
