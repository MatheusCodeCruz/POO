package br.com.residencia.poo.contas;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Data {
	public static String Data() {
		DateFormat dateFormat = new SimpleDateFormat ("dd/MM/yyyy HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		return(dateFormat.format(cal.getTime()));
	}
}
