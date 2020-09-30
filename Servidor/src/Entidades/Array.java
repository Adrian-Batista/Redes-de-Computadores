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

			Runtime rt = Runtime.getRuntime();
			long tempoInicial = System.currentTimeMillis();
			insertionSortArray(lista);
			long tempoFinal = System.currentTimeMillis();
			long memoria = (Runtime.getRuntime().freeMemory() - rt.freeMemory());

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
			System.out.println("Memória utilizada = " + memoria);
			System.in.read();
		}

		if(auxiliar.contains("QuickSort")) {
			
			Runtime rt = Runtime.getRuntime();
			long tempoInicial = System.currentTimeMillis();
			lista = quickSortArray(lista);
			long tempoFinal = System.currentTimeMillis();
			long memoria = (Runtime.getRuntime().freeMemory() - rt.freeMemory());

			System.out.println("Executado em = " + (tempoFinal - tempoInicial) + " ms");
			System.out.println("Memória utilizada = " + memoria);
			System.in.read();
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

	public static void insertionSortArray(List<Integer> Array) {

		int i, j;
		int key = 0;

		for (i = 1; i < Array.size(); i++) {
			key = Array.get(i);
			j = i;
			while((j > 0) && (Array.get(j - 1) > key)) {
				Array.set(j,Array.get(j - 1));
				j--;
			}
			Array.set(j,key);
		}
	}


	private static List<Integer> quickSortArray(List<Integer> list)
	{
		if (list.size() <= 1) 
			return list;  

		List<Integer> sorted = new ArrayList<Integer>();
		List<Integer> lesser = new ArrayList<Integer>();
		List<Integer> greater = new ArrayList<Integer>();
		Integer pivot = list.get(list.size()-1);
		for (int i = 0; i < list.size()-1; i++)
		{
			
			if (list.get(i).compareTo(pivot) < 0)
				lesser.add(list.get(i));    
			else
				greater.add(list.get(i));   
		}

		lesser = quickSortArray(lesser);
		greater = quickSortArray(greater);

		lesser.add(pivot);
		lesser.addAll(greater);
		sorted = lesser;

		return sorted;
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
