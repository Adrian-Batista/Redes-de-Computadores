package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	private static Socket s; // Create Socket
	private static ServerSocket ss; // Create a Server Socket

	public static void LigandoServidor() throws IOException {
		ss = new ServerSocket(2800); // Start a new server socket on port 2800
		while (true) {
			s = ss.accept();// Accept when a request arrives
			// String Buffer Reader
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(s.getInputStream()));
			String requestString = inFromClient.readLine(); // Convert it to String
			/* Some processing */
			System.out.println("Client sent : " + requestString); // Print Operation
			// Generate Stream Reply
			DataOutputStream outToClient = new DataOutputStream(s.getOutputStream());
			outToClient.writeBytes("Reply"); // Some Reply Data
			outToClient.writeBytes("\n"); // Line end
			outToClient.flush(); // Send it to client
			outToClient.close();

		}

	}

}
