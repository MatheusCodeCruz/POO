package br.com.poo.residencia.segundalista;

/*O sistema “Mais que mil” irá apresentar o resultado da soma dos 4
primeiros múltiplos de 4 acima de mil, subtraindo dos 4 primeiros números
primos a partir de um*/

public class MaisQMil {

	public static void main(String[] args) {
		int acimademil = 1004+1008+1012+1016;
		int primo = 2+3+5+7;
		int resultado = acimademil-primo;
		
		System.out.println("O sistema mais que mil vai retornar: "+resultado);

	}

}
