package nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
	public static void main(String[]args){
		try {
			Socket socket = new Socket("localhost", 2019);
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter pw = new PrintWriter(socket.getOutputStream());
			String lines;
			while ((lines = br.readLine()) != null) {
				pw.write(lines);
				pw.flush();
			}
			socket.close();
			br.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
