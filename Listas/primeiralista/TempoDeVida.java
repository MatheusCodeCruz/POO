package br.com.residencia.poo.primeiralista;

import java.util.Scanner;

public class TempoDeVida {

	public static void main(String[] args) {
		
		int idade;
		int soma = 0;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Qual a idade do 1º colega? ");
		idade = sc.nextInt();
		soma+=idade;
		System.out.println("Qual a idade do 2º colega?");
		idade = sc.nextInt();
		soma+=idade;
		System.out.println("Qual a idade do 3º colega?");
		idade = sc.nextInt();
		soma+=idade;
		System.out.println("Qual a idade do 4º colega?");
		idade = sc.nextInt();
		soma+=idade;
		System.out.println("Qual a idade do 5º colega?");
		idade = sc.nextInt();
		soma+=idade;
		System.out.println("Qual a minha idade? ");
		idade = sc.nextInt();
		sc.close();
		
		soma+=idade;
		
		System.out.println("O tempo de vida meu e dos meus colegas é " +soma);
		
		
	}

}
