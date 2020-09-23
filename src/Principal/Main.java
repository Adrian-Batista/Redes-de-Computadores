package Principal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import Entidades.Conexao;
import Entidades.Encadeada;
import Entidades.Servidor;

public class Main {
	
	public static List<Integer> arrayList = new ArrayList<Integer>();

	public static void main(String[] args) throws IOException {
		
		int opcao = 0;
		int cont = 0;
		int auxiliar = 0;
		
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
			System.out.println("| ( 4 ) - LISTAR ENVIO                  | \n|\t\t\t\t\t| ");
			System.out.println("| ( 0 ) - SAIR                          | \n|\t\t\t\t\t|");
			System.out.println("=========================================\n");
			System.out.println(" Escolha uma das opções: ");
			opcao = entrada.nextInt();
			entrada.nextLine();
			System.out.print("\n");
			
			switch (opcao) {
			
				case 1: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO VETOR ~~~~~~~~~~~~~~~~~~~~~~~~
					
					
					int[] vetor = new int[250000];
					
					for(auxiliar = 0; auxiliar<250000; auxiliar++) {
						vetor[auxiliar]=gerador.nextInt((1000000)+1);
						System.out.println("Valor: "+ vetor[auxiliar]);
					}
					System.out.println("Lista preenchida Pressione Enter!");
					System.in.read();
					
					break;
			
				case 2: // ~~~~~~~~~~~~~~~~~~~~~~ CARREGANDO LISTA ENCADEADA ~~~~~~~~~~~~~~~~~~~~~~~~
					
					Encadeada.listarElementos(Encadeada.inserirElementoFim());
					System.out.println("Lista Encadeada preenchida Pressione Enter!");
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
				
					break;
					
				case 5:
					Conexao.EnviandoDados();
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
