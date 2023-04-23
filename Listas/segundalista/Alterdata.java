package br.com.poo.residencia.segundalista;

/*Escrever o programa “Sobre a Empresa Alterdata”. Sua saída será a
mensagem: “A Alterdata tem x anos.”, onde o x é a idade da empresa. A
idade será atribuída em uma variável inteira.*/

public class Alterdata {
	int id;
	
	public Alterdata (int id) {
		this.id = id;
	
	}
		
	public static void main(String[] args) {
		Alterdata alterdata = new Alterdata(34);
		System.out.println("A empresa Alterdata tem " + alterdata.id + " anos!");

		
	}
	
}
