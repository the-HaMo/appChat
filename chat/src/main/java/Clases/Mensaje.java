package Clases;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje {

	private String texto;
	private String tipo;	//si es recibido o enviado
	private String hora;
	
	public static String onlyHourNow(LocalDateTime hora) {
		DateTimeFormatter soloHora = DateTimeFormatter.ofPattern("HH:mm");
		return hora.format(soloHora);
		
	}

	public Mensaje(String texto, String tipo) {
		this.texto = texto;
		this.tipo = tipo;
		this.hora = onlyHourNow(LocalDateTime.now());
	}
}
