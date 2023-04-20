package br.com.residencia.poo.sistema;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import contas.Conta;
import contas.ContaCorrente;
import contas.ContaPoupanca;
import pessoas.Cliente;
import pessoas.Diretor;
import pessoas.Gerente;
import pessoas.Pessoa;
import pessoas.Presidente;

public class SistemaPrincipal {

	public static void main(String[] args) throws NumberFormatException, IOException {

		Map<String, Pessoa> mapUsers = new HashMap<>();
		Map<String, Conta> mapContas = new HashMap<>();
		Map<Integer, Conta> mapContasNumeroConta = new HashMap<>();
		List<Object> tContas = new ArrayList<>();

		String nome;
		String CPF;
		String senha;
		String tipoUsuario;
		String tipoConta;
		int numeroConta;
		int agencia;
		double saldo;
		double tarifacao;

		// Se atentar com o Path, se o caminho do arquivo está correto
		String path = "C:\\serratec\\POO\\Workspace\\Projetofinal\\pessoas.txt";
		BufferedReader buffRead = new BufferedReader(new FileReader(path));
		String linha = "";

		while ((linha = buffRead.readLine()) != null) {

			String[] dados = linha.split(",");
			nome = dados[0];
			CPF = dados[1];
			senha = dados[2];
			tipoUsuario = dados[3];
			tipoConta = dados[4];
			numeroConta = Integer.parseInt(dados[5]);
			agencia = Integer.parseInt(dados[6]);
			saldo = Double.parseDouble(dados[7]);
			tarifacao = Double.parseDouble(dados[8]);
			// Direcionamento de dados de acordo com o tipo de usuário
			Pessoa usuario = null;
			switch (tipoUsuario) {
			case "Cliente":
				usuario = new Cliente(nome, CPF, senha, tipoUsuario, tipoConta);
				mapUsers.put(CPF, usuario);
				break;
			case "Gerente":
				usuario = new Gerente(nome, CPF, senha, tipoUsuario, tipoConta, agencia);
				mapUsers.put(CPF, usuario);
				break;
			case "Diretor":
				usuario = new Diretor(nome, CPF, senha, tipoUsuario, tipoConta, agencia);
				mapUsers.put(CPF, usuario);
				break;
			case "Presidente":
				usuario = new Presidente(nome, CPF, senha, tipoUsuario, tipoConta);
				mapUsers.put(CPF, usuario);
				break;
			}
			// Direcionamento de dados de acordo com o tipo de conta
			Conta conta = null;
			switch (tipoConta) {
			case "Corrente":
				conta = new ContaCorrente(nome, CPF, tipoConta, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
				mapContas.put(CPF, conta);
				mapContasNumeroConta.put(numeroConta, conta);
				tContas.add(conta);
				break;

			case "Poupanca":
				conta = new ContaPoupanca(nome, CPF, tipoConta, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
				mapContas.put(CPF, conta);
				mapContasNumeroConta.put(numeroConta, conta);
				tContas.add(conta);
				break;
			}
		}
		buffRead.close();
//Login - Usuário (CPF)
		try {
			System.out.println("Digite seu CPF: ");
			Scanner sc = new Scanner(System.in);
			String cpfParaLogar = sc.nextLine();
			Pessoa logado = login(cpfParaLogar, mapUsers);
			Conta logada = mapContas.get(cpfParaLogar);
			primeiroMenu(logado.getCPF(), logada, mapContas, mapContasNumeroConta, tContas);

		} catch (NullPointerException e) {
		} catch (Exception e) {
			System.out.println("");
		}
	}

//Login - Senha
	public static Pessoa login(String cpfParaLogar, Map<String, Pessoa> mapUsers) {

		Pessoa logado = mapUsers.get(cpfParaLogar);

		if (mapUsers.get(cpfParaLogar) != null) {
			System.out.println("Digite sua senha: ");
			Scanner sc2 = new Scanner(System.in);
			String senhaDigitada = sc2.next();
			if (senhaDigitada.equals(logado.getSenha())) {
				return logado;
			} else {
				System.out.println("Senha incorreta, tente novamente!");
				return login(senhaDigitada, mapUsers);
			}
		} else {
			System.out.println("Usuário inexistente, tente novamente!");
			return null;
		}
	}

//Primeiro menu após a entrada do usuário
	public static void primeiroMenu(String string, Conta logada, Map<String, Conta> mapContas,
			Map<Integer, Conta> mapContasNumeroConta, List<Object> numeroAgencias) throws IOException {

		boolean sair = false;

		do {
			// Primeiro Menu
			System.out.println("\nSeja bem vindo, " + logada.getNome() + "!");
			System.out.println("\nTipo da conta: " + logada.getTipoConta() + "!");
			System.out.println(
					"Numero da Conta: " + logada.getNumeroDaConta() + "\nAgência: " + logada.getAgencia() + "\n");
			System.out.println("Escolha a operação desejada:" + "\n1- Movimentações\n2- Relatórios\n3- Sair ");
			Scanner sc = new Scanner(System.in);
			int operacao;
			try {
				operacao = sc.nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Operação inválida. Digite um número válido.");
				sc.next();
				continue;
			}
			switch (operacao) {
			case 1:
				// Chama o Menu de movimentações
				Conta.movimentacoes(string, logada, mapContas, mapContasNumeroConta);
				break;

			case 2:
				// Chama o Menu de relatórios
				Conta.relatorios(string, logada, mapContas, mapContasNumeroConta, numeroAgencias);
				// Pergunta se deseja continuar ou sair
				if (verificaSairDoPrimeiroMenu()) {
					System.out.println("Obrigado por utilizar os nossos serviços");
					sair = true;
				}
				break;
			// Para sair do Programa
			case 3:
				try {
					System.out.println("\nVolte sempre!");
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sair = true;
				break;

			default:
				System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
				break;
			}
		} while (!sair);
	}

//Método para verificação do Primeiro menu após a entrada do usuário
	public static boolean verificaSairDoPrimeiroMenu() {
		int optMenu = 0;
		do {
			System.out.println("Muito obrigado por utilizar nossos serviços!\n"
					+ "\nDeseja realizar outra operação? Digite 1 caso SIM ou 2 caso NÃO.");
			try {
				optMenu = new Scanner(System.in).nextInt();
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida! Digite um número válido.");
				continue;
			}
		} while (optMenu < 1 || optMenu > 2);
		return optMenu == 2;
	}

}