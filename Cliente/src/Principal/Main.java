package Principal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Entidades.Conexao;
import Entidades.Encadeada;
import Entidades.Encadeada.Elemento;

public class Main {

	public static List<Integer> arrayList = new ArrayList<Integer>();
	public static Elemento aux;

	public static void main(String[] args) throws IOException {

		int opcao = 0;
		int cont = 0;
		int auxiliar = 0;
		String valor = null;
		int[] vetor = new int[250000];

		Random gerador = new Random();

		Scanner entrada = new Scanner(System.in);



		do {
			if(cont>0)
				Main.LimparTela();
			cont++;
			System.out.println("\n\n Cliente - Servidor ");	
			System.out.println("\n========================================= \n|\t\t\t\t\t|");
			System.out.println("| ( 1 ) - ENVIAR VIA VETOR              | \n|\t\t\t\t\t|");
			System.out.println("| ( 2 ) - ENVIAR VIA LISTA ENCADEADA    | \n|\t\t\t\t\t|");
			System.out.println("| ( 3 ) - ENVIA VIA ARRAY LIST          | \n|\t\t\t\t\t| ");
			System.out.println("| ( 4 ) - LISTAR DADOS ORDENADOS        | \n|\t\t\t\t\t| ");
			System.out.println("| ( 0 ) - SAIR                          | \n|\t\t\t\t\t|");
			System.out.println("=========================================\n");
			System.out.println(" Escolha uma das op��es: ");
			opcao = entrada.nextInt();
			entrada.nextLine();
			System.out.print("\n");

			switch (opcao) {

			case 1: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO VETOR ~~~~~~~~~~~~~~~~~~~~~~~~



				double processamento = 0;

				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					vetor[auxiliar]=gerador.nextInt((1000000)+1);
					System.out.println("Valor: "+ vetor[auxiliar]);
				}
				System.out.println("Lista preenchida Pressione Enter!");
				System.in.read();

				Conexao.EnviandoDados("vetor");

				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					valor = String.valueOf(vetor[auxiliar]);
					Conexao.EnviandoDados(valor);
					processamento = processamento + 0.0004;
					DecimalFormat formato = new DecimalFormat("#.00");
					System.out.println("\nEnviados: " + formato.format(processamento) + "%");
				}

				auxiliar = 0;

				do {
					Main.LimparTela();
					System.out.println("Todos os dados foram enviados ao Servidor!\n Escolha uma das op��es para ser executada:");
					System.out.println("\n========================================= \n|\t\t\t\t\t|");
					System.out.println("| ( 1 ) - Ordenar via Insertion Sort    | \n|\t\t\t\t\t|");
					System.out.println("| ( 2 ) - Ordenar via Quick Sort        | \n|\t\t\t\t\t|");
					System.out.println("=========================================\n");
					System.out.println(" Escolha uma das op��es: ");
					auxiliar = entrada.nextInt();
					entrada.nextLine();
					System.out.print("\n");

				}while(auxiliar != 1 && auxiliar != 2);


				if(auxiliar == 1) {
					Conexao.EnviandoDados("InsertionSort");
				}
				if(auxiliar == 2) {
					Conexao.EnviandoDados("QuickSort");
				}

				System.out.println("Aguarde a ordena��o, pode levar alguns minutos!\n");

				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					vetor[auxiliar]=Integer.parseInt(Conexao.RecebendoDados());
				}

				break;

			case 2: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO LISTA ENCADEADA ~~~~~~~~~~~~~~~~~~~~~~~~

				aux = Encadeada.listarElementos(Encadeada.inserirElementoFim());
				System.out.println("Lista Encadeada preenchida Pressione Enter!");
				System.in.read();
				
				Conexao.EnviandoDados("encadeada");
				
				processamento = 0;
				
				
				while(aux!=null){
					System.out.println("Valor : " + aux.valor);
					valor = String.valueOf(aux.valor);
					Conexao.EnviandoDados(valor);
					processamento = processamento + 0.0004;
					DecimalFormat formato = new DecimalFormat("#.00");
					System.out.println("\nEnviados: " + formato.format(processamento) + "%");
					aux = aux.prox;
				}
				
				/*auxiliar = 0;

				do {
					Main.LimparTela();
					System.out.println("Todos os dados foram enviados ao Servidor!\n Escolha uma das op��es para ser executada:");
					System.out.println("\n========================================= \n|\t\t\t\t\t|");
					System.out.println("| ( 1 ) - Ordenar via Insertion Sort    | \n|\t\t\t\t\t|");
					System.out.println("| ( 2 ) - Ordenar via Quick Sort        | \n|\t\t\t\t\t|");
					System.out.println("=========================================\n");
					System.out.println(" Escolha uma das op��es: ");
					auxiliar = entrada.nextInt();
					entrada.nextLine();
					System.out.print("\n");

				}while(auxiliar != 1 && auxiliar != 2 && auxiliar != 0);


				if(auxiliar == 1) {
					Conexao.EnviandoDados("InsertionSort");
				}
				if(auxiliar == 2) {
					Conexao.EnviandoDados("QuickSort");
				}*/

