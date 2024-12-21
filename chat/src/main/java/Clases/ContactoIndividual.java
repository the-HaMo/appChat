package Clases;

import java.util.List;


public class ContactoIndividual extends Contacto {

	private Usuario usu;
	public ContactoIndividual(String nombre, String telefono,Usuario usu) {
		super(nombre, telefono);
		this.usu = usu;
	}
	
	public ContactoIndividual(String nombre, String telefono,Usuario usu, List<Mensaje> mensajes) {
		super(nombre, telefono,mensajes);
		this.usu = usu;
	}


	public Usuario getUsuario() {
		return usu;
	}
	
	public void setUsuario(Usuario u) {
		this.usu = u;
	}
	
	public String getFoto() {
		return usu.getLink();
	}
	

	
	public boolean isUsuario(Usuario u) {
		return usu.equals(u);
	}
}
