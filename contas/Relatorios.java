package br.com.residencia.poo.contas;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Relatorios extends Conta {

	Relatorios(String tipoConta, String CPFDoTitular, String nome, String tipoPessoa, int numeroDaConta, int agencia,
			double saldo, double tarifacao) {
		super(tipoConta, CPFDoTitular, nome, tipoPessoa, numeroDaConta, agencia, saldo, tarifacao);
	}

	// digitador.txt para ocasiões de Deposito
	public static PrintWriter relDeposito(String nome, double valor, double valor2) throws IOException {
		Utilidade.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(1));
		String.format("%.2f", valor2);
		gravarArq.println("Usuário " + nome + " efetuou um depósito de R$ " + valor + " - " + data);
		gravarArq.close();
//			String str = "ABC";
//	        try {
//	  
//	            // attach a file to FileWriter
//	            FileWriter fw
//	                = new FileWriter("C:\\serratec\\POO\\Workspace\\Projetofinal\\deposito.txt");
//	  
//	            // read each character from string and write
//	            // into FileWriter
//	            for (int i = 0; i < str.length(); i++)
//	                fw.write(str.charAt(i));
//					System.out.println("Successfully written");
//	            // close the file
//	            fw.close();
//	        }
//	        catch (Exception e) {
//	            e.getStackTrace();
//	        }
		return gravarArq;
	}

	// digitador.txt para ocasião de saque
	public static PrintWriter relSaque(String nome, double valor, double valor2) throws IOException {
		Utilidade.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(1));
		String.format("%.2f", valor2);
		gravarArq.println("Usuário " + nome + " efetuou um saque de R$ " + valor + "\nSaldo restante: R$ " + valor2
				+ " - " + data);
		gravarArq.close();
		return gravarArq;
	}

	// digitador.txt para ocasiões de transferencia
	public static PrintWriter relTransferencia(String nome, double valor, int destino) throws IOException {
		Utilidade.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(1));
		gravarArq.println("Usuário " + nome + " efetuou uma transferência de R$ " + valor + " para a conta " + destino
				+ " - " + data);
		gravarArq.close();
		return gravarArq;
	}

	// digitador.txt para seguro de vida
	public static PrintWriter relSeguroDeVida(String nome, double valor) throws IOException {
		Utilidade.path(1);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(1));
		gravarArq.println("Usuário " + nome + " assegurou o valor de R$ " + valor + " - " + data);
		gravarArq.close();
		return gravarArq;
	}

	// digitador.txt para ocasiões de Nota Fiscal Saque
	public static PrintWriter nfSaque(String nome, String cPFDoTitular, double valor) throws IOException {
		Utilidade.path(2);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(2));
		gravarArq.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n--------------------------SAQUE--------------------------" + "\nUsuário: " + nome + ", CPF: "
				+ cPFDoTitular + "- " + data + "\nValor do saque: " + valor);
		gravarArq.close();
		return gravarArq;
	}

	// digitador.txt para ocasiões de nota fiscal DEPOSITO
	public static PrintWriter nfDeposito(String nome, String cPFDoTitular, double valor) throws IOException {
		Utilidade.path(2);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(2));
		gravarArq.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------DEPÓSITO-----------------------" + "\nUsuário: " + nome + ", CPF: "
				+ cPFDoTitular + "- " + data + "\nValor do depósito: " + valor);
		gravarArq.close();
		return gravarArq;
	}

	// digitador.txt para ocasiões de nota fiscal SEGURO
	public static PrintWriter nfSeguro(String nome, String cPFDoTitular, double valor) throws IOException {
		Utilidade.path(2);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(2));
		gravarArq.print("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------Seguro de Vida-----------------------" + "\n" + data + " - Usuário: " + nome
				+ ", CPF: " + cPFDoTitular
				+ "\nfechou contrato do serviço Seguro de vida, pagando uma taxa tributação de R$ " + valor
				+ " (20% do valor solicitade a assegurar)");
		gravarArq.close();
		return gravarArq;
	}

	public static PrintWriter relIndividualCliente(String nome, String cPFDoTitular, double valor) throws IOException {
		Utilidade.path(2);
		String data = Data.Data();
		PrintWriter gravarArq = new PrintWriter(Utilidade.path(2));
		gravarArq.print("\n-----------------------RELATORIO DE CLIENTE-----------------------"
				+ "\n--------------------------Seguro de Vida--------------------------" + "\n" + data + " - Usuário: "
				+ nome + ", CPF: " + cPFDoTitular
				+ "\nfechou contrato do serviço Seguro de vida, pagando uma taxa tributação de R$ " + valor
				+ " (20% do valor solicitade a assegurar)");
		gravarArq.close();
		return gravarArq;
	}

}