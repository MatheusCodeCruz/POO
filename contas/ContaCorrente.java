package br.com.residencia.poo.contas;

public class ContaCorrente extends Conta {

	double saldoCorrente;

	public double getSaldoCorrente() {
		return saldo;
	}

	public void setSaldoCorrente(double saldo) {
		this.saldo = saldo;
	}

	public ContaCorrente(String cPF, String nome, int numConta, String agConta, double saldo,
			double tarifacao, String localConta) {
		super("Corrente", cPF, nome, numConta, agConta, saldo, tarifacao, localConta);
	}

	public ContaCorrente() {
	}

	@Override
	public String toString() {
		return "ContaCorrente [getSaldo()=" + getSaldo() + "]";
	}

	
	
}