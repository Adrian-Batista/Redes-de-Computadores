package Entidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Conexao {
	private static Socket socket;

	private static Socket GeraSocket() throws UnknownHostException, IOException {
		socket = new Socket("localhost", 2800);
		return socket;

	}

	public static void EnviandoDados(String auxiliar) throws UnknownHostException, IOException {

		socket = GeraSocket();
		BufferedWriter wr;
		BufferedReader rd;

		wr = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())); 
		wr.write(auxiliar);
		wr.write("\n");
		wr.flush();

		rd = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		String requestString = rd.readLine();
		System.out.println("Dado: " + requestString);

		rd.close();
		wr.close();

	}

	public static String RecebendoDados() throws UnknownHostException, IOException {
		socket = GeraSocket();
		BufferedReader rd;

		rd = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
		String requestString = rd.readLine();
		rd.close();
		return requestString;
	}

}
