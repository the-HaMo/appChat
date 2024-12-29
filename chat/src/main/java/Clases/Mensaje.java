package Clases;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Mensaje implements Comparable<Mensaje> {

	private int id;
	private String texto;
	private String hora;
	private int emoticono;
	private Usuario emisor;
	private Contacto receptor;
	
	public static String onlyHourNow(LocalDateTime hora) {
		DateTimeFormatter soloHora = DateTimeFormatter.ofPattern("HH:mm");
		return hora.format(soloHora);
		
	}
//Mensaje de texto
	public Mensaje(String texto, LocalDateTime hora, Usuario emisor, Contacto receptor) {

		this.texto = texto;
		this.hora = onlyHourNow(hora);
		this.emisor = emisor;
		this.receptor = receptor;
	}
//Mensaje Emoji
	public Mensaje(LocalDateTime hora, int emoticono, Usuario emisor, Contacto receptor) {

		this.hora = onlyHourNow(hora);
		this.emoticono = emoticono;
		this.emisor = emisor;
		this.receptor = receptor;
	}
	//perssitencia sacar los emisores y receoptores
	public Mensaje(LocalDateTime hora, int emoticono, String texto) {

		this.hora = onlyHourNow(hora);
		this.emoticono = emoticono;
		this.texto = texto;
	}
	public String getTexto() {
		return texto;
	}

	public int getEmoji() {
        return emoticono;
    }
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public int getEmoticono() {
		return emoticono;
	}
	public void setEmoticono(int emoticono) {
		this.emoticono = emoticono;
	}
	public Usuario getEmisor() {
		return emisor;
	}
	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}
	public Contacto getReceptor() {
		return receptor;
	}
	public void setReceptor(Contacto receptor) {
		this.receptor = receptor;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	@Override
	public int compareTo(Mensaje o) {
			return this.hora.compareTo(o.hora);
	}
	
	
	
	

	
}
