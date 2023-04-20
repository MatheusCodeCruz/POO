package br.com.residencia.poo.pessoas;

public class Cliente extends Pessoa {

	public Cliente(String nome, String cPF, String senha, String tipoPessoa, String tipoConta) {
		super(nome, cPF, senha, tipoPessoa, tipoConta);

	}

	@Override
	public String toString() {
		return "Cliente [" + (tipoPessoa != null ? "tipoPessoa=" + tipoPessoa + ", " : "") + "CPF=" + CPF
				+ ", senha=" + senha + ", " + (nome != null ? "nome=" + nome + ", " : "")
				+ (tipoConta != null ? "tipoConta=" + tipoConta : "") + "]";
	}

}
