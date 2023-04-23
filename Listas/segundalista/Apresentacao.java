package br.com.poo.residencia.segundalista;

import java.util.Scanner;

/*O programa "Educado" irá perguntar seu nome, e também como você
gostaria de ser chamado. Depois disso, uma saudação para você aparece
na tela.*/

public class Apresentacao {
	
	public static void main(String[] args) {
	    String nome;
		String nick;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Qual o seu nome ?\n ");
		nome = sc.nextLine();
		System.out.println("\nComo gostaria de ser chamado " +nome+ " ?" );
		nick = sc.nextLine();
		sc.close();
		System.out.println("Qual a boa " +nick+ " ?");

	}

}
