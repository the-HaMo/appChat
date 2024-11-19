package Controlador;

import DAO.UsuarioDAO;
import DAO.DAOException;
import DAO.FactoriaDAO;
import Clases.Usuario;
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
	    }
	    Usuario usuario = new Usuario(nombre, telefono, password, fechaNacimiento);
	    RepositorioUsuarios.INSTANCE.addUsuario(usuario);
	    UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Adaptador DAO para almacenar el nuevo Usuario en la BD
	    usuarioDAO.create(usuario);
	    return true;
	}
/*
	public boolean borrarUsuario(Usuario usuario) {
		if (!esUsuarioRegistrado(usuario.getLogin()))
			return false;

		UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Adaptador DAO para borrar el Usuario de la BD 
		usuarioDAO.delete(usuario);

		RepositorioUsuarios.INSTANCE.removeUsuario(usuario);
		return true;
	} */
}
