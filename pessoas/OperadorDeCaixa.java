package br.com.residencia.poo.pessoas;

public class OperadorDeCaixa extends Funcionarios {

	protected OperadorDeCaixa(String nome, String cPF, String senha, String tipoPessoa, String tipoConta) {
		super(nome, cPF, senha, tipoPessoa, tipoConta);
		// TODO Auto-generated constructor stub
	}

	private String agenciaCaixa;

	public void OperadorCaixa(String nome, String cPF, String senha, String tipoPessoa, String tipoConta, String agenciaCaixa) {
		super(nome, cPF, senha, tipoPessoa, tipoConta);
		this.agenciaCaixa = agenciaCaixa;
	}
	
}
