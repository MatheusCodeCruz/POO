package br.com.residencia.poo.pessoas;

public class Presidente extends Funcionario {

	String cargo = "Presidente";
	private static double salario = 40000.0;
	private static double bonificacao = 1.5;
	private static double salarioP = 0.0;

	public Presidente(String nome, String cPF, String senha, String tipoUsuario, String tipoConta) {
		super(nome, cPF, senha, tipoUsuario, tipoConta);
	}

	public static double salarioPresidente() {
		salarioP = salario * bonificacao;
		return salarioP;
	}

	public double getSalario() {
		return salario;
	}

	public double getBonificacao() {
		return bonificacao;
	}

	@Override
	public String toString() {
		return "Presidente: " + nome + "\nUsuário: " + CPF + "\nSenha: " + senha + "\nSalário: R$" + salario
				+ "\nSalário com Bonificação: R$" + (salario * bonificacao);
	}

}
