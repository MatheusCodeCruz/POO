package br.com.residencia.poo.contas;

import java.util.Scanner;
import contas.ContaCorrente;

public abstract class Conta {
	String tipoConta;
	String CPF;
	String nome;
	int numConta;
	String agConta;
	double saldo;
	double valor;
	double tarifacao;
	String localConta;
	private static int totalContas;

	public Conta() {
	}

	protected Conta(String tipoConta, String cPF, String nome, int numConta, String agConta, double saldo,
			double tarifacao, String localConta) {

		this.tipoConta = tipoConta;
		this.CPF = cPF;
		this.nome = nome;
		this.numConta = numConta;
		this.agConta = agConta;
		this.saldo = saldo;
		this.tarifacao = tarifacao;
		this.localConta = localConta;
	}

	public static void movimento(){
	 	Scanner scan = new Scanner(System.in);
	 	System.out.println("Escolha a operação desejada:\n1- Saque\n2- Depósito\n3- Transferência\n4- Seguro de Vida\n5- Voltar ");
		int op = scan.nextInt();
		
	switch(op) {
		case 1:
			try{
				System.out.println("Digite o valor que você deseja sacar: R$");
				double valorSacar = new Scanner(System.in).nextDouble();
				if(sacar(valorSacar)){
						
				}
						
			}catch{
				System.out.println("Carácter inválido, tente novamente");
				break;
			}
		case 2:
 	}

	public boolean sacar(Double valor) {
		if (this.saldo < valor || valor <= 0) {
			System.out.println("Saldo insuficiente!");
			return false;
		} else {
			this.saldo -= valor;
			return true;
		}
	}

	public boolean depositar(Double valorDeposito) {
		Scanner scanner = new Scanner(System.in);
		while (valorDeposito <= 0) {
			System.out.println("Digite um valor positivo:");
			valorDeposito = scanner.nextDouble();
		}
		this.saldo += valorDeposito;
		return true;
	}

	public void transferir(Double valor, Conta destino) {
		sacar(valor);
		destino.depositar(valor);
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getNumConta() {
		return numConta;
	}

	public void setNumConta(int numConta) {
		this.numConta = numConta;
	}

	public String getAgConta() {
		return agConta;
	}

	public void setAgConta(String agConta) {
		this.agConta = agConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public double getTarifacao() {
		return tarifacao;
	}

	public void setTarifacao(double tarifacao) {
		this.tarifacao = tarifacao;
	}

	public String getLocalConta() {
		return localConta;
	}

	public void setLocalConta(String localConta) {
		this.localConta = localConta;
	}

//		public void debitoAutomatico() {
//			saldo -= luz + cartao;
//		}

}
