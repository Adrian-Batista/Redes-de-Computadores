package Entidades;

import java.util.Random;

public class Encadeada {

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

	Elemento inicio;
	Elemento fim;
	int tamanho;

	public Encadeada(){
		inicio = null;
		fim = null;
		tamanho = 0;
	}

	public int getTamanho(){
		return tamanho;
	}

	public static void listarElementos(Elemento inicio){
		Elemento aux = inicio;
		while(aux!=null){
			System.out.println("Valor : " + aux.valor);
			aux = aux.prox;
		}
	}

	public static Elemento inserirElementoFim(){
		Elemento inicio = null;
		Elemento fim = null;
		int tamanho =0;
		int valor = 0;
		Random gerador = new Random();
		for(int auxiliar = 0; auxiliar<250000; auxiliar++) {
			valor=gerador.nextInt((1000000)+1);
			Elemento novo = new Elemento(valor);
			if (tamanho == 0){
				inicio = novo;
				fim = novo;
			}else{
				fim.prox = novo;
				fim = novo;
			}
			tamanho++;
		}
		return inicio;
		
	}

	public Elemento getInicio() {
		return inicio;
	}

	public void setInicio(Elemento inicio) {
		this.inicio = inicio;
	}

	public Elemento getFim() {
		return fim;
	}

	public void setFim(Elemento fim) {
		this.fim = fim;
	}

	public void setTamanho(int tamanho) {
		this.tamanho = tamanho;
	}
	
}
