package br.com.residencia.poo.pessoas;

public class Diretor extends Funcionario {

	String cargo = "Diretor";

	int agencia;

	public Diretor(String nome, String cPF, String senha, String tipoPessoa, String tipoConta, int agencia) {
		super(nome, cPF, senha, tipoPessoa, tipoConta);
		this.agencia = agencia;
	}

	@Override
	public String toString() {
		return "Diretor [" + (cargo != null ? "cargo=" + cargo : "") + "]";
	}

}
