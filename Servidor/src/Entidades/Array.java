package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Array {
	private static Socket s;
	public static List<Integer> arrayList = new ArrayList<Integer>();

	public static void CarregaArray(ServerSocket ss) throws IOException { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO LISTA ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		List<Integer> lista = RecebeArray(ss);

		String auxiliar = EscolheOrdenacao(ss);

		if(auxiliar.contains("InsertionSort")) {

			long tempoInicial = System.currentTimeMillis();
			insertionSortArray(lista);
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
		}

		if(auxiliar.contains("QuickSort")) {
			long tempoInicial = System.currentTimeMillis();
			//quickSortVetor(lista, 0, lista.size()-1);
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
		}

		EnviaArrayOrdenado(ss, lista);

	}

	private static List<Integer> RecebeArray(ServerSocket ss) throws IOException {
		BufferedReader entrada;
		DataOutputStream saida;
		String dadoCliente = null;

		for(int auxiliar = 0; auxiliar<250000; auxiliar++) { // ~~~~~~~~~~~~ carregando a lista com os dados recebidos da conexão. 
			s = ss.accept();
			entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			dadoCliente = entrada.readLine();
			arrayList.add(auxiliar, Integer.parseInt(dadoCliente));
			System.out.println("\nValor: "+ arrayList.get(auxiliar));
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes("recebido!");
			saida.flush();
			entrada.close();
			saida.close();
		}
		return arrayList;

	}

	private static void insertionSortArray(List<Integer> lista) {
		int key = 0; //value to compare
		int j = 0; //index of other value
		
		//loop through list
		int aux = 0;
		for(int i = 0; i < lista.size(); i++){
			key = lista.get(i); //get value at index
			j = i - 1; //get index to previous value
			//compare value at the jth index to key
			while(j >= 0 && lista.get(j) > key){
				lista.set(j+1, lista.get(j));
				j--; //move to previous index
				System.out.println("\nDados ordenados: " + aux++);
			}
			lista.set(j+1, key); //set with new value
			
		}
	}
	
	

	private static void quickSortVetor(int[] vetor, int inicio, int fim) {
		if (inicio < fim) {
			int posicaoPivo = separarArray(vetor, inicio, fim);
			quickSortVetor(vetor, inicio, posicaoPivo - 1);
			quickSortVetor(vetor, posicaoPivo + 1, fim);
		}
	}

	private static int separarArray(int[] vetor, int inicio, int fim) {
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

	private static void EnviaArrayOrdenado(ServerSocket ss,List<Integer> lista) throws IOException {
		DataOutputStream saida;

		for(int auxiliar = 0; auxiliar<250000; auxiliar++) { // ~~~~~~~~~~~~ Enviando a lista com os dados recebidos da ordenacao. 
			s = ss.accept();
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes(String.valueOf(lista.get(auxiliar)));
			System.out.println("\n" + auxiliar + ") Enviado: " + lista.get(auxiliar));
			saida.flush();
			saida.close();
		}
	}

}
