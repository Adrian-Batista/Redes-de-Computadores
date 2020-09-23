package Entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexao {
	private static Socket socket; //Criando o Socket

	public static void EnviandoDados() throws UnknownHostException, IOException {
		
		socket = new Socket("localhost", 2800); //Create a Client Socket for "localhost" address and port

		//Create a Request Buffer
		BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
		wr.write("Request"); //Some Request data
		wr.write("\n"); //Line end
		wr.flush(); //Send it

		//Create a Reply Buffer
		BufferedReader rd = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		String requestString = rd.readLine(); //Read Reply Information
		System.out.println("Server replied: " + requestString); //Print reply
		rd.close();
		wr.close();

	}

}
