package fenetre;

import java.awt.*;

import javax.swing.*;

import clients.Client;

public class Fenetre extends JFrame{
	Client client;
	JLabel allMessage;
	JButton sendMessage;
	JButton sendFile;
	JTextField jTextField;	
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public JLabel getAllMessage() {
		return allMessage;
	}
	public void setAllMessage(JLabel allMessage) {
		this.allMessage = allMessage;
	}
	public JButton getSendMessage() {
		return sendMessage;
	}
	public void setSendMessage(JButton sendMessage) {
		this.sendMessage = sendMessage;
	}
	public JButton getSendFile() {
		return sendFile;
	}
	public void setSendFile(JButton sendFile) {
		this.sendFile = sendFile;
	}
	public JTextField getjTextField() {
		return jTextField;
	}
	public void setjTextField(JTextField jTextField) {
		this.jTextField = jTextField;
	}

	public Fenetre(Client client) {
		this.setClient(client);
		this.setTitle("Messagerie");
		this.setAllMessage(new JLabel());
		this.getAllMessage().setSize(400,400);
		this.getAllMessage().setBackground(Color.red);
		this.setSendMessage(new JButton("Envoyer"));
		this.setSendFile(new JButton("Envoyer un fichier"));
		this.setjTextField(new JTextField());
		this.getjTextField().setSize(800,50);
		this.getjTextField().setText("                                                                           ");
		this.setLayout(new BorderLayout());
		JPanel message = new JPanel();
		message.add(this.getAllMessage());
		JPanel allConnected = new JPanel();
		allConnected.setBackground(Color.blue);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(1,2));
		mainPanel.add(allConnected);
		mainPanel.add(message);
		JPanel actionPanel = new JPanel();
		actionPanel.add(this.getjTextField());
		actionPanel.add(this.getSendMessage());
		actionPanel.add(this.getSendFile());
		
		this.setSize(1200,720);
		this.add(mainPanel,BorderLayout.CENTER);
		this.add(actionPanel,BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
}
