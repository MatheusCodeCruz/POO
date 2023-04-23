package br.com.residencia.poo.contas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.residencia.poo.sistema.SistemaPrincipal;

public abstract class Conta implements Comparable<Conta> {
	private String tipoConta;
	private String CPFDoTitular;
	private String nome;
	private String tipoUsuario;
	private int numeroDaConta;
	private int agencia;
	private double saldo;
	private double tarifacao;

	protected Conta(String tipoConta, String cPFDoTitular, String nome, String tipoUsuario, int numeroDaConta,
			int agencia, double saldo, double tarifacao) {

		this.tipoConta = tipoConta;
		this.CPFDoTitular = cPFDoTitular;
		this.nome = nome;
		this.tipoUsuario = tipoUsuario;
		this.numeroDaConta = numeroDaConta;
		this.agencia = agencia;
		this.saldo = saldo;
		this.tarifacao = tarifacao;
	}

	public int compareTo(Conta a) {
		return nome.compareTo(a.getNome());
	}

	public static void movimentacoes(String string, Conta logada, Map<String, Conta> mapTipoConta,
			Map<Integer, Conta> mapNumeroConta) throws IOException {

		boolean sair = false;

		do {
			// Menu Principal
			System.out.println("Escolha a operação desejada:\n1- Saque\n2- Depósito\n"
					+ "3- Transferência\n4- Seguro de Vida\n5- Voltar ");
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

			// Para saque
			case 1:
				try {
					System.out.println("Digite o valor do saque:");
					double valorASacar = new Scanner(System.in).nextDouble();
					if (valorASacar == logada.saldo) {
						System.out.println("Caso você saque todo o seu saldo, ficará com"
								+ " seu saldo no especial\nDeseja continuar? " + "1- Sim ou 2 - Não\n");
						Scanner scE = new Scanner(System.in);
						int operacaoE = scE.nextInt();
						// Para verificar caso saque o total
						switch (operacaoE) {
						case 1:

						case 2:
							System.out.println("Obrigado por utilizar a nossa plataforma!");
							sair = true;
							System.exit(0);
						}
					}
					// Ato do Saque
					if (verificaValor(valorASacar) && valorASacar <= logada.getSaldo()
							&& logada.getTipoConta().equals("Corrente")) {
						saque(valorASacar, logada);
						logada.tarifacao = 0.0;
						logada.saldo = logada.saldo - 0.1;
						logada.tarifacao += 0.1;
						System.out.println("Taxa de saque: R$ " + Arquivos.formatInt(logada.tarifacao)
								+ "\nSaldo restante R$ " + logada.saldo);
						Relatorios.relSaque(logada.nome, valorASacar, logada.saldo);
						Relatorios.nfSaque(logada.nome, logada.CPFDoTitular, valorASacar);
						System.out.println("\nRetire seu recibo no caixa!\n");

					} else if (verificaValor(valorASacar) && valorASacar <= logada.getSaldo()
							&& logada.getTipoConta().equals("Poupanca")) {
						saque(valorASacar, logada);
						System.out.println(
								"Você acabou de fazer um saque, seu saldo " + "restante é de R$ " + logada.saldo);
						Relatorios.relSaque(logada.nome, valorASacar, logada.saldo);
						Relatorios.nfDeposito(logada.nome, logada.nome, valorASacar);
						System.out.println("\nRetire seu recibo no caixa!\n");
					}

					else {
						System.out.println("Não foi possível realizar a operação. " + "Digite um valor válido. \n");
					}
					// Opção para volta ao menu ou saída
					if (SistemaPrincipal.verificaMenu()) {
						System.out.println("Obrigado por utilizar o nosso banco!");
						sair = true;
						System.exit(0);
					}
					break;
				} catch (Exception ee) {
					System.out.println("Você inseriu um caracter invalido. " + "Por favor, tente novamente.\n");
					break;
				}

				// Para deposito
			case 2:
				try {
					System.out.println("\nDigite o valor que deseja depositar na sua Conta:");
					double valorADepositar = new Scanner(System.in).nextDouble();
					if (verificaValor(valorADepositar) && logada.getTipoConta().equals("Corrente")) {
						deposito(valorADepositar, logada);
						logada.tarifacao = 0.0;
						logada.saldo = logada.saldo - 0.1;
						logada.tarifacao += 0.1;
						System.out.println("\nTaxa de deposito: R$ " + Arquivos.formatInt(logada.tarifacao));
						System.out.println(
								"Depósito efetuado com sucesso para " + logada.nome + "\nCPF: " + logada.CPFDoTitular
										+ "\nConta número: " + logada.numeroDaConta + "\nAgência: " + logada.agencia);
						String.format("%.2f", logada.saldo);
						Relatorios.relDeposito(logada.nome, valorADepositar, logada.saldo);
						Relatorios.nfDeposito(logada.nome, logada.CPFDoTitular, valorADepositar);
						System.out.println("\nSeu Saldo passou a ser: R$" + logada.saldo);
						System.out.println("\nRetire seu recibo no caixa!");
					} else if (verificaValor(valorADepositar) && logada.getTipoConta().equals("Poupanca")) {
						deposito(valorADepositar, logada);
						Relatorios.relDeposito(logada.nome, valorADepositar, logada.saldo);
						Relatorios.nfDeposito(logada.nome, logada.CPFDoTitular, valorADepositar);
						System.out.println(
								"Depósito efetuado com sucesso para " + logada.nome + ".\nCPF: " + logada.CPFDoTitular
										+ "\nConta número: " + logada.numeroDaConta + "\nAgência: " + logada.agencia);
						System.out.println("\nSeu Saldo passou a ser: R$" + logada.saldo);
						System.out.println("\nRetire seu recibo no caixa!");
					} else {
						System.out.println("\nNão foi possivel realizar a operação. " + "Digite um valor valido. \n");
					}
					// Opção para volta ao menu ou saída
					if (SistemaPrincipal.verificaMenu()) {
						System.out.println("\nObrigado por utilizar a nossa plataforma!");
						sair = true;
						System.exit(0);
					}
					break;
				} catch (Exception ee) {
					System.out.println("\nVocê inseriu um caracter invalido. " + "Por favor, tente novamente.\n");
					break;
				}

				// Para Transferência
			case 3:
				try {
					System.out.println("Digite a conta de destino");
					int contaDestino = new Scanner(System.in).nextInt();
					if (contaDestino == logada.numeroDaConta) {
						System.out.println("Impossível transferir para a mesma conta " +
						// Pergunta se deseja continuar ou sair
								"\nDeseja voltar ao menu? " + "1- Sim ou 2 - Não\n");
						Scanner scT = new Scanner(System.in);
						int operacaoT = scT.nextInt();
						switch (operacaoT) {

						case 1:
							return;
						case 2:
							break;
						}
					}
					if (mapNumeroConta.get(contaDestino) != null) {
						Conta temporaria = mapNumeroConta.get(contaDestino);
						System.out.println("Digite o valor da transferência para " + temporaria.nome + ": R$");
						double valorATransferir = new Scanner(System.in).nextDouble();
						if (verificaValor(valorATransferir) && valorATransferir <= logada.getSaldo()
								&& logada.getTipoConta().equals("Corrente")) {
							System.out.println(
									"É cobrado uma taxa de R$0,20 para " + "transferências pela Conta Corrente\n");
							logada.saldo -= valorATransferir;
							temporaria.saldo += valorATransferir;
							logada.tarifacao = 0.0;
							logada.saldo = logada.saldo - 0.1;
							logada.tarifacao += 0.2;
							System.out.println("Transferencia efetuada com sucesso para " + temporaria.nome + "\nCPF: "
									+ temporaria.CPFDoTitular + "\nConta número: " + temporaria.numeroDaConta
									+ "\nAgência: " + temporaria.agencia);
							Relatorios.relTransferencia(logada.nome, valorATransferir, contaDestino);
							// Pergunta se deseja continuar ou sair
							if (SistemaPrincipal.verificaMenu()) {
								System.out.println(logada.nome + "Obrigado por utilizar a nossa plataforma");
								sair = true;
								System.exit(0);
							}
							break;
						} else if (verificaValor(valorATransferir) && valorATransferir <= logada.getSaldo()
								&& logada.getTipoConta().equals("Poupanca")) {
							logada.saldo -= valorATransferir;
							temporaria.saldo += valorATransferir;
							Relatorios.relTransferencia(logada.nome, valorATransferir, contaDestino);
							System.out.println("Transferencia efetuada com sucesso para " + temporaria.nome + "\nCPF: "
									+ temporaria.CPFDoTitular + "\nConta número: " + temporaria.numeroDaConta
									+ "\nAgência: " + temporaria.agencia);
							if (SistemaPrincipal.verificaMenu()) {
								System.out.println("\nObrigado por utilizar o nosso Banco.");
								sair = true;
								System.exit(0);
							}
						} else {
							System.out.println("Não foi possivel realizar a operação, voltando ao Menu.\n");
							return;
						}
					}
					break;
				} catch (Exception ee) {
					System.out.println("Você inseriu um caracter invalido. Por favor, " + "tente novamente. \n");
					break;
				}

				// Para Seguro de Vida
			case 4:
				System.out.println("Digite o valor que deseja assegurar: ");
				double contaSeguro = new Scanner(System.in).nextInt();
				if (logada.getSaldo() >= contaSeguro * 0.2) {
					double valorTributacao = SeguroVida.Seguro(contaSeguro);
					System.out.printf("Valor assegurado: R$ %.2f\n", contaSeguro);
					System.out.println("Será cobrado uma taxa de 20% para realizar essa "
							+ "operação. \n Valor da tributação: R$" + valorTributacao);
					System.out.println("\nDeseja continuar com a solicitação? \n1- Sim" + "\n2- Não");
					int escolha = sc.nextInt();
					switch (escolha) {

					case 1:
						logada.saldo -= SeguroVida.Seguro(contaSeguro);
						System.out.println("Parabéns! Você acaba de adquirir nosso " + "seguro!\n");
						Relatorios.relSeguroDeVida(logada.nome, contaSeguro);
						Relatorios.nfSeguro(logada.nome, logada.CPFDoTitular, contaSeguro);
						break;
					case 2:
						System.out.println("Te esperamos em uma próxima vez fazer " + "nosso seguro!");
						break;
					}
					break;
				} else {
					System.out.println("Você não tem saldo suficiente para completar " + "essa solicitação!\n");
				}
				// Opção para volta ao menu ou saída
				if (SistemaPrincipal.verificaMenu()) {
					System.out.println("Obrigado por utilizar a nossa plataforma!");
					sair = true;
					System.exit(0);
				}

				// Para voltar
			case 5:
				sair = true;
				break;
			}
		} while (!sair);
	}

	public static void relatorios(String string, Conta logada, Map<String, Conta> mapTipoConta,
			Map<Integer, Conta> mapNumeroConta, List<Object> tContas) {

		boolean sair = false;

		if (logada.getTipoUsuario().equals("Cliente")) {
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a opção desejada: \n1- Saldo"
						+ "\n2- Relatorio Tarifa Conta Corrente\n3- Informações sobre tarifas");
				Scanner sc = new Scanner(System.in);
				int operacao = 0;
				try {
					operacao = sc.nextInt();
				} catch (InputMismatchException e) {
					System.out.println("\nOperação inválida. Digite um número válido.\n");
					sc.next();
				}
				switch (operacao) {

				case 1:
					System.out.println("Seu saldo atual é de R$" + Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					System.out
							.println("A tarifa da Conta Corrente é de R$" + Arquivos.formatInt(logada.getTarifacao()));
					break;
				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$ 0,10*"
							+ "\nPara cada depósito o valor cobrado é de R$ 0,10*"
							+ "\nPara cada transferência será cobrado o valor "
							+ "de R$ 0,20*\n*Valores válidos para conta corrente.\n**Conta poupança não será tarifada");
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println("Escolha a operação desejada: \n1- Saldo da Conta \n2 - Relatório de Rendimentos"
						+ " da Conta Poupança");
				Scanner sc = new Scanner(System.in);
				int operacao = sc.nextInt();
				switch (operacao) {

				case 1:
					System.out.println("O seu saldo atual é de R$" + Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					relRendimentosPoup(logada);
					break;
				default:
					break;
				}
			}
		}

		if (logada.getTipoUsuario().equals("Gerente")) {
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada: \n1 - Saldo\n2 - Relatório "
						+ "de Tarifação da Conta Corrente\n3 - Informações sobre tarifas"
						+ "\n4 - Relatorio de contas gerenciadas\n5 - Relatório de "
						+ "Rendimentos da Poupança\n6 - Relatório Individual de Clientes\n7 - Voltar ou Sair");
				Scanner sc = new Scanner(System.in);
				int operacao = sc.nextInt();
				switch (operacao) {

				case 1:
					System.out.println("Seu saldo atual é de R$ " + Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					System.out
							.println("A tarifa da conta corrente é de R$" + Arquivos.formatInt(logada.getTarifacao()));
					break;
				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$ 0,10\n"
							+ "Para cada depósito o valor cobrado é de R$ 0,10\nPara cada"
							+ " transferência será cobrado o valor de R$ 0,20\n Valores "
							+ "válidos para conta corrente. COnta poupança não será tarifada");
					break;
				case 4:
					System.out.println("Numero de contas gerenciados na mesma agencia:");
					int contador = 0;
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						if (temp.getAgencia() == logada.getAgencia()) {
							contador++;
						}
					}
					System.out.println(Arquivos.formatInt(contador));
					break;
				case 5:
					relRendimentosPoup(logada);
					break;
				case 6:
					while (logada.agencia == 1) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.agencia == 1) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}

						}
						break;
					}
					while (logada.agencia == 2) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.agencia == 2) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}

						}
						break;
					}
					while (logada.agencia == 3) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.agencia == 3) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}

						}
						break;
					}
					while (logada.agencia == 4) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.agencia == 4) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}

						}
						break;
					}
				case 7:
					if (SistemaPrincipal.verificaMenu()) {
						System.out.println("Obrigado por utilizar a nossa plataforma!");
						sair = true;
						System.exit(0);
					}
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println("Escolha a operação desejada: \n1 - Saldo\n2 - Relatório "
						+ "de Tarifação da Conta Corrente\n3 - Informações sobre tarifas"
						+ "\n4 - Relatorio de contas gerenciadas\n5 - Relatório de "
						+ "Rendimentos da Poupança\n6 - Relatório Individual de Clientes\n7 - Voltar ou Sair");
				Scanner sc = new Scanner(System.in);
				int operacao = sc.nextInt();
				switch (operacao) {

				case 1:
					System.out.println("Seu saldo atual é de R$ " + Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					System.out
							.println("A tarifa da conta corrente é de R$" + Arquivos.formatInt(logada.getTarifacao()));
					break;
				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$ 0,10\n"
							+ "Para cada depósito o valor cobrado é de R$ 0,10\nPara cada"
							+ " transferência será cobrado o valor de R$ 0,20\n Valores "
							+ "válidos para conta corrente. COnta poupança não será tarifada");
					break;
				case 4:
					System.out.println("Numero de contas gerenciados na mesma agencia:");
					int contador = 0;
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						if (temp.getAgencia() == logada.getAgencia()) {
							contador++;
						}
					}
					System.out.println(Arquivos.formatInt(contador));
					break;
				case 5:
					relRendimentosPoup(logada);
					break;
				case 6:
					while (logada.agencia == 1) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.agencia == 1) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}
						}
						break;
					}
				case 7:
					if (SistemaPrincipal.verificaMenu()) {
						System.out.println("Obrigado por utilizar a nossa plataforma!");
						sair = true;
						System.exit(0);
					}
					break;
				default:
					break;
				}
			}
		}

		if (logada.getTipoUsuario().equals("Diretor")) {
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada:" + "\n1 - Saldo da conta\n2- Relatorio de Tarifação"
						+ " da Conta Corrente\n3- Informação sobre tarifas\n4- Informações dos Clientes do Sistema"
						+ "\n5 - Informações de Gerentes\n6 - Voltar ou Sair");
				Scanner sc = new Scanner(System.in);
				int operacao = sc.nextInt();
				switch (operacao) {

				case 1:
					System.out.println("O saldo da sua conta é de R$:" + Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					System.out
							.println("A tarifa da conta corrente é de R$" + Arquivos.formatInt(logada.getTarifacao()));
					break;
				case 3:
					System.out.println("Para cada saque será cobrado o valor de R$ 0,10\n"
							+ "Para cada depósito o valor cobrado é de R$ 0,10\n"
							+ "Para cada transferência será cobrado o valor de R$ 0,20\n"
							+ "Valores válidos para conta corrente. COnta poupança não será tarifada");
					break;
				case 4:
					System.out.println("Informações dos Clientes do Sistema\n");
					List lista = new ArrayList();
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						lista.add(temp);
					}
					Collections.sort(lista);
					for (Object temp : lista) {
						Conta conta = (Conta) (temp);
						System.out.println("Nome: " + conta.nome + ", Número da Conta: " + conta.numeroDaConta + "-"
								+ conta.agencia);
					}
					break;
				case 5:
					while (logada.agencia == 1) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.getTipoUsuario().equals("Gerente") && (temp.agencia != 2) && (temp.agencia != 4)) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}
						}
						break;
					}
					while (logada.agencia == 2) {
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.getTipoUsuario().equals("Gerente") && (temp.agencia != 1) && (temp.agencia != 3)) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
							}
						}
						break;
					}
				case 6:
					if (SistemaPrincipal.verificaMenu()) {
						System.out.println("Obrigado por utilizar a nossa plataforma!");
						sair = true;
						System.exit(0);
					}
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println("Escolha a opção desejada: \n1- Saldo\n2- Relatórios de Rendimento da Poupança"
						+ "\n3- Informações dos Clientes do Sistema\n4 - Informações de Gerentes");
				Scanner sc = new Scanner(System.in);
				int operacoes = sc.nextInt();
				switch (operacoes) {

				case 1:
					System.out.println(Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					relRendimentosPoup(logada);
					break;
				case 3:
					System.out.println("Informações dos Clientes do Sistema");
					List lista = new ArrayList();
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						lista.add(temp);
						System.out.println(temp.nome);
					}
					Collections.sort(lista);
					for (Object temp : lista) {
						Conta conta = (Conta) (temp);
						System.out.println(conta.nome);
					}
					break;
				case 4:
					System.out.println("Informações dos Gerentes do Banco");
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						if (temp.tipoUsuario.equals("Gerente")) {
							System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
									+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ " + temp.getSaldo()
									+ ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
						}
					}
					break;
				default:
					break;
				}
			}
		}

		if (logada.getTipoUsuario().equals("Presidente")) {
			double capitalTotal = 0;
			if (logada.getTipoConta().equals("Corrente")) {
				System.out.println("Escolha a operação desejada: \n1- Saldo\n"
						+ "2- Relatorios de tarifação da Conta Corrente\n"
						+ "3- Informações sobre tarifas\n4- Informações dos Clientes do Sistema\n"
						+ "5 - Informações sobre Gerentes e Diretores\n" + "6- Relatório - Capital Total do Banco");
				Scanner sc = new Scanner(System.in);
				int operacoes = sc.nextInt();

				switch (operacoes) {

				case 1:
					System.out.println("O saldo da sua conta é de R$:" + Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					System.out.println("A tarifa da sua conta é de R$" + Arquivos.formatInt(logada.getTarifacao()));
					break;
				case 3:
					System.out.println(
							"Para cada saque será cobrado o valor de R$ 0,10\n Para cada depósito o valor cobrado "
									+ "é de R$ 0,10\nPara cada transferência será cobrado o valor de R$ 0,20\n"
									+ "Valores válidos para conta corrente. Conta poupança não será tarifada");
					break;
				case 4:
					System.out.println("Informações dos Clientes do Sistema");
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
								+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ " + temp.getSaldo()
								+ ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
					}
					break;
				case 5:
					System.out.println("Informações dos Gerentes e Diretores do Banco");
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						if (temp.tipoUsuario.equals("Gerente") || temp.tipoUsuario.equals("Diretor")) {
							System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
									+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ " + temp.getSaldo()
									+ ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
						}
					}
					break;
				case 6:
					System.out.println("Capital Total do NoBank");
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						capitalTotal += temp.getSaldo() + temp.getTarifacao();
					}
					System.out.println("R$" + Arquivos.formatInt(capitalTotal) + "\n");
					break;
				default:
					break;
				}
			} else if (logada.getTipoConta().equals("Poupanca")) {
				System.out.println("Escolha a operação desejada: \n1- Saldo\n2- Relatorios de Rendimentos da Poupança"
						+ "\n3- Informações dos Clientes do Sistema \n4- Relatorio - Capital Total do Banco"
						+ "\n5 - Voltar ao Menu");
				Scanner sc = new Scanner(System.in);
				int operacoes = sc.nextInt();
				switch (operacoes) {

				case 1:
					System.out.println(Arquivos.formatInt(logada.getSaldo()));
					break;
				case 2:
					relRendimentosPoup(logada);
					break;
				case 3:
					System.out.println("Informações dos Gerentes e Diretores do Banco");
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						if (temp.tipoUsuario.equals("Gerente") || temp.tipoUsuario.equals("Diretor")) {
							System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
									+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ " + temp.getSaldo()
									+ ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
						}
					}
					break;
				case 4:
					System.out.println("Capital Total =");
					for (Object object : tContas) {
						Conta temp = (Conta) object;
						capitalTotal += temp.getSaldo() + temp.getTarifacao();
					}
					System.out.println(Arquivos.formatInt(capitalTotal));
					break;
				default:
					break;
				}
			}
		}
	}

	public static boolean verificaValor(double valor) {
		if (valor > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public static void saque(double valorASacar, Conta logada) throws IOException {
		logada.saldo -= valorASacar;
	}

	public static void deposito(double valorASacar, Conta logada) throws IOException {
		logada.saldo += valorASacar;
	}

	public static void relRendimentosPoup(Conta logada) {
		try {
			System.out.println("Insira quanto gostaria de investir: ");
			Scanner valor = new Scanner(System.in);
			double tempoRendimento = valor.nextDouble();
			System.out.println("Simule no prazo desejado: \nDigite 1 para 3 meses"
					+ "\nDigite 2 para 6 meses\nDigite 3 para 1 Ano");
			Scanner sc = new Scanner(System.in);
			int operacao = sc.nextInt();
			switch (operacao) {

			case 1:
				System.out.println("Valor utilizado para a cotação: R$" + tempoRendimento);
				System.out.println("O Valor investido mais o rendimento após 3 meses será: R$"
						+ Arquivos.formatInt(tempoRendimento += tempoRendimento * 0.025));
				break;
			case 2:
				System.out.println("Valor utilizado para a cotação: R$" + tempoRendimento);
				System.out.println("O Valor investido mais o rendimento após 6 meses será: R$"
						+ Arquivos.formatInt(tempoRendimento += tempoRendimento * 0.05));
				break;
			case 3:
				System.out.println("Valor utilizado para a cotação: R$" + tempoRendimento);
				System.out.println("O Valor investido mais o rendimento após 1 Ano será: R$"
						+ Arquivos.formatInt(tempoRendimento += tempoRendimento * 0.1));
				break;
			default:
				System.out.println("Caracter inválido.");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Caracter inválido!\n");
		}
	}

	public static void cpfBusca(String nome, String cPFDoTitular, int numeroConta, int agencia, double valor) {
		Scanner scB = new Scanner(System.in);
		System.out.println("Digite o CPF que deseja buscar: ");
		String buscaCPF = scB.next();
		String Busca = cPFDoTitular;
		switch (buscaCPF) {

		case "12345678911":
			if (Busca == "12345678911") {
				System.out.println(SistemaPrincipal.login(nome, null));
			}
			break;
		case "12345678912":

			break;
		case "12345678913":

			break;
		case "12345678914":

			break;
		case "12345678915":

			break;
		case "12345678916":

			break;
		case "12345678917":

			break;
		case "12345678918":

			break;
		case "12345678919":

			break;
		case "12345678920":

			break;
		case "12345678922":

			break;
		case "12345678923":

			break;
		case "12345678924":

			break;
		case "12345678925":

			break;
		case "12345678926":

			break;
		case "12345678927":

			break;
		case "12345678921":

			break;
		}
	}

	@Override
	public String toString() {
		return "Conta [tipoConta=" + tipoConta + ", CPFDoTitular=" + CPFDoTitular + ", nome=" + nome + ", tipoUsuario="
				+ tipoUsuario + ", numeroDaConta=" + numeroDaConta + ", agencia=" + agencia + ", saldo=" + saldo
				+ ", tarifacao=" + tarifacao + "]";
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public String getCPFDoTitular() {
		return CPFDoTitular;
	}

	public String getNome() {
		return nome;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public int getNumeroDaConta() {
		return numeroDaConta;
	}

	public int getAgencia() {
		return agencia;
	}

	public double getSaldo() {
		return saldo;
	}

	public double getTarifacao() {
		return tarifacao;
	}

}
//	public static boolean verificaSairDoSegundoMenu() {
//		int optMenu = 0;
//		do {
//			System.out.println("\nDeseja realizar outra operação? Digite 1 para VOLTAR ou 2 caso SAIR.");
//			try {
//				optMenu = new Scanner(System.in).nextInt();
//			} catch (InputMismatchException e) {
//				System.out.println("\nEntrada inválida! Digite um número válido.");
//				continue;
//			}
//		} while (optMenu < 1 || optMenu > 2);
//		return optMenu == 2;
//	}
