package Clases;

import java.util.LinkedList;

public class Contacto {

	private  String nombre;
	private String telefono;
	private LinkedList<String> mensajes;
	
	public Contacto(String nombre, String telefono) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.mensajes = new LinkedList<String>();
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getTelefono() {
		return telefono;
	}
	
	
	
	
}
