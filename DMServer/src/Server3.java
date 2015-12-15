import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;


public class Server3 extends Thread{

	
		private static final boolean TRUE = false;
		private InputStream is;
		private OutputStream os;
		private ServerSocket serverSocket;
		private ObjectInputStream ois;
		ObjectOutputStream oos;
		Socket socket;
		public void run(){
			//try
			
				try {
					try {
						serverSocket = new ServerSocket(54321);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					while(true){
						try
						{
							System.out.println("요청대기");
							socket=serverSocket.accept();
							System.out.println("접속한 클라이언트 : "+socket.getInetAddress());
							is=socket.getInputStream();
							os=socket.getOutputStream();
							ois = new ObjectInputStream(is);
							oos = new ObjectOutputStream(os);
							String msg = (String)ois.readObject();
							System.out.println("클라이언트에서 보낸 메시지 : " + msg);
							String retMsg="서버로부터 되돌아온 메시지 : "+msg;
							oos.writeObject(retMsg);
							socket.close();
						}
							catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					} 
				 catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		
		/*
		ServerSocket serverSocket = null;
		Socket clientSocket = null;
		PrintWriter out = null;
		BufferedReader in = null;
		
		serverSocket = new ServerSocket(54321);
		
		try {
			clientSocket = serverSocket.accept();
			System.out.println("Client connect");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			while (true) {
				String inputLine = null;
				inputLine = in.readLine();
				System.out.println("클라이언트로부터 받은 문자열 : " + inputLine);
				out.println(inputLine);
				if (inputLine.equals("quit"))
					break;
			}
			out.close();
			in.close();
			clientSocket.close();
			serverSocket.close();
			} catch (Exception e){
				e.printStackTrace();
		}
		*/
	
	public static void main(String[] args){
		new Server3().run();
	}
}
