package DAO;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import Clases.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class TDSUsuarioDAO implements UsuarioDAO {

	//private static final String USUARIO = "Usuario";

	private static final String NOMBRE = "nombre";
	private static final String TELEFONO = "telefono";
	private static final String PASSWORD = "password";
	//private static final ImageIcon FOTO = new ImageIcon();
	private static final String PREMIUM = "premium";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";

	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	}

	private Usuario entidadToUsuario(Entidad eUsuario) {

		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String telefono = servPersistencia.recuperarPropiedadEntidad(eUsuario, TELEFONO);
		String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
		//ImageIcon foto = servPersistencia.recuperarPropiedadEntidad(eUsuario, FOTO);	NO PUEDE GUARDAR FOTOS DE PERFIL CREO
		String premium = servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM);
		String fechaNacimiento = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
		
		Usuario usuario = new Usuario(nombre, telefono, password, fechaNacimiento);
		usuario.setId(eUsuario.getId());

		return usuario;
	}

	private Entidad usuarioToEntidad(Usuario usuario) {
		Entidad eUsuario = new Entidad();
		eUsuario.setNombre(NOMBRE);

		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(
						new Propiedad(NOMBRE, usuario.getNombre()),
						new Propiedad(TELEFONO, usuario.getTelefono()), 
						new Propiedad(PASSWORD, usuario.getContraseña()),
						new Propiedad(PREMIUM, usuario.getPremiumString()),
						new Propiedad(FECHA_NACIMIENTO, usuario.getFechaNacimiento()))));
		return eUsuario;
	}

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		return servPersistencia.borrarEntidad(eUsuario);
	}

	/**
	 * Permite que un Usuario modifique su perfil: password y email
	 */
	public void update(Usuario usuario) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(PASSWORD)) {
				prop.setValor(usuario.getContraseña());
			} else if (prop.getNombre().equals(TELEFONO)) {
				prop.setValor(usuario.getTelefono());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(usuario.getNombre());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(NOMBRE);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}

}
