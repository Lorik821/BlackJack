package client.metier.reseau;

import java.net.*;
import java.nio.charset.StandardCharsets;

import javax.swing.JOptionPane;

import client.metier.Metier;
import client.vue.FenetrePrincipale;
import client.vue.InterpreteurMessages;
import client.vue.Litiere;

import java.io.*;

public class Client implements Runnable {
	private InterpreteurMessages im;
	private Socket socket = null;
	private Thread thread = null;
	private DataInputStream console = null;
	private DataOutputStream streamOut = null;
	private ClientThread client = null;
	private String message = "";
	private boolean msgPasse = false;
	private String pseudo = "inconnu";
	
	private Metier metier;
	private Litiere litiere;
	private FenetrePrincipale fp;

	public Client(String serverName, int serverPort, Metier metier, FenetrePrincipale fp) {		
		this.metier = metier;
		litiere = new Litiere (this, pseudo);
		fp.getPanelJeux().add(litiere);
		litiere.setBounds(0, 0, 300, 250);
		System.out.println("La fenetre principale est : " + fp);
		this.im = new InterpreteurMessages (litiere, metier, fp);
		System.out.println("Establishing connection at " + serverPort + ". Please wait ...");
		try {
			socket = new Socket(serverName, serverPort);
			System.out.println("Connected: " + socket);
			start();
		} catch (UnknownHostException uhe) {
			System.out.println("Host unknown: " + uhe.getMessage());
		} catch (IOException ioe) {
			System.out.println("Unexpected exception: " + ioe.getMessage());
		}
	}
	
	public String getPseudo () { return pseudo; } 

	public void run() {
		while (thread != null) {
			try {
				try { Thread.sleep(10); }
				catch (InterruptedException e) {}
				if (msgPasse) {
					System.out.println("Le message est : "+ message);
					streamOut.writeUTF(message);
					streamOut.flush();
					msgPasse = false;
				}
			} catch (IOException ioe) {
				System.out.println("Sending error: " + ioe.getMessage());
				stop();
			}
		}
	}

	public void handle(String msg) {
		if (msg.equals(".bye")) {
			System.out.println("Good bye. Press RETURN to exit ...");
			stop();
		} else {
			im.InterpreterMessage(im.separerMessage(msg));
		}
	}

	public void start() throws IOException {
		console = new DataInputStream(new ByteArrayInputStream(message.getBytes(StandardCharsets.UTF_8)));
		streamOut = new DataOutputStream(socket.getOutputStream());
		if (thread == null) {
			client = new ClientThread(this, socket);
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
		try {
			if (console != null)
				console.close();
			if (streamOut != null)
				streamOut.close();
			if (socket != null)
				socket.close();
		} catch (IOException ioe) {
			System.out.println("Error closing ...");
		}
		client.close();
		client.stop();
	}

	public void setMessage(String msg) {
		message = msg;
		msgPasse = true;
	}
	
	public void setLM (String msg, String pseudo)
	{
		this.pseudo = pseudo;
		message = msg;
		msgPasse = true;
	}
	
	public void setMessageLitiere (String msg)
	{
		message = pseudo+":"+"litiere"+":"+msg;
		msgPasse = true;
	}
	
	public void setMessageCarte (String msg)
	{
		message = pseudo+":"+"carte"+":"+msg;
		msgPasse = true;
	}
	
	public void setMessagePasser (String msg)
	{
		message = pseudo+":"+"passe"+":"+msg;
		msgPasse = true;
	}
	/*
	 * public static void main(String args[]) { ChatClient client = null; if
	 * (args.length != 2) System.out.println("Usage: java ChatClient host port"
	 * ); else client = new ChatClient(args[0], Integer.parseInt(args[1])); }
	 */

	public void setMessageCommencer() {
		message = pseudo+":commencer: ";
		msgPasse = true;
		
	}

	public void setMessageDeconection() {
		message = pseudo + ":disconnect: ";
		msgPasse = true;
	}
}