package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Vetor {

	private static Socket s;

	public static void CarregaVetor(ServerSocket ss) throws IOException { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO VETOR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		int[] vetor = RecebeVetor(ss);

		String auxiliar = EscolheOrdenacao(ss);

		if(auxiliar.contains("InsertionSort")) {
			
			Runtime rt = Runtime.getRuntime();
			long tempoInicial = System.currentTimeMillis();
			insertionSortVetor(vetor);
			long tempoFinal = System.currentTimeMillis();
			long memoria = (Runtime.getRuntime().freeMemory() - rt.freeMemory());

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
			System.out.println("Memória utilizada = " + memoria);
			System.in.read();
			
		}

		if(auxiliar.contains("QuickSort")) {
			
			Runtime rt = Runtime.getRuntime();
			long tempoInicial = System.currentTimeMillis();
			quickSortVetor(vetor, 0, vetor.length-1);
			long tempoFinal = System.currentTimeMillis();
			long memoria = (Runtime.getRuntime().freeMemory() - rt.freeMemory());

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
			System.out.println("Memória utilizada = " + memoria);
			System.in.read();
		}
		
		EnviaVetorOrdenado(ss, vetor);

	}
	
	private static int[] RecebeVetor(ServerSocket ss) throws IOException {
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
		return vetor;
		
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

	private static void quickSortVetor(int[] vetor, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separarVetor(vetor, inicio, fim);
			quickSortVetor(vetor, inicio, posicaoPivo - 1);
			quickSortVetor(vetor, posicaoPivo + 1, fim);
		}
	}

	private static int separarVetor(int[] vetor, int inicio, int fim) {
		int pivo = vetor[inicio];
		int i = inicio + 1, f = fim;
		while (i <= f) {
			if (vetor[i] <= pivo)
				i++;
			else if (pivo < vetor[f])
				f--;
			else {
				int troca = vetor[i];
				vetor[i] = vetor[f];
				vetor[f] = troca;
				i++;
				f--;
			}
		}
		vetor[inicio] = vetor[f];
		vetor[f] = pivo;
		return f;
	}
	
	private static String EscolheOrdenacao(ServerSocket ss) throws IOException {
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
	
	private static void EnviaVetorOrdenado(ServerSocket ss,int[] vetor) throws IOException {
		DataOutputStream saida;

		for(int auxiliar = 0; auxiliar<250000; auxiliar++) { // ~~~~~~~~~~~~ Enviando o vetor com os dados recebidos da ordenacao. 
			s = ss.accept();
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes(String.valueOf(vetor[auxiliar]));
			System.out.println("\n" + auxiliar + ") Enviado: " + vetor[auxiliar]);
			saida.flush();
			saida.close();
		}
	}

}
