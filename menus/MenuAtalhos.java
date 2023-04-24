package br.com.residencia.poo.menus;


import br.com.residencia.poo.contas.Arquivos;
import br.com.residencia.poo.contas.Conta;
import br.com.residencia.poo.sistema.SistemaPrincipal;

public class MenuAtalhos {

	static boolean sair = false;

	// Método para apresentar o Saldo da conta logada
	public static void saldoConta(Conta logada) {
		System.out.println("Seu saldo atual é de R$ " + Arquivos.formatInt(logada.getSaldo()));
		if (SistemaPrincipal.verificaMenu()) {
			System.out.println("Obrigado por utilizar a nossa plataforma!");
			sair = true;
			System.exit(0);
		}
	}

	// Método para apresentar a soma das tarifas cobradas até o momento
	public static void totalTarifas(Conta logada) {
		System.out.println(
				"\nO total de tarifas cobradas até agora é de: R$ " + Arquivos.formatInt(logada.getTarifacao()));
		if (SistemaPrincipal.verificaMenu()) {
			System.out.println("Obrigado por utilizar a nossa plataforma!");
			sair = true;
			System.exit(0);
		}
	}

	// Método para apresentar a tabela de tarifas
	public static void infoTarifas() {
		System.out.println("Para cada saque será cobrado o valor de R$ 0,10*"
				+ "\nPara cada depósito o valor cobrado é de R$ 0,10*"
				+ "\nPara cada transferência será cobrado o valor "
				+ "de R$ 0,20*\n*Valores válidos para conta corrente.\n**Conta poupança não será tarifada");
		if (SistemaPrincipal.verificaMenu()) {
			System.out.println("Obrigado por utilizar a nossa plataforma!");
			sair = true;
			System.exit(0);
		}
	}

	// Método para evitar repetições no código
	public static void relRendimentosPoup(Conta logada) {
		relRendimentosPoup(logada);
		if (SistemaPrincipal.verificaMenu()) {
			System.out.println("Obrigado por utilizar a nossa plataforma!");
			sair = true;
			System.exit(0);
		}
	}

	// Método para evitar repetições no código
	public static void saidaMenu() {
		if (SistemaPrincipal.verificaMenu()) {
			System.out.println("Obrigado por utilizar a nossa plataforma!");
			sair = true;
			System.exit(0);
		}
	}

}
