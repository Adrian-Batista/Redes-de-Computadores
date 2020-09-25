package Principal;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Entidades.Vetor;

public class Main {

	private static Socket s;
	private static ServerSocket ss;

	public static void main(String[] args) throws IOException {
		ss = new ServerSocket(2800);
		while (true) {
			String auxiliar = DadoCliente();

			if(auxiliar.contains("vetor")) {
				Vetor.CarregaVetor(ss);
			}
			if(auxiliar=="encadeada") {

			}
			if(auxiliar=="array") {

			}	
		}
	}

	public static String DadoCliente() throws IOException {
		BufferedReader entrada;
		DataOutputStream saida;
		String dadoCliente = null;

		s = ss.accept();

		entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));  // lendo a escolha do cliente de qual tipo de armazenamento..
		dadoCliente = entrada.readLine();										 // ...será feito, vetor, encadeada ou Array.

		saida = new DataOutputStream(s.getOutputStream());
		saida.flush();
		entrada.close();
		saida.close();

		return dadoCliente;
	}

}
