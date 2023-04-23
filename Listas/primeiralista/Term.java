package br.com.residencia.poo.primeiralista;

import java.util.Scanner;

public class Term {

	public static void main(String[] args) {
		double tempc;
		double tempf;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n===Conversor de Temperatura===\n");
		System.out.println("Escreva a temperatura em ºC: ");
		tempc = sc.nextDouble();
		sc.close();
		
		tempf = tempc*1.8+32;
		
		System.out.println("A temperatura em ºF: " +tempf);


	}

}
