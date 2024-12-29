package Clases;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;



public class ContactoIndividual extends Contacto {

	private Usuario usu;	//Usuario que es el contacto

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
	
	
	public ContactoIndividual getContacto(Usuario usuario) {
		return this.usu.getListaContactos().stream()
				.filter(c -> c instanceof ContactoIndividual)
				.map(c -> (ContactoIndividual) c)
				.filter(c -> c.getUsuario().equals(usuario))
				.findAny()
				.orElse(null);
	}

	public List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario) {
		ContactoIndividual contacto = getContacto(usuario.orElse(null));
		if (contacto != null) {
			return contacto.getMensajes();
		} else {
			return new LinkedList<>();
		}
	}
}

