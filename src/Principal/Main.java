package Principal;

import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException {
		
		int opcao = 0;
		int cont = 0;
		
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
			
				case 1:
					break;
			
				case 2:
					
					break;
											
				case 3:
				
					break;

				case 4:
				
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
