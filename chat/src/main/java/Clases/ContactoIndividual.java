package Clases;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import Clases.*;


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
	
	public ContactoIndividual getContacto(Usuario usuario) {// de la lista que entra, se coge aquel contacto es igual que objeto actual
		return usu.getListaContactos().stream()
				.filter(c -> c instanceof ContactoIndividual)
				.map(c -> (ContactoIndividual) c)
				.filter(c -> c.getUsuario().equals(usuario))
				.findAny().orElse(null);
	}
	
	public List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario) {
		ContactoIndividual c = getContacto(usuario.orElse(null));
		if (c != null) {
			return c.getMensajes();
		} else
			return new LinkedList<>();
	}

}
