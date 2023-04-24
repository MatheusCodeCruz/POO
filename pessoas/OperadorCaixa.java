package br.com.residencia.poo.pessoas;

public class OperadorCaixa extends Funcionario {

	
	String cargo = "Operador";
	static int agencia;
	private static double salario = 3500.0;
	private static double bonificacao = 1.2;
	private static double salarioOp = 0.0;

	public OperadorCaixa(String nome, String cPF, String senha, String tipoUsuario, String tipoConta, int agencia) {
		super(nome, cPF, senha, tipoUsuario, tipoConta);
		OperadorCaixa.agencia = agencia;
	}
	
	public static double salarioOperadorCaixa() {
		salarioOp = salario * bonificacao;
		return salarioOp;
	}
	
//	public static void somaAgencia () {
//		if() != null) {
//			
//		}
//	}

	public String getCargo() {
		return cargo;
	}

	public static int getAgencia() {
		return agencia;
	}

	public static double getSalario() {
		return salario;
	}

	public static double getBonificacao() {
		return bonificacao;
	}

	public static double getSalarioD() {
		return salarioOp;
	}
	
}