				System.out.println("Aguarde a ordena��o, pode levar alguns minutos!\n");
				
				aux = Encadeada.RecebeEncadeada();
				
				System.out.println("Dados recebidos com Sucesso!");
				System.in.read();
				
				

				break;

			case 3: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO ARRAYLIST ~~~~~~~~~~~~~~~~~~~~~~~~

				for(auxiliar = 0; auxiliar<=250000; auxiliar++) {
					Main.arrayList.add(auxiliar,gerador.nextInt(1000000));
					System.out.println("Valor : " + Main.arrayList.get(auxiliar));	
				}
				System.out.println("ArrayList preenchida Pressione Enter!");
				System.in.read();

				Conexao.EnviandoDados("array");

				processamento = 0;

				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					valor = String.valueOf(arrayList.get(auxiliar));
					Conexao.EnviandoDados(valor);
					processamento = processamento + 0.0004;
					DecimalFormat formato = new DecimalFormat("#.00");
					System.out.println("\nEnviados: " + formato.format(processamento) + "%");
				}

				auxiliar = 0;

				do {
					Main.LimparTela();
					System.out.println("Todos os dados foram enviados ao Servidor!\n Escolha uma das op��es para ser executada:");
					System.out.println("\n========================================= \n|\t\t\t\t\t|");
					System.out.println("| ( 1 ) - Ordenar via Insertion Sort    | \n|\t\t\t\t\t|");
					System.out.println("| ( 2 ) - Ordenar via Quick Sort        | \n|\t\t\t\t\t|");
					System.out.println("=========================================\n");
					System.out.println(" Escolha uma das op��es: ");
					auxiliar = entrada.nextInt();
					entrada.nextLine();
					System.out.print("\n");

				}while(auxiliar != 1 && auxiliar != 2);


				if(auxiliar == 1) {
					Conexao.EnviandoDados("InsertionSort");
				}
				if(auxiliar == 2) {
					Conexao.EnviandoDados("QuickSort");
				}

				System.out.println("Aguarde a ordena��o, pode levar alguns minutos!\n");

				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					arrayList.add(auxiliar, Integer.parseInt(Conexao.RecebendoDados()));
				}

				break;

			case 4:
				auxiliar = 0;

				do {
					Main.LimparTela();
					System.out.println("\n - Escolha uma das op��es para ser executada:");
					System.out.println("\n========================================= \n|\t\t\t\t\t|");
					System.out.println("| ( 1 ) - Listar VETOR ordenado        | \n|\t\t\t\t\t|");
					System.out.println("| ( 2 ) - Listar ENCADEADA ordenada    | \n|\t\t\t\t\t|");
					System.out.println("| ( 3 ) - Listar ARRAY ordenado        | \n|\t\t\t\t\t|");
					System.out.println("=========================================\n");
					System.out.println(" Escolha uma das op��es: ");
					auxiliar = entrada.nextInt();
					entrada.nextLine();
					System.out.print("\n");

				}while(auxiliar != 1 && auxiliar != 2 && auxiliar != 3);
				
				if(auxiliar == 1) {
					for(auxiliar = 0; auxiliar<250000; auxiliar++) {
						System.out.println(auxiliar + ") Valor: " + vetor[auxiliar]);
					}
				}
				if(auxiliar == 2) {
					int contador = 0;
					while(aux!=null){
						System.out.println(contador ++ +") Valor: " + aux.valor);
						aux = aux.prox;
					}
				}
				if(auxiliar == 3) {
					if(arrayList.size()!=0) {
						for(auxiliar = 0; auxiliar<250000; auxiliar++) {
							System.out.println(auxiliar + ") Valor: " + arrayList.get(auxiliar));
						}
					}else {
						System.out.println("A lista n�o foi ordenada!");
						System.in.read();
						break;
					}
				}
				
				System.out.println("\nDados Listados com sucesso!");
				System.in.read();
				break;

			case 0:

				break;
			default:
				System.out.println("Op��o Inv�lida!");
				System.out.println("Pressione Enter.!");
				System.in.read();
			}	
		} while (opcao != 0);
		entrada.close();


	}

	// ------------------------------------ M�TODO LIMPAR TELA ------------------------------------------------

	public static void LimparTela() {
		for(int i = 0; i < 100; i++)
			System.out.println("");
	}

}
