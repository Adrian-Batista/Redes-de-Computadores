package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import Entidades.Encadeada.Elemento;

public class Encadeada {

	private static Socket s;

	public static void CarregaEncadeada(ServerSocket ss) throws IOException { // ~~~~~~~~~~~~~~~~~~~~~~ MÉTODO VETOR ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

		Elemento inicio = RecebeEncadeada(ss);

		//String auxiliar = EscolheOrdenacao(ss);

		/*if(auxiliar.contains("InsertionSort")) {

			long tempoInicial = System.currentTimeMillis();
			//insertionSortVetor(vetor);
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
		}

		if(auxiliar.contains("QuickSort")) {
			long tempoInicial = System.currentTimeMillis();
			//quickSortVetor(vetor, 0, vetor.length-1);
			long tempoFinal = System.currentTimeMillis();

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
		}*/

		EnviaEncadeadaOrdenada(ss, inicio);

	}

	public static class Elemento{

		int valor;
		Elemento prox;

		public Elemento(int valor){
			this.valor = valor;
			this.prox = null;
		}		
		public Elemento (int valor, Elemento prox){
			this.valor = valor;
			this.prox = prox;
		}
	}

	static Elemento inicio;
	Elemento fim;
	int tamanho;

	public Encadeada(){
		inicio = null;
		fim = null;
		tamanho = 0;
	}

	private static Elemento RecebeEncadeada(ServerSocket ss) throws IOException {
		BufferedReader entrada = null;
		DataOutputStream saida;
		String dadoCliente = null;

		Elemento inicio = null;
		Elemento fim = null;
		int tamanho =0;

		for(int auxiliar = 0; auxiliar<250000; auxiliar++) {
			s = ss.accept();
			entrada = new BufferedReader(new InputStreamReader(s.getInputStream()));
			dadoCliente = entrada.readLine();
			Elemento novo = new Elemento(Integer.parseInt(dadoCliente));
			if (tamanho == 0){
				inicio = novo;
				fim = novo;
			}else{
				fim.prox = novo;
				fim = novo;
			}
			tamanho++;

			System.out.println("\nValor: "+ dadoCliente);
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes("recebido!");
			saida.flush();
			entrada.close();
			saida.close();
		}



		return inicio;		
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

	private static void EnviaEncadeadaOrdenada(ServerSocket ss,Elemento inicio) throws IOException {
		DataOutputStream saida;
		int cont = 0;
		Elemento aux = inicio;
		while(aux!=null){
			s = ss.accept();
			saida = new DataOutputStream(s.getOutputStream());
			saida.writeBytes(String.valueOf(aux.valor));
			System.out.println("\n" + cont++ + ") Enviado: " + aux.valor);
			saida.flush();
			saida.close();
			aux = aux.prox;


		}
	}

}
