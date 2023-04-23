package br.com.residencia.poo.primeiralista;

import java.util.Scanner;

public class Minicalc {

	public static void main(String[] args) {
		double soma;
		double div;
		double multi;
		double sub;
		double num1;
		double num2;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Digite o primeiro número: ");
		num1 = sc.nextDouble();
		System.out.println("Digite o segundo número: ");
		num2 = sc.nextDouble();
		sc.close();
		
		soma  = num1+num2;
		sub   = num1-num2;
		multi = num1*num2;
		div   = num1/num2;
		
		System.out.println("\nSoma = "+soma+
						   "\nDivisão = "+div+
						   "\nSubtração = "+sub+	
						   "\nMultiplicação = "+multi);
		
		

	}

}
