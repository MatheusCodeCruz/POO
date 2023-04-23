package br.com.residencia.poo.contas;

import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;

public class Utilidade {

	public static String formataMoeda(double valor) {

			DecimalFormat df = new DecimalFormat ("#.##");
			String formatted = df.format(valor);
			return formatted;
	}

	public static FileWriter path (int choose) throws IOException {

		// *** ALTERAR PATH AQUI!!!!!!!!!
		String path = "C:\\serratec\\POO\\Workspace\\ProjetoFinal";

		//1- path de alterações alterações
		//2- path para nota fiscal/extrato

		if(choose == 1) {
			FileWriter arq1 = new FileWriter(path + "alteracoes.txt", true);
			return arq1;
		}
		if(choose == 2) {
			FileWriter arq2 = new FileWriter(path + "nf.txt", false);
			return arq2;
		}
		return null;
	}

}