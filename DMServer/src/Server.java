import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Thread {
	private static final boolean TRUE = false;
	private InputStream is;
	private OutputStream os;
	private ServerSocket serverSocket;
	private ObjectInputStream ois;
	ObjectOutputStream oos;
	Socket socket;
	
	public void run(){
		try{
			try{
				serverSocket = new ServerSocket(54321);
			} catch(IOException e1){
				e1.printStackTrace();
			}
			while(true){
				try
				{
					System.out.println("��û ���");
					socket=serverSocket.accept();
					System.out.println("������ Ŭ���̾�Ʈ : "+socket.getInetAddress());
					is=socket.getInputStream();
					os=socket.getOutputStream();
					ois=new ObjectInputStream(is);
					oos=new ObjectOutputStream(os);
					String msg=(String)ois.readObject();
					System.out.println("Ŭ���̾�Ʈ���� ���� �޽��� : "+msg);
					String retMsg="�����κ��� �ǵ��ƿ� �޽��� : "+msg;
					oos.writeObject(retMsg);
					socket.close();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		}
		catch (ClassNotFoundException e){
			e.printStackTrace();
		}
	}
	/*
	public static void main(String[] args){
		new Server().run();
	}
	*/
}
