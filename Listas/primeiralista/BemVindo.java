package br.com.residencia.poo.primeiralista;

import java.util.Scanner;

public class BemVindo {

	public static void main(String[] args) {
		String nome;
		String sobrenome;
		Scanner sc = new Scanner (System.in);
		
		System.out.println("Qual o seu nome?");
		nome = sc.next();
		System.out.println("Qual seu sobrenome?");
		sobrenome = sc.next();
		sc.close();
		
		System.out.println("Olá, " +nome+ " "+sobrenome+ " seja bem vindo(a)a programação!!");
		
		
	}

}
