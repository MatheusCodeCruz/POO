package br.com.residencia.poo.contas;

public class ContaCorrente extends Conta {

	public ContaCorrente(String nome, String cPF, String tipoConta, String tipoUsuario, int numeroConta, int agencia,
			double saldo, double tarifacao) {
		super(tipoConta, cPF, nome, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
	}
	
	
}