package br.com.residencia.poo.primeiralista;

import java.util.Scanner;

public class MiniCalcB {

	public static void main(String[] args) {

		double num1 = 0.0;
		double num2 = 0.0;
		int op;
		Scanner sc = new Scanner(System.in);

		System.out.println("Escolha uma operação: \n");
		System.out.println(" 1-Soma\n 2-Sub\n 3-Div\n 4-Multi\n");
		op = sc.nextInt();

		switch (op) {
		case 1:
			System.out.println("Voce escolheu soma! \n");
			System.out.println("Insira o primeiro número: \n");
			num1 = sc.nextDouble();
			System.out.println("Insira o segundo número!");
			num2 = sc.nextDouble();
			double soma = num1 + num2;
			
			if ((soma * 10) % 10 == 0) {
				int intresult1 = (int) soma;
				System.out.println("O resultado é: " + intresult1);
			} else {
				System.out.println("O resultado é: \n" + soma);
			}
			
			break;

		case 2:
			System.out.println("Voce escolheu Subtração! \n");
			System.out.println("Insira o primeiro número: \n");
			num1 = sc.nextDouble();
			System.out.println("Insira o segundo número!");
			num2 = sc.nextDouble();
			double sub = num1 - num2;

			if ((sub * 10) % 10 == 0) {
				int intresult2 = (int) sub;
				System.out.println("O resultado é: " + intresult2);
			} else {
				System.out.println("O resultado é: \n" + sub);
			}

			break;

		case 3:
			System.out.println("Voce escolheu Divisão! \n");
			System.out.println("Insira o primeiro número: \n");
			num1 = sc.nextDouble();
			System.out.println("Insira o segundo número!");
			num2 = sc.nextDouble();

			if (num2 == 0) {
				System.out.println("Não é possivel dividir por zero! \n");
			} else {
				double div = num1 / num2;
				if (div % 1 == 0) {
					int intresult3 = (int) div;
					System.out.println("O resultado é: " + intresult3);
				} else {
					System.out.println("O resultado é: \n" + div);
				}
			}

			break;

		case 4:
			System.out.println("Voce escolheu Multiplicação\n");
			System.out.println("Insira o primeiro número: \n");
			num1 = sc.nextDouble();
			System.out.println("Insira o segundo número!");
			num2 = sc.nextDouble();
			double multi = num1 * num2;
			
			if((multi*10)%10 == 0) {
				int intresult4 = (int)multi;
				System.out.println("O resultado é: \n"+intresult4);
			}else {
				System.out.println("O resultado é: " + multi);
				
			}
			
			break;

		default:
			System.out.println("Operação Inválida!");
			break;

		}

		sc.close();

	}

}
