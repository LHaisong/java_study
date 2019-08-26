package nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket是属于阻塞IO，
 * 服务器启动后等待客户端的连接
 */
public class BlockingIOServer {
	public static void main(String[]args){
		int port=2019;
		try{
			ServerSocket server=new ServerSocket(port);
			Socket clientSocket=server.accept();
			BufferedReader br=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			char[]chars=new char[1024];
			int len;
			StringBuilder sb=new StringBuilder();
			while ((len=br.read(chars))!=-1){
				sb.append(new String(chars,0,len));
			}
			System.out.println(sb);
			clientSocket.close();
			br.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
