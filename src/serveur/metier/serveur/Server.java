package serveur.metier.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import serveur.controleur.Controleur;
import serveur.metier.InterpreteurMessages;
import serveur.metier.Joueur;
import serveur.metier.Metier;

public class Server implements Runnable {
	private InterpreteurMessages im;
	private ServerThread clients[] = new ServerThread[4];
	private ServerSocket server = null;
	private Thread thread = null;
	private int clientCount = 0;
	private Metier metier;
	private Controleur ctrl;

	public Server(int port, InterpreteurMessages im, Metier metier, Controleur ctrl) {
		this.im = im;
		this.metier = metier;
		this.ctrl = ctrl;
		try {
			System.out.println("Binding to port " + port + ", please wait  ...");
			server = new ServerSocket(port);
			System.out.println("Server started: " + server);
			start();
		} catch (IOException ioe) {
			System.out.println("Can not bind to port " + port + ": " + ioe.getMessage());
		}
	}

	public void run() {
		while (thread != null) {
			try {
				System.out.println("Waiting for a client ...");
				addThread(server.accept());
			} catch (IOException ioe) {
				System.out.println("Server accept error: " + ioe);
				stop();
			}
		}
	}

	public void start() {
		if (thread == null) {
			thread = new Thread(this);
			thread.start();
		}
	}

	public void stop() {
		if (thread != null) {
			thread.stop();
			thread = null;
		}
	}

	private int findClient(int ID) {
		for (int i = 0; i < clientCount; i++)
			if (clients[i].getID() == ID)
				return i;
		return -1;
	}

	public synchronized void handle(int ID, String input) {
		if (input.equals(".bye")) {
			int index = findClient(ID);
			clients[index].send(".bye");
			metier.retirerJoueur(index);
			remove(ID);
		} else {
			/*
			 * Ici je peux capturer le message envoy� par l'un des clients et en
			 * faire ce que je veux. Enfin je met ici l'appel de m�thodes qui
			 * pourront traiter le message, puis choisir quoi renvoyer.
			 */
			System.out.println("Message : " + input);
			String[] messages = im.separerMessage(input);
			int retour = im.InterpreterMessage(messages); 
			if (retour == 0) {
				clients[findClient(ID)].send("serveur:connect:failed");
				System.out.println("Message : serveur:connect:failed");
				clients[findClient(ID)] = null;
				System.out.println("un client a ete tej !");
			} else if (retour == 1) {
				clients[findClient(ID)].send("serveur:connect:succed");
				System.out.println("Message : serveur:connect:succed");
				metier.addUser(messages[0], ctrl.getCredit(messages[0]), ID);
				clients[findClient(ID)].send("serveur:credits:" + ctrl.getCredit(messages[0]));
				this.sendAtAllClients("serveur:joueur:" + metier.getJoueurEnCours().getPseudo());
				if(clientCount == 1) clients[0].send("serveur:commencer: ");
				ctrl.getSgbd().updateCredit(messages[0], ctrl.getCredit(messages[0])-10);
			} else if (retour == 2) {
				// sendAtClient (ID, input);
				sendAtAllClients(input);
			}

			/*
			 * for (int i = 0; i < clientCount; i++) { clients[i].send(ID + ": "
			 * + input);
			 */

		}
	}

	public synchronized void remove(int ID) {
		int pos = findClient(ID);
		if (pos >= 0) {
			ServerThread toTerminate = clients[pos];
			System.out.println("Removing client thread " + ID + " at " + pos);
			if (pos < clientCount - 1)
				for (int i = pos + 1; i < clientCount; i++)
					clients[i - 1] = clients[i];
			clientCount--;
			try {
				toTerminate.close();
			} catch (IOException ioe) {
				System.out.println("Error closing thread: " + ioe);
			}
			toTerminate.stop();
		}
	}

	private void addThread(Socket socket) {
		if (clientCount < clients.length) {
			System.out.println("Client accepted: " + socket);
			clients[clientCount] = new ServerThread(this, socket);
			try {
				clients[clientCount].open();
				clients[clientCount].start();
				clientCount++;
			} catch (IOException ioe) {
				System.out.println("Error opening thread: " + ioe);
			}
		} else
			System.out.println("Client refused: maximum " + clients.length + " reached.");
	}

	private void sendAtClient(int ID, String message) {
		clients[ID].send(message);
	}

	public void sendAtAllClients(String message) {
		System.out.println(" Le message est : " + message);
		for (int i = 0; i < clientCount; i++)
			clients[i].send(message);
	}
	/*
	 * public static void main(String args[]) { Server server = null; if
	 * (args.length != 1) System.out.println("Usage: java ChatServer port");
	 * else server = new Server(5600); }
	 */
	
	public Controleur getCtrl() {
		return this.ctrl;
	}
	
	public Metier getMetier() {
		return this.metier;
	}
	
}