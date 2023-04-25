package br.com.residencia.poo.contas;

import java.io.IOException;
import java.io.PrintWriter;

public class Relatorios extends Conta {

	Relatorios(String tipoConta, String CPFDoTitular, String nome, String tipoPessoa, int numeroDaConta, int agencia,
			double saldo, double tarifacao) {
		super(tipoConta, CPFDoTitular, nome, tipoPessoa, numeroDaConta, agencia, saldo, tarifacao);
	}

	// Escrita que vai para o .txt de Transações
	public static PrintWriter relDeposito(String tipoCliente, String nome, double valor, double valor2)
			throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		String.format("%.2f", valor2);
		escritaPath.println(tipoCliente + " " + nome + " efetuou um depósito de R$ " + Arquivos.formatInt(valor)
				+ " \nSaldo atual: R$" + Arquivos.formatInt(valor2) + " - " + data);
		escritaPath
				.println("-------------------------------------------------------------------------------------------");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de Transações
	public static PrintWriter relSaque(String tipoCliente, String nome, double valor, double valor2)
			throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		String.format("%.2f", valor2);
		escritaPath.println(tipoCliente + " " + nome + " efetuou um saque de R$ " + Arquivos.formatInt(valor)
				+ "\nSaldo atual: R$ " + Arquivos.formatInt(valor2) + " - " + data);
		escritaPath
				.println("-------------------------------------------------------------------------------------------");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de Transações
	public static PrintWriter relTransferencia(String tipoCliente, String nome, double valor, double valor2,
			String nomeDestino, int destino, int agencia) throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		escritaPath.println(tipoCliente + " " + nome + " efetuou uma transferência de R$ " + Arquivos.formatInt(valor)
				+ " para  " + nomeDestino + " " + destino + "-" + agencia + "\nSaldo atual: R$ "
				+ Arquivos.formatInt(valor2) + " - " + data);
		escritaPath
				.println("-------------------------------------------------------------------------------------------");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de Transações
	public static PrintWriter relSeguroDeVida(String tipoCliente, String nome, double valor, double valor2)
			throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		escritaPath.println(tipoCliente + " " + nome + " assegurou o valor de R$ " + Arquivos.formatInt(valor) + " - "
				+ data + "\nValor da Tributação: R$ " + Arquivos.formatInt(valor2));
		escritaPath
				.println("-------------------------------------------------------------------------------------------");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de nfSaque
	public static PrintWriter nfSaque(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(2);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(2));
		escritaPath.printf("|-------------------------PYRAMID-------------------------|"
				+ "\n|--------------------------SAQUE--------------------------|" + "\n|Usuário: " + nome + ", CPF: "
				+ cPFDoTitular + " - " + data + " |\n Valor do saque: R$ " + Arquivos.formatInt(valor));
		escritaPath.println("\n|-------------------------PYRAMID-------------------------|");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de nfDeposito
	public static PrintWriter nfDeposito(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(3);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(3));
		escritaPath.printf("|-------------------------PYRAMID-------------------------|"
				+ "\n|------------------------DEPÓSITO-------------------------|" + "\n|Usuário: " + nome + ", CPF: "
				+ cPFDoTitular + " - " + data + " |\n Valor do saque: R$ " + Arquivos.formatInt(valor));
		escritaPath.println("\n|-------------------------PYRAMID-------------------------|");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de nfTransferencia
	public static PrintWriter nfTransferencia(String nome, String cPFDoTitular, double valor, String destino,
			int contaDestino, int agenciaDestino) throws IOException {
		Arquivos.path(4);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(4));
		escritaPath.printf("|-------------------------PYRAMID-------------------------|"
				+ "\n|----------------------TRANSFERÊNCIA----------------------------|" + "\n|Usuário: " + nome + ", CPF: "
				+ cPFDoTitular + " - " + data + " |\n Valor da Transferência: R$ " + Arquivos.formatInt(valor)
				+ " para " + destino + " " + contaDestino + "-" + agenciaDestino);
		escritaPath.println("\n|-------------------------PYRAMID-------------------------|");
		escritaPath.close();
		return escritaPath;
	}

	// Escrita que vai para o .txt de nfSeguro
	public static PrintWriter nfSeguro(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(5);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(5));
		escritaPath.print("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------SEGURO DE VIDA-----------------------" + "\n" + data + " - Usuário: " + nome
				+ ", CPF: " + cPFDoTitular
				+ "\nfechou contrato do serviço Seguro de vida, pagando uma taxa tributação de R$ " + valor
				+ " (20% do valor solicitado a assegurar)");
		escritaPath.close();
		return escritaPath;
	}

}