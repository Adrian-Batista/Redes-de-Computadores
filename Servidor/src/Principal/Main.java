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
			BufferedReader entrada;
			DataOutputStream saida;
			String dadoCliente = null;
			
			s = ss.accept();
			
			entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));  // lendo a escolha do cliente de qual tipo de armazenamento..
			dadoCliente = entrada.readLine();										 // ...será feito, vetor, encadeada ou Array.
			
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes("Os dados serão armazenados em " + dadoCliente);
			saida.flush();
			entrada.close();
			saida.close();
			
			if(dadoCliente.contains("vetor")) {
				Vetor.OrdenaVetor(ss);
			}
			if(dadoCliente=="encadeada") {
				
			}
			if(dadoCliente=="array") {
				
			}
			
			
			
		}
	
	}
	
	
	
	public static void OrdenaEncadeada() { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO ENCADEADA ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	}

	public static void OrdenaArray() { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO ARRAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	}
	
	

}
