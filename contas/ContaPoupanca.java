package br.com.residencia.poo.contas;

public class ContaPoupanca extends Conta{

	public ContaPoupanca(String nome, String cPF, String tipoConta, String tipoUsuario, int numeroConta, int agencia,
			double saldo, double tarifacao) {
		super(tipoConta, cPF, nome, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
	}

}
