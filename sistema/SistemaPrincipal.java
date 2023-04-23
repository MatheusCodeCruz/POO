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

import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.contas.ContaCorrente;
import br.com.residencia.poo.contas.ContaPoupanca;
import br.com.residencia.poo.pessoas.Cliente;
import br.com.residencia.poo.pessoas.Diretor;
import br.com.residencia.poo.pessoas.Gerente;
import br.com.residencia.poo.pessoas.Pessoa;
import br.com.residencia.poo.pessoas.Presidente;

public class SistemaPrincipal {

	public static void main(String[] args) throws NumberFormatException, IOException {

		Map<String, Pessoa> mapUsuarios = new HashMap<>();
		Map<String, Conta> mapTipoConta = new HashMap<>();
		Map<Integer, Conta> mapNumeroConta = new HashMap<>();
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
		String path = "C:\\serratec\\POO\\Workspace\\ProjetoFinal\\pessoas.txt";
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
				mapUsuarios.put(CPF, usuario);
				break;
			case "Gerente":
				usuario = new Gerente(nome, CPF, senha, tipoUsuario, tipoConta, agencia);
				mapUsuarios.put(CPF, usuario);
				break;
			case "Diretor":
				usuario = new Diretor(nome, CPF, senha, tipoUsuario, tipoConta, agencia);
				mapUsuarios.put(CPF, usuario);
				break;
			case "Presidente":
				usuario = new Presidente(nome, CPF, senha, tipoUsuario, tipoConta);
				mapUsuarios.put(CPF, usuario);
				break;
			}
			// Direcionamento de dados de acordo com o tipo de conta
			Conta conta = null;
			switch (tipoConta) {
			case "Corrente":
				conta = new ContaCorrente(nome, CPF, tipoConta, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
				mapTipoConta.put(CPF, conta);
				mapNumeroConta.put(numeroConta, conta);
				tContas.add(conta);
				break;

			case "Poupanca":
				conta = new ContaPoupanca(nome, CPF, tipoConta, tipoUsuario, numeroConta, agencia, saldo, tarifacao);
				mapTipoConta.put(CPF, conta);
				mapNumeroConta.put(numeroConta, conta);
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
			Pessoa logado = login(cpfParaLogar, mapUsuarios);
			Conta logada = mapTipoConta.get(cpfParaLogar);
			primeiroMenu(logado.getCPF(), logada, mapTipoConta, mapNumeroConta, tContas);
		} catch (NullPointerException e) {
		} catch (Exception e) {
			System.out.println("");
		}
	}

//Login - Senha
	public static Pessoa login(String cpfParaLogar, Map<String, Pessoa> mapUsuarios) {

		Pessoa logado = mapUsuarios.get(cpfParaLogar);

		if (mapUsuarios.get(cpfParaLogar) != null) {
			System.out.println("Digite sua senha: ");
			Scanner sc2 = new Scanner(System.in);
			String senhaDigitada = sc2.next();
			if (senhaDigitada.equals(logado.getSenha())) {
				return logado;
			} else {
				System.out.println("Senha incorreta, tente novamente!");
				return login(senhaDigitada, mapUsuarios);
			}
		} else {
			System.out.println("Usuário inexistente, tente novamente!");
			return null;
		}
	}

//Primeiro menu após a entrada do usuário
	public static void primeiroMenu(String string, Conta logada, Map<String, Conta> mapContas,
			Map<Integer, Conta> mapNumeroConta, List<Object> tContas) throws IOException {

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
				Conta.movimentacoes(string, logada, mapContas, mapNumeroConta);
				break;

			case 2:
				// Chama o Menu de relatórios
				Conta.relatorios(string, logada, mapContas, mapNumeroConta, tContas);
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
	public static boolean verificaMenu() {
		int optMenu = 0;
		do {
			System.out.println("\nDeseja realizar outra operação? Digite 1 para VOLTAR ou 2 para SAIR.");
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