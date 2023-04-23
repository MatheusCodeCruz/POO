package br.com.poo.residencia.segundalista;

/*Criar o programa “Qual o significado da vida, do universo e tudo mais?”. Ao
clicar, deve aparecer no console o número do universo.
O programa terá uma variável Inteira, com identificador “Universo”, onde
você irá atribuir o número em questão. Após a atribuição, escreva na tela o
conteúdo da variável.*/

public class Sig_Vida {

	int universo;
	
	public Sig_Vida(int universo) {
		this.universo = universo;
	}

	public static void main(String[] args) {

		Sig_Vida sigVida = new Sig_Vida(42);
		System.out.println("O número do universo é: " +sigVida.universo);

	}

}

