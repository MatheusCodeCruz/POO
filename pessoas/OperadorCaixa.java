package br.com.residencia.poo.pessoas;

public class OperadorCaixa extends Funcionario {

	private String agenciaCaixa;

	public OperadorCaixa(String nome, String cPF, String senha, String tipoPessoa, String tipoConta, String agenciaCaixa) {
		super(nome, cPF, senha, tipoPessoa, tipoConta);
		this.setAgenciaCaixa(agenciaCaixa);
	}

	public String getAgenciaCaixa() {
		return agenciaCaixa;
	}

	public void setAgenciaCaixa(String agenciaCaixa) {
		this.agenciaCaixa = agenciaCaixa;
	}
	
}
