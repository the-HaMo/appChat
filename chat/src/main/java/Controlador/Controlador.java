package Controlador;

import DAO.UsuarioDAO;
import DAO.ContactoIndividualDAO;
import DAO.DAOException;
import DAO.FactoriaDAO;
import DAO.GrupoDAO;
import Clases.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import Clases.Contacto;
import Clases.ContactoIndividual;
import Clases.Grupo;
import Clases.Mensaje;
import Clases.RepositorioUsuarios;

public enum Controlador {
	INSTANCE;
	private Usuario usuarioActual;
	private FactoriaDAO factoria;
	private RepositorioUsuarios repositorioUsuarios;

	private Controlador() {
		usuarioActual = null;
		repositorioUsuarios = RepositorioUsuarios.INSTANCE;
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
	    return repositorioUsuarios.findUsuario(telf) != null;
	}

	public boolean loginUsuario(String telefono, String password) {
		Usuario usuario = repositorioUsuarios.findUsuario(telefono);
		
		if (usuario != null && usuario.getContraseña().equals(password)) {
			this.usuarioActual = usuario;
			return true;
		}
		return false;
	}
	
	

	public boolean registrarUsuario(String nombre, String telefono, String contraseña, String link, String fechaNacimientoStr, String saludo) {
        if (esUsuarioRegistrado(telefono)) {
            return false;
        } else {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimiento = null;
            try {
                fechaNacimiento = dateFormat.parse(fechaNacimientoStr);
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
            Usuario usuario = new Usuario(nombre, telefono, contraseña, link, fechaNacimiento, saludo);
            repositorioUsuarios.addUsuario(usuario);
            UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Adaptador DAO para almacenar el nuevo Usuario en la BD
            usuarioDAO.create(usuario);
            return true;
        }
    }

	public ContactoIndividual crearContacto(String nombre, String telefono) {
	    if (usuarioActual.contieneContacto(telefono)){
	        return null;
	    }
	    if (repositorioUsuarios.findUsuario(telefono) == null) {//No existe Usuario
	        return null;
	    }
		if (usuarioActual.getTelefono().equals(telefono)) {
			return null;
		}
	    ContactoIndividual contacto = new ContactoIndividual(nombre, telefono, repositorioUsuarios.findUsuario(telefono));
	    usuarioActual.addContacto(contacto);
	    ContactoIndividualDAO contactoDAO = factoria.getContactoDAO(); // Adaptador DAO para almacenar el nuevo Contacto en la BD
	    contactoDAO.create(contacto);
	    UsuarioDAO usuarioDAO = factoria.getUsuarioDAO(); // Habra que hacer que se actualice los contactos del usuario
	    usuarioDAO.update(usuarioActual);
	    return contacto;
	}

	
	public List<Contacto> getContactosUsuarioActual() {
		if (usuarioActual == null) {
			return new LinkedList<Contacto>();
		}
		Usuario u = repositorioUsuarios.findUsuario(usuarioActual.getTelefono());
		return new LinkedList<Contacto>(u.getListaContactos());
	}
	
	 
	public List<Mensaje> getMensajesDeContacto(Contacto contacto) {
		if (usuarioActual == null) {
			return new LinkedList<>();
		}
		List<Mensaje> mensajes = contacto.getMensajes().stream()	//Filtra los mensajes del contacto donde el emisor o receptor son o bien el usuario actual o el contacto
				.filter(m -> (m.getEmisor().equals(usuarioActual) || m.getReceptor().equals(usuarioActual))
						&&(m.getEmisor().equals(contacto) || m.getReceptor().equals(contacto)))
				.sorted(Comparator.comparing(Mensaje::getHora))
				.collect(Collectors.toList());
		// Ordenar los mensajes por fecha y hora
		return mensajes;
	}
	
	public Mensaje getUltimoMensaje(Contacto c) {
		List<Mensaje> mensajes = getMensajesDeContacto(c);
		if (mensajes.isEmpty())
			return null;
		return mensajes.get(mensajes.size() - 1);
		}
	
	
	public Grupo crearGrupo(String nombre, List<ContactoIndividual> participantes) {
		if (usuarioActual.perteneceGrupo(nombre)) {
			return null;
		}

		Grupo g = new Grupo(nombre, new LinkedList<Mensaje>(), participantes);

		// Se añade el grupo al usuario actual y al resto de participantes
		usuarioActual.addContacto(g);

		// Conexion con persistencia
		GrupoDAO adaptadorGrupo = factoria.getGrupoDAO();
		UsuarioDAO adaptadorUsu = factoria.getUsuarioDAO();
		adaptadorGrupo.create(g);
		adaptadorUsu.update(usuarioActual);
		participantes.stream()
		.forEach(p -> {
			Usuario usuario = p.getUsuario();
			adaptadorUsu.update(usuario);
		});

		return g;
	}
	}
	

