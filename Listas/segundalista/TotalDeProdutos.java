package br.com.poo.residencia.segundalista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TotalDeProdutos {

	public static void main(String[] args) {
		int x = 0;
		Scanner sc = new Scanner(System.in);

		while (true) {
			try {
				System.out.println("Quantos produtos o senhor(a) pretende comprar? ");
				x = sc.nextInt();

				if (x == 0) {
					System.out.println("Hey, vamos compra! \n");
					System.out.println("Temos várias promoções! \n");

				} else if (x < 0) {
					System.out.println("Valor negativo não é válido! \n");

				} else {
					System.out.println("Parabéns por comprar " + x + " produtos.");
					break;
				}

			} catch (InputMismatchException e) {
				System.out.println("Favor inserir apenas números!");
				sc.nextLine();

			}

		}
	}
}
