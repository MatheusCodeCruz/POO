package br.com.residencia.poo.contas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import br.com.residencia.poo.menus.MenuAtalhos;
import br.com.residencia.poo.pessoas.Diretor;
import br.com.residencia.poo.pessoas.Gerente;
import br.com.residencia.poo.pessoas.OperadorCaixa;
import br.com.residencia.poo.pessoas.Presidente;
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
	double tarifacaoZero;

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

	// Menu de Movimentações bancárias
	public static void movimentacoes(String string, Conta logada, Map<String, Conta> mapTipoConta,
			Map<Integer, Conta> mapNumeroConta, List<Object> tContas) throws IOException {

		boolean sair = false;

		SistemaPrincipal.logoMenu();
		do {
			System.out.println("Escolha a operação desejada:\n1- Saque\n2- Depósito\n"
					+ "3- Transferência\n4- Seguro de Vida\n5- Voltar ");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			SistemaPrincipal.limpaTela7();
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
					@SuppressWarnings("resource")
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
						scE.close();
					}
					if (verificaValor(valorASacar) && valorASacar <= logada.getSaldo()
							&& logada.getTipoConta().equals("Corrente")) {
						saque(valorASacar, logada);
						logada.saldo = logada.saldo - 0.1;
						logada.tarifacao += 0.1;
						System.out.println("\nTaxa de saque: R$ 0,10\nSaldo restante R$ "
								+ Arquivos.formatInt(logada.saldo) + "\nO total de tarifas cobradas até agora é de: R$ "
								+ Arquivos.formatInt(logada.getTarifacao()));
						Relatorios.relSaque(logada.nome, valorASacar, logada.saldo);
						Relatorios.nfSaque(logada.nome, logada.CPFDoTitular, valorASacar);
						System.out.println("\nRetire seu recibo no caixa!\n");
					} else if (verificaValor(valorASacar) && valorASacar <= logada.getSaldo()
							&& logada.getTipoConta().equals("Poupanca")) {
						saque(valorASacar, logada);
						System.out.println("Você acabou de fazer um saque, seu Saldo restante é de R$ "
								+ Arquivos.formatInt(logada.saldo));
						Relatorios.relSaque(logada.nome, valorASacar, logada.saldo);
						Relatorios.nfDeposito(logada.nome, logada.nome, valorASacar);
						System.out.println("\nRetire seu recibo no caixa!\n");
					} else {
						System.out.println("Não foi possível realizar a operação. " + "Digite um valor válido. \n");
					}
					MenuAtalhos.saidaMenu();
					break;
				} catch (Exception ee) {
					System.out.println("Você inseriu um caracter invalido. " + "Por favor, tente novamente.\n");
					break;
				}
				// Para deposito
			case 2:
				try {
					System.out.println("\nDigite o valor que deseja depositar na sua Conta:");
					@SuppressWarnings("resource")
					double valorADepositar = new Scanner(System.in).nextDouble();
					if (verificaValor(valorADepositar) && logada.getTipoConta().equals("Corrente")) {
						deposito(valorADepositar, logada);
						logada.saldo = logada.saldo - 0.1;
						logada.tarifacao += 0.1;
						System.out.println("\nTaxa de deposito: R$ 0,10");
						System.out.println("Depósito efetuado com sucesso para " + logada.nome + "\nCPF: "
								+ logada.CPFDoTitular + "\nConta número: " + logada.numeroDaConta + "\nAgência: "
								+ logada.agencia + "\nO total de tarifas cobradas até agora é de: R$ "
								+ Arquivos.formatInt(logada.getTarifacao()));
						String.format("%.2f", logada.saldo);
						Relatorios.relDeposito(logada.nome, valorADepositar, logada.saldo);
						Relatorios.nfDeposito(logada.nome, logada.CPFDoTitular, valorADepositar);
						System.out.println("\nSeu Saldo passou a ser: R$" + Arquivos.formatInt(logada.saldo));
						System.out.println("\nRetire seu recibo no caixa!");
					} else if (verificaValor(valorADepositar) && logada.getTipoConta().equals("Poupanca")) {
						deposito(valorADepositar, logada);
						Relatorios.relDeposito(logada.nome, valorADepositar, logada.saldo);
						Relatorios.nfDeposito(logada.nome, logada.CPFDoTitular, valorADepositar);
						System.out.println(
								"Depósito efetuado com sucesso para " + logada.nome + ".\nCPF: " + logada.CPFDoTitular
										+ "\nConta número: " + logada.numeroDaConta + "\nAgência: " + logada.agencia);
						System.out.println("\nSeu Saldo passou a ser: R$" + Arquivos.formatInt(logada.saldo));
						System.out.println("\nRetire seu recibo no caixa!");
					} else {
						System.out.println("\nNão foi possivel realizar a operação. " + "Digite um valor valido. \n");
					}
					MenuAtalhos.saidaMenu();
					break;
				} catch (Exception ee) {
					System.out.println("\nVocê inseriu um caracter invalido. " + "Por favor, tente novamente.\n");
					break;
				}
				// Para Transferência
			case 3:
				try {
					List<Integer> numC = new ArrayList<>();
					for (Conta conta : mapNumeroConta.values()) {
						numC.add(conta.getNumeroDaConta());
					}
					System.out.println("Digite a conta de destino");
					@SuppressWarnings("resource")
					int contaDestino = new Scanner(System.in).nextInt();
					if (contaDestino == logada.numeroDaConta || !numC.contains(contaDestino)) {
						System.out.println("Impossível transferir para a mesma conta ou conta inexistente.");
						MenuAtalhos.saidaMenu();
						break;
					}
					if (mapNumeroConta.get(contaDestino) != null) {
						Conta temporaria = mapNumeroConta.get(contaDestino);
						System.out.println("\nNome: " + temporaria.nome + "\nConta: " + temporaria.tipoConta
								+ "\nConta número: " + temporaria.numeroDaConta + "\nAgência: " + temporaria.agencia);
						System.out
								.println("É cobrado uma taxa de R$0,20 para " + "transferências pela Conta Corrente\n");
						System.out.println("\nDigite o valor da transferência para " + temporaria.nome + ": R$");
						@SuppressWarnings("resource")
						double valorATransferir = new Scanner(System.in).nextDouble();
						if (verificaValor(valorATransferir) && valorATransferir <= logada.getSaldo()
								&& logada.getTipoConta().equals("Corrente")) {
							logada.saldo -= valorATransferir;
							temporaria.saldo += valorATransferir;
							logada.saldo = logada.saldo - 0.1;
							logada.tarifacao += 0.2;
							System.out.println("Transferencia efetuada com sucesso para " + temporaria.nome
									+ "\nSaldo restante: R$" + Arquivos.formatInt(logada.saldo)
									+ "\nO total de tarifas cobradas até agora é de: R$ "
									+ Arquivos.formatInt(logada.getTarifacao()));
							Relatorios.relTransferencia(logada.nome, valorATransferir, contaDestino);
							Relatorios.nfTransferencia(logada.nome, logada.CPFDoTitular, valorATransferir,
									temporaria.nome);
							MenuAtalhos.saidaMenu();
							break;
						} else if (verificaValor(valorATransferir) && valorATransferir <= logada.getSaldo()
								&& logada.getTipoConta().equals("Poupanca")) {
							logada.saldo -= valorATransferir;
							temporaria.saldo += valorATransferir;
							Relatorios.relTransferencia(logada.nome, valorATransferir, contaDestino);
							Relatorios.nfTransferencia(logada.nome, logada.CPFDoTitular, valorATransferir,
									temporaria.nome);
							System.out.println("Transferencia efetuada com sucesso para " + temporaria.nome + "\nCPF: "
									+ temporaria.CPFDoTitular + "\nConta número: " + temporaria.numeroDaConta
									+ "\nAgência: " + temporaria.agencia);
							MenuAtalhos.saidaMenu();
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
				try {
					System.out.println("Digite o valor que deseja assegurar: ");
					@SuppressWarnings("resource")
					double optValor = new Scanner(System.in).nextDouble();
					if (logada.getSaldo() >= optValor * 0.2) {
						double valorTributacao = SeguroVida.Seguro(optValor);
						System.out.println("Valor assegurado: R$ " + Arquivos.formatInt(optValor));
						System.out.println("Será cobrado uma taxa de 20% para realizar essa "
								+ "operação. \nValor da tributação: R$ " + Arquivos.formatInt(valorTributacao));
						System.out.println("\nDeseja continuar com a solicitação? \n1- Sim\n2- Não");
						int escolha = sc.nextInt();
						switch (escolha) {

						case 1:
							logada.saldo -= SeguroVida.Seguro(optValor);
							System.out.println("Parabéns! Você acaba de adquirir nosso seguro!\n");
							MenuAtalhos.saidaMenu();
							Relatorios.relSeguroDeVida(logada.nome, optValor);
							Relatorios.nfSeguro(logada.nome, logada.CPFDoTitular, optValor);
							break;
						case 2:
							System.out.println("Te esperamos em uma próxima vez fazer nosso seguro!");
							MenuAtalhos.saidaMenu();
							break;
						}
						break;
					} else {
						System.out.println("Você não tem saldo suficiente para completar essa solicitação!\n");
					}
					MenuAtalhos.saidaMenu();
				} catch (Exception ee) {
					System.out.println("\nVocê inseriu um caracter invalido. " + "Por favor, tente novamente.\n");
					break;
				}
				// Para voltar
			case 5:
				sair = true;
				break;
			default:
				System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
				break;
			}
		} while (!sair);
	}

	// Menu de Relatórios de acordo com tipo de Usuário
	@SuppressWarnings("unchecked")
	public static void relatorios(String string, Conta logada, Map<String, Conta> mapTipoConta,
			Map<Integer, Conta> mapNumeroConta, List<Object> tContas) {

		boolean sair = false;

		SistemaPrincipal.logoMenu();
		do {
			if (logada.getTipoUsuario().equals("Cliente")) {
				if (logada.getTipoConta().equals("Corrente")) {
					System.out.println("Escolha a opção desejada: \n1- Saldo\n2- Relatorio Tarifa Conta Corrente"
							+ "\n3- Informações sobre tarifas\n4 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						MenuAtalhos.infoTarifas();
						break;
					case 4:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						break;
					}
				} else if (logada.getTipoConta().equals("Poupanca")) {
					System.out.println("Escolha a operação desejada: \n1- Saldo da Conta \n2 - Relatório de Rendimentos"
							+ " da Conta Poupança\n3 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 3:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				}
			}

			if (logada.getTipoUsuario().equals("Operador")) {
				if (logada.getTipoConta().equals("Corrente")) {
					System.out.println("Escolha a operação desejada: \n1 - Saldo\n2 - Relatório "
							+ "de Tarifação da Conta Corrente\n3 - Informações sobre tarifas"
							+ "\n4 - Relatório Individual de Clientes\n5 - Relatorio de Bonificacao\n6 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						MenuAtalhos.infoTarifas();
						break;
					case 4:
						while (logada.agencia == 1) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 1) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Conta: "
											+ temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 2) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 2) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Conta: "
											+ temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 3) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 3) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Conta: "
											+ temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 4) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 4) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Conta: "
											+ temp.tipoConta);
								}
							}
							break;
						}
						MenuAtalhos.saidaMenu();
						break;
					case 5:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						MenuAtalhos.saidaMenu();
						break;
					case 6:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				} else if (logada.getTipoConta().equals("Poupanca")) {
					System.out.println("Escolha a operação desejada: \n1 - Saldo\n2 - Relatório "
							+ "de Tarifação da Conta Corrente\n3 - Informações sobre tarifas" + "\n4 - Relatório de "
							+ "Rendimentos da Poupança\n5 - Relatório Individual de Clientes"
							+ "\n6 - Relatorio de Bonificacao\n7 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						MenuAtalhos.infoTarifas();
						break;
					case 4:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 5:
						while (logada.agencia == 1) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 1) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Conta: "
											+ temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 2) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 2) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Conta: "
											+ temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 3) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 3) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 4) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 4) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						MenuAtalhos.saidaMenu();
						break;
					case 6:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						MenuAtalhos.saidaMenu();
						break;
					case 7:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				}
			}

			if (logada.getTipoUsuario().equals("Gerente")) {
				if (logada.getTipoConta().equals("Corrente")) {
					System.out.println("Escolha a operação desejada: \n1 - Saldo\n2 - Relatório "
							+ "de Tarifação da Conta Corrente\n3 - Informações sobre tarifas"
							+ "\n4 - Relatorio de contas gerenciadas\n5 - Relatório de "
							+ "Rendimentos da Poupança\n6 - Relatório Individual de Clientes\n"
							+ "7 - Relatorio de Bonificacao\n8 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						MenuAtalhos.infoTarifas();
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
						MenuAtalhos.saidaMenu();
						break;
					case 5:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 6:
						while (logada.agencia == 1) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 1) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 2) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 2) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 3) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 3) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 4) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 4) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						MenuAtalhos.saidaMenu();
						break;
					case 7:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						System.out
								.printf("\nO salário de Gerente após a bonificação é de R$" + Gerente.salarioGerente());
						MenuAtalhos.saidaMenu();
						break;
					case 8:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				} else if (logada.getTipoConta().equals("Poupanca")) {
					System.out.println("Escolha a operação desejada: \n1 - Saldo\n2 - Relatório "
							+ "de Tarifação da Conta Corrente\n3 - Informações sobre tarifas"
							+ "\n4 - Relatorio de contas gerenciadas\n5 - Relatório de "
							+ "Rendimentos da Poupança\n6 - Relatório Individual de Clientes\n"
							+ "7 - Relatorio de Bonificacao\n8 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						MenuAtalhos.infoTarifas();
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
						MenuAtalhos.saidaMenu();
						break;
					case 5:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 6:
						while (logada.agencia == 1) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 1) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 2) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 2) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 3) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 3) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 4) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.agencia == 4) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						MenuAtalhos.saidaMenu();
						break;
					case 7:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						System.out
								.printf("\nO salário de Gerente após a bonificação é de R$" + Gerente.salarioGerente());
						MenuAtalhos.saidaMenu();
						break;
					case 8:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				}
			}

			if (logada.getTipoUsuario().equals("Diretor")) {
				if (logada.getTipoConta().equals("Corrente")) {
					System.out.println("Escolha a operação desejada:"
							+ "\n1 - Saldo da conta\n2- Relatorio de Tarifação"
							+ " da Conta Corrente\n3 - Relatório de Bonificação\n4 - Relatório de Rendimentos da Poupança"
							+ "\n5- Informação sobre tarifas\n6- Informações dos Clientes do Sistema"
							+ "\n7 - Informações de Gerentes\n8 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						System.out
								.printf("\nO salário de Gerente após a bonificação é de R$" + Gerente.salarioGerente());
						System.out
								.printf("\nO salário do Diretor após a bonificação é de R$" + Diretor.salarioDiretor());
						MenuAtalhos.saidaMenu();
						break;
					case 4:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 5:
						MenuAtalhos.infoTarifas();
						break;
					case 6:
						System.out.println("Informações dos Clientes do Sistema\n");
						@SuppressWarnings("rawtypes")
						List lista = new ArrayList();
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							lista.add(temp);
						}
						Collections.sort(lista);
						for (Object temp : lista) {
							Conta conta = (Conta) (temp);
							System.out.println("Nome: " + conta.nome + ", Número da Conta: " + conta.numeroDaConta + "-"
									+ conta.agencia + ", CPF: " + conta.CPFDoTitular);
						}
						MenuAtalhos.saidaMenu();
						break;
					case 7:
						while (logada.agencia == 1) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.getTipoUsuario().equals("Gerente") && (temp.agencia != 2)
										&& (temp.agencia != 4)) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 2) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.getTipoUsuario().equals("Gerente") && (temp.agencia != 1)
										&& (temp.agencia != 3)) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							MenuAtalhos.saidaMenu();
							break;
						}
					case 8:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				} else if (logada.getTipoConta().equals("Poupanca")) {
					System.out.println("Escolha a operação desejada:"
							+ "\n1 - Saldo da conta\n2- Relatorio de Tarifação"
							+ " da Conta Corrente\n3 - Relatório de Bonificação\n4 - Relatório de Rendimentos da Poupança"
							+ "\n5- Informação sobre tarifas\n6- Informações dos Clientes do Sistema"
							+ "\n7 - Informações de Gerentes\n8 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						System.out
								.printf("\nO salário de Gerente após a bonificação é de R$" + Gerente.salarioGerente());
						System.out
								.printf("\nO salário do Diretor após a bonificação é de R$" + Diretor.salarioDiretor());
						MenuAtalhos.saidaMenu();
						break;
					case 4:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 5:
						MenuAtalhos.infoTarifas();
						break;
					case 6:
						System.out.println("Informações dos Clientes do Sistema\n");
						@SuppressWarnings("rawtypes")
						List lista = new ArrayList();
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							lista.add(temp);
						}
						Collections.sort(lista);
						for (Object temp : lista) {
							Conta conta = (Conta) (temp);
							System.out.println("Nome: " + conta.nome + ", Número da Conta: " + conta.numeroDaConta + "-"
									+ conta.agencia + ", CPF: " + conta.CPFDoTitular);
						}
						MenuAtalhos.saidaMenu();
						break;
					case 7:
						while (logada.agencia == 1) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.getTipoUsuario().equals("Gerente") && (temp.agencia != 2)
										&& (temp.agencia != 4)) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							break;
						}
						while (logada.agencia == 2) {
							for (Object object : tContas) {
								Conta temp = (Conta) object;
								if (temp.getTipoUsuario().equals("Gerente") && (temp.agencia != 1)
										&& (temp.agencia != 3)) {
									System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular()
											+ ", " + temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
											+ temp.getSaldo() + ", Conta: " + temp.tipoConta);
								}
							}
							MenuAtalhos.saidaMenu();
							break;
						}
					case 8:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				}
			}

			if (logada.getTipoUsuario().equals("Presidente")) {
				double capitalTotal = 0.0;
				if (logada.getTipoConta().equals("Corrente")) {
					System.out.println("Escolha a operação desejada: \n1 - Saldo da conta\n"
							+ "2 - Relatorios de tarifação da Conta Corrente\n3 - Relatório de número de contas do Banco"
							+ "\n4 - Relatório de Bonificação\n5 - Relatório de Rendimento da Poupança"
							+ "\n6 - Informações sobre tarifas\n7 - Informações dos Clientes"
							+ "\n8 - Informações sobre Gerentes e Diretores"
							+ "\n9- Relatório - Capital Total do Pyramid" + "\n10 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						System.out.println("Numero de contas gerenciados na mesma agencia:");
						int contador = 0;
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.saldo >= 0) {
								contador++;
							}
						}
						System.out.println(Arquivos.formatInt(contador));
						MenuAtalhos.saidaMenu();
						break;
					case 4:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						System.out
								.printf("\nO salário de Gerente após a bonificação é de R$" + Gerente.salarioGerente());
						System.out
								.printf("\nO salário do Diretor após a bonificação é de R$" + Diretor.salarioDiretor());
						System.out.printf("\nO salário do Presidente após a bonificação é de R$"
								+ Presidente.salarioPresidente());
						MenuAtalhos.saidaMenu();
						break;
					case 5:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 6:
						MenuAtalhos.infoTarifas();
						break;
					case 7:
						System.out.println("Informações dos Clientes do Sistema");
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
									+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ " + temp.getSaldo()
									+ ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
						}
						MenuAtalhos.saidaMenu();
						break;
					case 8:
						System.out.println("Informações dos Gerentes e Diretores do Banco");
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.tipoUsuario.equals("Gerente") || temp.tipoUsuario.equals("Diretor")) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
							}
						}
						MenuAtalhos.saidaMenu();
						break;
					case 9:
						System.out.println("Capital Total do Pyramid");
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							capitalTotal += temp.getSaldo() + temp.getTarifacao();
						}
						System.out.println("R$" + Arquivos.formatInt(capitalTotal) + "\n");
						MenuAtalhos.saidaMenu();
						break;
					case 10:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				} else if (logada.getTipoConta().equals("Poupanca")) {
					System.out.println("Escolha a operação desejada: \n1 - Saldo da conta\n"
							+ "2 - Relatorios de tarifação da Conta Corrente\n3 - Relatório de número de contas do Banco"
							+ "\n4 - Relatório de Bonificação\n5 - Relatório de Rendimento da Poupança"
							+ "\n6 - Informações sobre tarifas\n7 - Informações dos Clientes"
							+ "\n8 - Informações sobre Gerentes e Diretores"
							+ "\n9- Relatório - Capital Total do Pyramid" + "\n10 - Voltar");
					@SuppressWarnings("resource")
					Scanner sc = new Scanner(System.in);
					int operacao = 0;
					try {
						operacao = sc.nextInt();
					} catch (InputMismatchException e) {
						System.out.println("\nOperação inválida. Digite um número válido.\n");
						sc.next();
						continue;
					}
					switch (operacao) {

					case 1:
						MenuAtalhos.saldoConta(logada);
						break;
					case 2:
						MenuAtalhos.totalTarifas(logada);
						break;
					case 3:
						System.out.println("Numero de contas gerenciados na mesma agencia:");
						int contador = 0;
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.saldo >= 0) {
								contador++;
							}
						}
						System.out.println(Arquivos.formatInt(contador));
						MenuAtalhos.saidaMenu();
						break;
					case 4:
						System.out.printf("\nO salário de Operador de Caixa após a bonificação é de R$"
								+ OperadorCaixa.salarioOperadorCaixa());
						System.out
								.printf("\nO salário de Gerente após a bonificação é de R$" + Gerente.salarioGerente());
						System.out
								.printf("\nO salário do Diretor após a bonificação é de R$" + Diretor.salarioDiretor());
						System.out.printf("\nO salário do Presidente após a bonificação é de R$"
								+ Presidente.salarioPresidente());
						MenuAtalhos.saidaMenu();
						break;
					case 5:
						MenuAtalhos.relRendimentosPoup(logada);
						break;
					case 6:
						MenuAtalhos.infoTarifas();
						break;
					case 7:
						System.out.println("Informações dos Clientes do Sistema");
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
									+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ " + temp.getSaldo()
									+ ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
						}
						MenuAtalhos.saidaMenu();
						break;
					case 8:
						System.out.println("Informações dos Gerentes e Diretores do Banco");
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							if (temp.tipoUsuario.equals("Gerente") || temp.tipoUsuario.equals("Diretor")) {
								System.out.println("Nome: " + temp.getNome() + ", CPF: " + temp.getCPFDoTitular() + ", "
										+ temp.numeroDaConta + "-" + temp.getAgencia() + ", Saldo: R$ "
										+ temp.getSaldo() + ", Conta: " + temp.tipoConta + ", " + temp.tipoUsuario);
							}
						}
						MenuAtalhos.saidaMenu();
						break;
					case 9:
						System.out.println("Capital Total do Pyramid");
						for (Object object : tContas) {
							Conta temp = (Conta) object;
							capitalTotal += temp.getSaldo() + temp.getTarifacao();
						}
						System.out.println("R$" + Arquivos.formatInt(capitalTotal) + "\n");
						MenuAtalhos.saidaMenu();
						break;
					case 10:
						sair = true;
						break;
					default:
						System.out.println("\nOperação inválida, utilize apenas as opções apresentadas.\n");
						break;
					}
				}
			}
		} while (!sair);
	}

	// Método para filtrar valores positivos
	public static boolean verificaValor(double valor) {
		if (valor > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	// Método responsável por atualizar o Saldo
	public static void saque(double valorASacar, Conta logada) throws IOException {
		logada.saldo -= valorASacar;
	}

	// Método responsável por atualizar o Saldo
	public static void deposito(double valorASacar, Conta logada) throws IOException {
		logada.saldo += valorASacar;
	}

	// Método de cálclulo do Seguro de Vida de acordo com o tempo requerido
	public static void relRendimentosPoup(Conta logada) {
		try {
			System.out.println("Insira quanto gostaria de investir: ");
			@SuppressWarnings("resource")
			Scanner valor = new Scanner(System.in);
			double tempoRendimento = valor.nextDouble();
			System.out.println("Simule no prazo desejado: \nDigite 1 para 3 meses"
					+ "\nDigite 2 para 6 meses\nDigite 3 para 1 Ano");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			int operacao = sc.nextInt();
			switch (operacao) {

			case 1:
				System.out.println("Valor utilizado para a cotação: R$" + Arquivos.formatInt(tempoRendimento));
				System.out.println("O Valor investido mais o rendimento após 3 meses será: R$"
						+ Arquivos.formatInt(tempoRendimento += tempoRendimento * 0.025));
				break;
			case 2:
				System.out.println("Valor utilizado para a cotação: R$" + Arquivos.formatInt(tempoRendimento));
				System.out.println("O Valor investido mais o rendimento após 6 meses será: R$"
						+ Arquivos.formatInt(tempoRendimento += tempoRendimento * 0.05));
				break;
			case 3:
				System.out.println("Valor utilizado para a cotação: R$" + Arquivos.formatInt(tempoRendimento));
				System.out.println("O Valor investido mais o rendimento após 1 Ano será: R$"
						+ Arquivos.formatInt(tempoRendimento += tempoRendimento * 0.1));
				break;
			default:
				System.out.println("Caracter inválido!\n");
				return;
			}
		} catch (InputMismatchException e) {
			System.out.println("Caracter inválido!\n");
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
