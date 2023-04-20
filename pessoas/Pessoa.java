package br.com.residencia.poo.pessoas;

public class Pessoa {
	 String nome;
	 String CPF;
	 String senha;
	 String tipoPessoa;
	 String tipoConta;

	public Pessoa(String nome, String cPF, String senha, String tipoPessoa, String tipoConta) {
		this.CPF = cPF;
		this.senha = senha;
		this.tipoPessoa = tipoPessoa;
		this.tipoConta = tipoConta;
	}

	public String getNome() {
		return nome;
	}

	public String getCPF() {
		return CPF;
	}


	public String getSenha() {
		return senha;
	}


	public String getTipoPessoa() {
		return tipoPessoa;
	}

	public String getTipoConta() {
		return tipoConta;
	}

}
