package Principal;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Entidades.Conexao;
import Entidades.Encadeada;

public class Main {

	public static List<Integer> arrayList = new ArrayList<Integer>();

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
			System.out.println(" Escolha uma das opções: ");
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
					System.out.println("Todos os dados foram enviados ao Servidor!\n Escolha uma das opções para ser executada:");
					System.out.println("\n========================================= \n|\t\t\t\t\t|");
					System.out.println("| ( 1 ) - Ordenar via Insertion Sort    | \n|\t\t\t\t\t|");
					System.out.println("| ( 2 ) - Ordenar via Quick Sort        | \n|\t\t\t\t\t|");
					System.out.println("| ( 3 ) - Não ordenar                   | \n|\t\t\t\t\t| ");
					System.out.println("=========================================\n");
					System.out.println(" Escolha uma das opções: ");
					auxiliar = entrada.nextInt();
					entrada.nextLine();
					System.out.print("\n");
					if(auxiliar == 3) {
						break;
					}
				}while(auxiliar != 1 || auxiliar != 2);


				if(auxiliar == 1) {
					Conexao.EnviandoDados("InsertionSort");
				}
				if(auxiliar == 2) {
					Conexao.EnviandoDados("QuickSort");
				}

				System.out.println("Aguarde a ordenação, pode levar alguns minutos!\n");

				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					vetor[auxiliar]=Integer.parseInt(Conexao.RecebendoDados());
				}

				break;

			case 2: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO LISTA ENCADEADA ~~~~~~~~~~~~~~~~~~~~~~~~

				Encadeada.listarElementos(Encadeada.inserirElementoFim());
				System.out.println("Lista Encadeada preenchida Pressione Enter!");
				System.in.read();

				Encadeada.EnviaEncadeada();
				System.out.println("Dados enviados com sucesso!");
				System.in.read();





				break;

			case 3: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO ARRAYLIST ~~~~~~~~~~~~~~~~~~~~~~~~

				for(auxiliar = 0; auxiliar<=250000; auxiliar++) {
					Main.arrayList.add(auxiliar,gerador.nextInt(1000000));
					System.out.println("Valor : " + Main.arrayList.get(auxiliar));	
				}
				System.out.println("ArrayList preenchida Pressione Enter!");
				System.in.read();

				break;

			case 4:
				for(auxiliar = 0; auxiliar<250000; auxiliar++) {
					System.out.println(auxiliar + ") Valor: " + vetor[auxiliar]);
				}
				System.out.println("\nDados Listados com sucesso!");
				System.in.read();
				break;

			case 5:

				int aux = 10;
				valor = String.valueOf(aux);
				Conexao.EnviandoDados(valor);
				break;

			case 0:

				break;
			default:
				System.out.println("Opção Inválida!");
				System.out.println("Pressione Enter.!");
				System.in.read();
			}	
		} while (opcao != 0);
		entrada.close();


	}

	// ------------------------------------ MÉTODO LIMPAR TELA ------------------------------------------------

	public static void LimparTela() {
		for(int i = 0; i < 100; i++)
			System.out.println("");
	}

}
