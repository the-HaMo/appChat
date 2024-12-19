package Controlador;

import DAO.UsuarioDAO;
import DAO.ContactoIndividualDAO;
import DAO.DAOException;
import DAO.FactoriaDAO;
import DAO.TDSContactoIndividualDAO;
import DAO.TDSUsuarioDAO;
import Clases.Usuario;
import Clases.ContactoIndividual;
import Clases.RepositorioUsuarios;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDAO factoria;

	private Controlador() {
		usuarioActual = null;
		try {
			factoria = FactoriaDAO.getInstancia();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public Usuario getUsuarioActual() {
		return usuarioActual;
	}

	public boolean esUsuarioRegistrado(String telf) {
		return RepositorioUsuarios.INSTANCE.findUsuario(telf) != null;
	}

	public boolean loginUsuario(String telefono, String password) {
		Usuario usuario = RepositorioUsuarios.INSTANCE.findUsuario(telefono);
		if (usuario != null && usuario.getContraseña().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}

	public boolean registrarUsuario(String nombre, String telefono, String password, String fechaNacimiento) {
	    if (esUsuarioRegistrado(telefono)) {
	        return false;
	    }else {
	    	Usuario usuario = new Usuario(nombre, telefono, password, fechaNacimiento);
		    RepositorioUsuarios.INSTANCE.addUsuario(usuario);
		    UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Adaptador DAO para almacenar el nuevo Usuario en la BD
		    usuarioDAO.create(usuario);
		    return true;
	    }
	    
	}

	public ContactoIndividual crearContacto(String nombre, String telefono) { // no se guardaran aun en la BD del usuario
	    if (usuarioActual.contieneContacto(telefono)) {
	        return null;
	    }
	    if (RepositorioUsuarios.INSTANCE.findUsuario(telefono) != null) {
	        return null;
	    }
	    ContactoIndividual contacto = new ContactoIndividual(nombre, telefono, usuarioActual);
	    usuarioActual.addContacto(contacto);
	    ContactoIndividualDAO contactoDAO = factoria.getContactoDAO(); // Adaptador DAO para almacenar el nuevo Contacto en la BD
	    contactoDAO.register(contacto);
	    UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Habra que hacer que se actualice los contactos del usuario
	    usuarioDAO.update(usuarioActual);
	    return contacto;
	}

}
