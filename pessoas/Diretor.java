package br.com.residencia.poo.pessoas;

public class Diretor extends Funcionario {

	String cargo = "Diretor";
	int agencia;
	private static double salario = 15000.0;
	private static double bonificacao = 1.4;
	private static double salarioD = 0.0;

	public Diretor(String nome, String cPF, String senha, String tipoUsuario, String tipoConta, int agencia) {
		super(nome, cPF, senha, tipoUsuario, tipoConta);
		this.agencia = agencia;
	}

	public static double salarioDiretor() {
		salarioD = salario * bonificacao;
		return salarioD;
	}

	public double getSalario() {
		return salario;
	}

	public double getBonificacao() {
		return bonificacao;
	}

	@Override
	public String toString() {
		return "Diretor [" + (cargo != null ? "cargo=" + cargo : "") + "]";
	}

}
