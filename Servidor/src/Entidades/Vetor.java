package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Principal.Main;

public class Vetor {

	private static Socket s;

	public static void CarregaVetor(ServerSocket ss) throws IOException { // ~~~~~~~~~~~~~~~~~~~~~~ M�TODO VETOR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		int[] vetor = new int[250000];
		BufferedReader entrada;
		DataOutputStream saida;
		String dadoCliente = null;

		for(int auxiliar = 0; auxiliar<250000; auxiliar++) { // ~~~~~~~~~~~~ carregando o vetor com os dados recebidos da conex�o. 
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

		String auxiliar = Main.DadoCliente();

		if(auxiliar.contains("InsertionSort")) {

			long tempoInicial = System.currentTimeMillis();
			insertionSortVetor(vetor);
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
			System.in.read();
		}

		if(auxiliar.contains("QuickSort")) {
			long tempoInicial = System.currentTimeMillis();
			quickSortVetor(vetor, 0, vetor.length);
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
			System.in.read();
		}

		for(int i = 0; i<vetor.length; i++) {
			System.out.println("Valor: "+ vetor[i]);
		}

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

}
