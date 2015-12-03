import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server2 implements Runnable {
	public static int port = 54321;
	public static String ip="192.168.0.2";

	public void run() {
		try {
			System.out.println("S : Connecting... ");;
			ServerSocket serverSocket = new ServerSocket(port);
			while (true){
				Socket client = serverSocket.accept();
				System.out.println("S : Receving... ");
				try {
					BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
					String str = in.readLine();
					System.out.println("S: Received: '" + str + "'");
					PrintWriter out = new PrintWriter( new BufferedWriter( new OutputStreamWriter(client.getOutputStream())),true);
					out.println("Server Received "+ str);
				} catch(Exception e) {
					System.out.println("S : Error");
					e.printStackTrace();
				} finally {
					client.close();
					System.out.println("S : Done.");
				}
			}
		} catch (Exception e) {
			System.out.println("S : Error");
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread desktopServerThread = new Thread(new Server2());
		desktopServerThread.start();
	}
*/
}
