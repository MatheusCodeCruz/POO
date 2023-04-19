package br.com.residencia.poo.contas;

import Conta.Conta;

public class ContaPoupanca extends Conta{

	public ContaPoupanca(String cPF, String nome, int numConta, String agConta, double saldo,
			double tarifacao, String localConta) {
		super("Poupança", cPF, nome, numConta, agConta, saldo, tarifacao, localConta);
	}

}
