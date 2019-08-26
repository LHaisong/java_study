package nio;

import java.io.*;
import java.net.Socket;

public class ServerHandler implements Runnable {
	private Socket socket;
	PrintWriter pw;
	BufferedReader br;
	public ServerHandler(Socket s){
		this.socket=s;
	}
	public void initStream()throws IOException {
		pw=new PrintWriter(socket.getOutputStream(),true);
		br=new BufferedReader(new InputStreamReader(System.in));
	}
	@Override
	public void run() {
		try{
			initStream();
			String inpuLines;
			while ((inpuLines=br.readLine())!=null){
				pw.println(inpuLines);
				pw.flush();
			}
		}catch (Exception e){
			e.getMessage();
		}
	}
}
