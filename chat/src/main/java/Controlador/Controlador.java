package Controlador;

import DAO.UsuarioDAO;

import java.io.FileOutputStream;
import java.io.IOException;
import DAO.ContactoIndividualDAO;
import DAO.DAOException;
import DAO.FactoriaDAO;
import DAO.GrupoDAO;
import DAO.MensajeDAO;
import Clases.Usuario;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Aplicacion.Login;
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
		ContactoIndividual contacto = new ContactoIndividual(nombre, telefono, repositorioUsuarios.findUsuario(telefono));
	    if (usuarioActual.contieneContacto(contacto)){
	        return null;
	    }
	    if (repositorioUsuarios.findUsuario(telefono) == null) {//No existe Usuario
	        return null;
	    }
		if (usuarioActual.getTelefono().equals(telefono)) {
			return null;
		}
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
		return usuarioActual.getListaContactos();
	}
	
	 
	public List<Mensaje> getMensajesDeContacto(Contacto contacto) {//todos los mensajes con un contacto
		if (usuarioActual == null) {
			return new LinkedList<>();
		}
		if (contacto instanceof Grupo) {
			return contacto.getMensajes().stream()
					.filter(c -> c.getEmisor().equals(usuarioActual))
					.sorted()
					.collect(Collectors.toList());
		}
		return Stream.concat(contacto.getMensajes().stream(),
				contacto.getMensajesRecibidos(Optional.of(usuarioActual)).stream())
				.sorted()
				.collect(Collectors.toList());
		// Ordenar los mensajes por fecha y hora
		
	}
	
	public Mensaje getUltimoMensaje(Contacto c) {
		List<Mensaje> mensajes = getMensajesDeContacto(c);
		if (mensajes.isEmpty())
			return null;
		return mensajes.get(mensajes.size() - 1);
		}
	
	
	public Grupo crearGrupo(String nombre, List<ContactoIndividual> participantes, String link) {
		if (usuarioActual.perteneceGrupo(nombre)) {
			return null;
		}

		Grupo g = new Grupo(nombre, new LinkedList<Mensaje>(), participantes, link);

		// Se añade el grupo al usuario actual y al resto de participantes
		usuarioActual.addContacto(g);
		// Conexion con persistencia
		GrupoDAO adaptadorGrupo = factoria.getGrupoDAO();
		UsuarioDAO adaptadorUsu = factoria.getUsuarioDAO();
		adaptadorGrupo.create(g);
		adaptadorUsu.update(usuarioActual);

		return g;
	}

	public void logout() {
		usuarioActual = null;
		Login login = new Login();
    	login.Mostrar();
		}
	
	public void enviarMensaje(Contacto contacto, String texto) {

		MensajeDAO adaptadorMensaje = factoria.getMensajeDAO();
		ContactoIndividualDAO adaptadorContactoIndividual = factoria.getContactoDAO();
		GrupoDAO adaptadorGrupo = factoria.getGrupoDAO();
		Mensaje mensaje = new Mensaje(texto,LocalDateTime.now(), usuarioActual, contacto);


		if (contacto instanceof ContactoIndividual) {
			contacto.addMensaje(mensaje);
			adaptadorMensaje.create(mensaje);
			adaptadorContactoIndividual.update((ContactoIndividual) contacto);
		} else {
			Grupo grupo = (Grupo) contacto;
			for (ContactoIndividual c : grupo.getContactos()) {
				Mensaje m = new Mensaje(texto, LocalDateTime.now(), usuarioActual, c);
	            c.addMensaje(m);
	            adaptadorMensaje.create(m);
	            adaptadorContactoIndividual.update(c);
			}
			contacto.addMensaje(mensaje);
			adaptadorGrupo.update(grupo);
		}
	}
	
	public void enviarMensaje(Contacto contacto, int emoji) {

		MensajeDAO adaptadorMensaje = factoria.getMensajeDAO();
		ContactoIndividualDAO adaptadorContactoIndividual = factoria.getContactoDAO();
		GrupoDAO adaptadorGrupo = factoria.getGrupoDAO();
		Mensaje mensaje = new Mensaje(emoji,LocalDateTime.now(), usuarioActual, contacto);


		if (contacto instanceof ContactoIndividual) {
			contacto.addMensaje(mensaje);
			adaptadorMensaje.create(mensaje);
			adaptadorContactoIndividual.update((ContactoIndividual) contacto);
		} else {
			Grupo grupo = (Grupo) contacto;
			for (ContactoIndividual c : grupo.getContactos()) {
				Mensaje m = new Mensaje(emoji, LocalDateTime.now(), usuarioActual, c);
	            c.addMensaje(m);
	            adaptadorMensaje.create(m);
	            adaptadorContactoIndividual.update(c);
			}
			contacto.addMensaje(mensaje);
			adaptadorGrupo.update(grupo);
		}
	}
	
	
	public List<Mensaje> getMensajesReceptorUsuarioActual() {
		MensajeDAO adaptadorMensaje = factoria.getMensajeDAO();
		List<Mensaje> mensajes = adaptadorMensaje.getAll();
		return mensajes.stream()
				.filter(m -> m.getReceptor().getTelefono().equals(usuarioActual.getTelefono()))
				.filter(m -> !(usuarioActual.contieneTelf(m.getEmisor().getTelefono())))
				.sorted(Comparator.comparing(Mensaje::getHora))
				.collect(Collectors.toList());

	}
	public List<Grupo> getGruposUsuarioActual() {
	    if (usuarioActual == null) {
	        return new LinkedList<>(); 
	    }
	    return usuarioActual.getListaContactos().stream()
	            .filter(contacto -> contacto instanceof Grupo)
	            .map(contacto -> (Grupo) contacto)
	            .collect(Collectors.toList());
	}
	public List<Mensaje> getMensajesUsuario() {
		MensajeDAO adaptadorMensaje = factoria.getMensajeDAO();
		List<Mensaje> mensajes = adaptadorMensaje.getAll();
		return mensajes.stream()
				.filter(m -> (m.getReceptor().getTelefono().equals(usuarioActual.getTelefono())||(m.getEmisor().equals(usuarioActual))))
				.sorted(Comparator.comparing(Mensaje::getHora))
				.collect(Collectors.toList());
	}	
	
	
	public void editarGrupo(String nombreGrupo, List<ContactoIndividual> contactos, String foto) {
	    // Buscar el grupo por su nombre
	    Grupo grupo = usuarioActual.getListaContactos().stream()
	            .filter(c -> c instanceof Grupo && c.getNombre().equals(nombreGrupo))
	            .map(c -> (Grupo) c)
	            .findFirst()
	            .orElse(null);
	    
	    if (grupo != null) {
	        grupo.clearContactos();
	        grupo.setNombre(nombreGrupo);
	        // grupo.setPhoto
	        for (ContactoIndividual contacto : contactos) {
	        	grupo.addContacto(contacto);
	        }
	        GrupoDAO grupoDao = factoria.getGrupoDAO();
	        UsuarioDAO adaptadorUsu = factoria.getUsuarioDAO();
	        grupoDao.update(grupo);
	        adaptadorUsu.update(usuarioActual);
	    }
	}
	
	public long getNumMensajesEnviados() {
		MensajeDAO adaptadorMensaje = factoria.getMensajeDAO();
		long contar= adaptadorMensaje.getAll().stream()
				.filter(m -> m.getEmisor().equals(usuarioActual))
				.count();
		return contar;
	}
	
	
	public double getPrecio() {
		return usuarioActual.getPrecioPremium();
	}
	
	public void hacerPremium() {
		usuarioActual.setPremium(true);
		UsuarioDAO adaptadorUsu = factoria.getUsuarioDAO();
		adaptadorUsu.update(usuarioActual);
	}
	

	public void deshacerPremium() {
		usuarioActual.setPremium(false);
		UsuarioDAO adaptadorUsu = factoria.getUsuarioDAO();
		adaptadorUsu.update(usuarioActual);
	}
	
	public boolean generarPDF(String filePath) {
	    // Lógica para generar el PDF en la ruta especificada
	    // Puedes usar una biblioteca como iText para crear el PDF
	    /*try {
	        Document document = new Document();
	        PdfWriter.getInstance(document, new FileOutputStream(filePath));
	        document.open();
	        document.add(new Paragraph("Contenido del PDF"));
	        document.close();
	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }*/
		return false;
	}

	public int getEmojiID(int id) {
		return id;
	}

	
}


















