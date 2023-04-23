package br.com.residencia.poo.contas;

import java.io.IOException;
import java.io.PrintWriter;

public class Relatorios extends Conta {

	Relatorios(String tipoConta, String CPFDoTitular, String nome, String tipoPessoa, int numeroDaConta, int agencia,
			double saldo, double tarifacao) {
		super(tipoConta, CPFDoTitular, nome, tipoPessoa, numeroDaConta, agencia, saldo, tarifacao);
	}

	public static PrintWriter relDeposito(String nome, double valor, double valor2) throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		String.format("%.2f", valor2);
		escritaPath.println("Usuário " + nome + " efetuou um depósito de R$ " + valor + " - " + data);
		escritaPath.close();
//			String str = "ABC";
//	        try {
//	  
//	            // attach a file to FileWriter
//	            FileWriter fw = new FileWriter("C:\\ws-eclipse\\trabalhofinal\\deposito.txt");
//	            for (int i = 0; i < str.length(); i++)
//	                fw.write(str.charAt(i));
//					System.out.println("Successfully written");
//	            fw.close();
//	        }
//	        catch (Exception e) {
//	            e.getStackTrace();
//	        }
		return escritaPath;
	}

	public static PrintWriter relSaque(String nome, double valor, double valor2) throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		String.format("%.2f", valor2);
		escritaPath.println("Usuário " + nome + " efetuou um saque de R$ " + valor + "\nSaldo restante: R$ " + valor2
				+ " - " + data);
		escritaPath.close();
		return escritaPath;
	}

	public static PrintWriter relTransferencia(String nome, double valor, int destino) throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		escritaPath.println("Usuário " + nome + " efetuou uma transferência de R$ " + valor + " para a conta " + destino
				+ " - " + data);
		escritaPath.close();
		return escritaPath;
	}

	public static PrintWriter relSeguroDeVida(String nome, double valor) throws IOException {
		Arquivos.path(1);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(1));
		escritaPath.println("Usuário " + nome + " assegurou o valor de R$ " + valor + " - " + data);
		escritaPath.close();
		return escritaPath;
	}

	public static PrintWriter nfSaque(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(2);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(2));
		escritaPath.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n--------------------------SAQUE--------------------------" + "\nUsuário: " + nome + ", CPF: "
				+ cPFDoTitular + "- " + data + "\nValor do saque: " + valor);
		escritaPath.close();
		return escritaPath;
	}

	public static PrintWriter nfDeposito(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(3);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(3));
		escritaPath.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------DEPÓSITO-----------------------" + "\nUsuário: " + nome + ", CPF: "
				+ cPFDoTitular + "- " + data + "\nValor do depósito: " + valor);
		escritaPath.close();
		return escritaPath;
	}

	public static PrintWriter nfTransferencia(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(4);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(4));
		escritaPath.printf("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-------------------------TRANSFERÊNCIA-------------------------" + "\nUsuário: " + nome + ", CPF: "
				+ cPFDoTitular + "- " + data + "\nValor da Transferência: " + valor);
		escritaPath.close();
		return escritaPath;
	}

	public static PrintWriter nfSeguro(String nome, String cPFDoTitular, double valor) throws IOException {
		Arquivos.path(5);
		String data = Data.Data();
		PrintWriter escritaPath = new PrintWriter(Arquivos.path(5));
		escritaPath.print("\n-----------------------NOTA FISCAL-----------------------"
				+ "\n-----------------------Seguro de Vida-----------------------" + "\n" + data + " - Usuário: " + nome
				+ ", CPF: " + cPFDoTitular
				+ "\nfechou contrato do serviço Seguro de vida, pagando uma taxa tributação de R$ " + valor
				+ " (20% do valor solicitado a assegurar)");
		escritaPath.close();
		return escritaPath;
	}

//	public static PrintWriter relIndividualCliente(String nome, String cPFDoTitular, double valor) throws IOException {
//		Arquivos.path(2);
//		String data = Data.Data();
//		PrintWriter escritaPath = new PrintWriter(Arquivos.path(2));
//		escritaPath.print("\n-----------------------RELATORIO DE CLIENTE-----------------------"
//				+ "\n--------------------------Seguro de Vida--------------------------" + "\n" + data + " - Usuário: "
//				+ nome + ", CPF: " + cPFDoTitular
//				+ "\nfechou contrato do serviço Seguro de vida, pagando uma taxa tributação de R$ " + valor
//				+ " (20% do valor solicitade a assegurar)");
//		escritaPath.close();
//		return escritaPath;
//	}

}