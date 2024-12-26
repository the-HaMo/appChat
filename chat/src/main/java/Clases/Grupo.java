package Clases;

import java.util.List;


public class Grupo extends Contacto{

	private List<ContactoIndividual> lista;


	public Grupo(String nombre, List<ContactoIndividual> contactos) {
		super(nombre);
		this.lista = contactos;
	}
	
	public Grupo(String nombre, List<Mensaje> mensajes, List<ContactoIndividual> contactos) {
		super(nombre, null,mensajes);
		this.lista = contactos;
	}

	public void addContacto(ContactoIndividual contacto) {
		if(!lista.contains(contacto)) {
			lista.add(contacto);
		}

	}

	public List<ContactoIndividual> getContactos() {
		return lista;
	}

	
	public void addMensaje(Mensaje mensaje) {
		for (ContactoIndividual contacto : lista) {
			contacto.addMensaje(mensaje);
		}
	}




}