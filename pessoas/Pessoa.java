package br.com.residencia.poo.pessoas;

public abstract class Pessoa {

	private String nome;
	private String CPF;
	private String senha;
	private String tipoPessoa;
	private String tipoConta;

	protected Pessoa(String nome, String cPF, String senha, String tipoPessoa, String tipoConta) {
		this.nome = nome;
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
