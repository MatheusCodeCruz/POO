package br.com.residencia.poo.contas;

public class SeguroVida {

	public static double Seguro(double valorSeguro) {
		double porcent = 0.2;
		double tributo = valorSeguro * porcent;
		return tributo;
	}
	
}
