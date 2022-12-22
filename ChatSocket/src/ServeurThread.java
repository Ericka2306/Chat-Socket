package serveur;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServeurThread implements Runnable{
	Serveur server;
	Socket client;
	Thread serverThread;
	BufferedReader receive;
	PrintWriter send;
	int clientId = 0;
	String clientName;
	String message;
		
	public Serveur getServer() {
		return server;
	}
	public void setServer(Serveur server) {
		this.server = server;
	}
	public Socket getClient() {
		return client;
	}
	public void setClient(Socket client) {
		this.client = client;
	}
	public Thread getServerThread() {
		return serverThread;
	}
	public void setServerThread(Thread serverThread) {
		this.serverThread = serverThread;
	}
	public BufferedReader getReceive() {
		return receive;
	}
	public void setReceive(BufferedReader receive) {
		this.receive = receive;
	}
	public PrintWriter getSend() {
		return send;
	}
	public void setSend(PrintWriter send) {
		this.send = send;
	}
	public int getClientId() {
		return clientId;
	}
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public ServeurThread(Socket client, Serveur server) {
		this.setClient(client);
		this.setServer(server);
		
		try {
			this.setReceive(new BufferedReader(new InputStreamReader(this.getClient().getInputStream())));
			this.setSend(new PrintWriter(this.getClient().getOutputStream()));
			this.setClientId(this.getServer().addClient(this.getClient()));
			this.setServerThread(new Thread(this));
			this.getServerThread().start();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	@Override
	public void run() {
		this.getServer().sendToAll("Client " + this.getClientId() + " s'est connecté.");
		
		try {
			this.setMessage(this.getReceive().readLine());
			while (this.getMessage()!=null) {
				this.getServer().sendToAll(this.getMessage());
				this.setMessage(this.getReceive().readLine());
			}
			
			this.getServer().sendToAll("Client " + this.getClientId() + " s'est déconnecté.");
			this.getSend().flush();
		} catch (Exception e) {
			// TODO: handle exception
		}
		finally {
			try {
				this.getReceive().close();
				this.getServer().deleteClient(this.getClientId());
				this.getClient().close();
				this.getServerThread().join();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

}
