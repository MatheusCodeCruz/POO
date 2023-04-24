package br.com.residencia.poo.pessoas;

public class Gerente extends Funcionario {
	String cargo = "Gerente";
	int agencia;
	private static double salario = 7000.0;
	private static double bonificacao = 1.3;
	private static double salarioG = 0.0;

	public Gerente(String nome, String cPF, String senha, String tipoUsuario, String tipoConta, int agencia) {
		super(nome, cPF, senha, tipoUsuario, tipoConta);
		this.agencia = agencia;
	}

	public static double salarioGerente() {
		salarioG = salario * bonificacao;
		return salarioG;
	}

	public double getSalario() {
		return salario;
	}

	public double getBonificacao() {
		return bonificacao;
	}

	@Override
	public String toString() {
		return "[Cargo = " + cargo + ", CPF = " + CPF + ", nome = " + nome + ", agencia=" + agencia + "]";
	}

}
