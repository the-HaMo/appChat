package Clases;

import java.util.List;


public class Grupo extends Contacto{

	private List<ContactoIndividual> lista;
	private Usuario admin;


	public Grupo(String nombre, List<ContactoIndividual> contactos, Usuario admin) {
		super(nombre);
		this.lista = contactos;
		this.admin = admin;
	}
	
	public Grupo(String nombre, List<Mensaje> mensajes, List<ContactoIndividual> contactos, Usuario admin) {
		super(nombre, null,mensajes);
		this.lista = contactos;
		this.admin = admin;
	}

	public void addContacto(ContactoIndividual contacto) {
		if(!lista.contains(contacto)) {
			lista.add(contacto);
		}

	}

	public List<ContactoIndividual> getContactos() {
		return lista;
	}


	public Usuario getAdmin() {
		return admin;
	}
	
	public void setAdmin(Usuario admin) {
		this.admin = admin;
	}

	
	public void addMensaje(Mensaje mensaje) {
		for (ContactoIndividual contacto : lista) {
			contacto.addMensaje(mensaje);
		}
	}




}