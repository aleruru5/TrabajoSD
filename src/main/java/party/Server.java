package party;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{
	
	private int puerto;
	
	private ServerSocket ss;
	
	public ServerSocket getServerSocket() {
		return ss;
	}

	public Server(int puerto) {
		super();
		this.puerto = puerto;
	}

	public void run() {
		try (ServerSocket serversocket = new ServerSocket(puerto);) {
			ss = serversocket;
			ExecutorService pool = Executors.newCachedThreadPool();
			while (true) {
				try {
					Socket s = ss.accept();
					ManageRequest m = new ManageRequest(new DataInputStream(s.getInputStream()), new DataOutputStream(s.getOutputStream()));
					m.run();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}                                     
	}

}
