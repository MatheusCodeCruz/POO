package br.com.residencia.poo.pessoas;

public class Gerente extends Funcionario{

	String cargo = "Gerente";
	int agencia;

	public Gerente(String nome, String cPF, String senha, String tipoPessoa, String tipoConta, int agencia) {
		super(nome, cPF, senha, tipoPessoa, tipoConta);
		this.agencia = agencia;
	}

	@Override
	public String toString() {
		return "[Cargo = " + cargo + ", CPF = " + CPF + ", nome = " + nome + ", agencia=" + agencia + "]";
	}
		
	}
