package serveur;

import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ServerCloneException;
import java.util.Vector;

public class Serveur {
	Vector<Socket> clients = new Vector<Socket>();
	int nbrClients = 0;
	public Vector<Socket> getClients() {
		return clients;
	}

	public void setClients(Vector<Socket> clients) {
		this.clients = clients;
	}

	public int getNbrClients() {
		return nbrClients;
	}

	public void setNbrClients(int nbrClients) {
		this.nbrClients = nbrClients;
	}

	public static void main(String[] args) {
		Serveur serveur = new Serveur();
		
		final ServerSocket serverSocket;
		
		try {
			serverSocket = new ServerSocket(5000);
			
			while (true) {
				new ServeurThread(serverSocket.accept(),serveur);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public int addClient(Socket client) {
		this.setNbrClients(this.getNbrClients()+1);
		this.getClients().add(client);
		return this.getClients().size()-1;
	}
	
	public void deleteClient(int i) {
		this.setNbrClients(this.getNbrClients()-1);
		
		clients.removeElementAt(i);
	}
	
	public void sendToAll(String message) {
		for (int i = 0; i < clients.size(); i++) {
			try {
				PrintWriter out = new PrintWriter(clients.get(i).getOutputStream());
				out.println(message);
				
				out.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
