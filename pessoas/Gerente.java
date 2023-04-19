package br.com.residencia.poo.pessoas;

public class Gerente extends Funcionarios{

	
		private String agenciaGerente;

		public Gerente(String nome, String cPF, String senha, String tipoPessoa, String tipoConta, String agenciaGerente) {
			super(nome, cPF, senha, tipoPessoa, tipoConta);
			this.agenciaGerente = agenciaGerente;
		}
		
	}
