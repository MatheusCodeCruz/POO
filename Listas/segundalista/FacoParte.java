package br.com.poo.residencia.segundalista;

/*O sistema “Faço parte” vai te mostrar um histórico da trajetória de nossa
cidade. O resultado apresentado na tela será: “Teresópolis tem X anos.
Desses, Y foram antes de mim. Mas os últimos Z anos contaram comigo!”.
Cada letra maiúscula do texto será uma variável, sendo que “x” e “z” são
valores fixos, e “y” irá conter uma expressão.*/

public class FacoParte {

	public static void main(String[] args) {
		int x = 131;
		int z = 28;
		int y = x-z;
		
		System.out.println("Teresópolis tem " + x + " anos.");
		System.out.println("Desses, " + y + " foram antes de mim.");
		System.out.println("Mas os últimos " + z + " anos contaram comigo!");
	}

}
