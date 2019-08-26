package nio;

import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadIOServer {
	public static void main(String[]args){
		int port=2019;
		try {
			ServerSocket serverSocket;
			Socket socket;
			while (true){
				serverSocket=new ServerSocket(port);
				socket=serverSocket.accept();
				new Thread(new ServerHandler(socket)).start();
			}
		}catch (Exception e){
			e.getMessage();
		}
	}
}
