package clients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
       

 public static void main(String[] args) {
    
    final Socket clientSocket;
    final BufferedReader in;
    final PrintWriter out;
    final Scanner sc = new Scanner(System.in);

    System.out.println("Entrez votre nom: ");
    Scanner scanner = new Scanner(System.in);
    String nom = sc.nextLine();
    
    try {
    
    clientSocket = new Socket("localhost",5000);
    
    //flux pour envoyer
    out = new PrintWriter(clientSocket.getOutputStream());
    //flux pour recevoir
    in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    
    Thread envoyer = new Thread(new Runnable() {
    String msg;
    @Override
    public void run() {
    while(true){
        msg = sc.nextLine();
        out.println(nom+": " +msg);
        out.flush();
    }
    }
    });
    envoyer.start();
    
    Thread recevoir = new Thread(new Runnable() {
    String msg;

    @Override
    public void run() {
        try {
            msg = in.readLine();
            while(msg!=null){
            System.out.println(msg);
            msg = in.readLine();
        }
            System.out.println("Server déconecté");
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    });
    recevoir.start();
    
    } catch (IOException e) {
    e.printStackTrace();
    }
    }



}