package Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
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
			quickSortArray(lista, 0, 0);
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
	
	private static List<Integer> quickSortArray(List<Integer> list, int a, int b)
	{
	    if (a >= b) 
	        return list;

	    int pivot = list.get(b);

	    int left = a;
	    int right = b;

	    while (left < right)
	    {
	        while(list.get(left).compareTo(pivot) < 0)
	            left++;

	        while(list.get(right).compareTo(pivot) > 0)
	            right--;

	        if (right > left);
	        {
	            Collections.swap(list, left, right);
	        }
	    }

	    quickSortArray(list, a, right-1);
	    quickSortArray(list, right+1, b);
	    
	    
	    
	    return list;
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
