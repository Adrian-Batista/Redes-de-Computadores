package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

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
				OrdenaVetor();
			}
			if(dadoCliente=="encadeada") {
				
			}
			if(dadoCliente=="array") {
				
			}
			
			
			
		}
	
	}
	
	public static void OrdenaVetor() throws IOException { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO VETOR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		int[] vetor = new int[250000];
		BufferedReader entrada;
		DataOutputStream saida;
		String dadoCliente = null;
		
		for(int auxiliar = 0; auxiliar<250000; auxiliar++) { // ~~~~~~~~~~~~ carregando o vetor com os dados recebidos da conexão. 
			s = ss.accept();
			entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			dadoCliente = entrada.readLine();
			vetor[auxiliar] = Integer.parseInt(dadoCliente);
			System.out.println("\nValor: "+ vetor[auxiliar]);
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes("recebido!");
			saida.flush();
			entrada.close();
			saida.close();
		}
		
		 long tempoInicial = System.currentTimeMillis();

	     insertionSortVetor(vetor);

	     long tempoFinal = System.currentTimeMillis();

	     System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
	     
	     System.in.read();
	     
	     for(int i = 0; i<vetor.length; i++) {
	    	 System.out.println("Valor: "+ vetor[i]);
	     }
		
	}
	
	public static void OrdenaEncadeada() { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO ENCADEADA ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
	}

	public static void OrdenaArray() { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO ARRAY ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	}
	
	public static void insertionSortVetor(int[] vetor) {
	    int j;
	    int key;
	    int i;

	    for (j = 1; j < vetor.length; j++)
	    {
	      key = vetor[j];
	      for (i = j - 1; (i >= 0) && (vetor[i] > key); i--)
	      {
	         vetor[i + 1] = vetor[i];
	       }
	        vetor[i + 1] = key;
	    }
	}

}
