package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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


	private static final String NOMBRE = "nombre";
	private static final String TELEFONO = "telefono";
	private static final String PASSWORD = "password";
	private static final String FOTO = "foto";
	private static final String SALUDO = "saludo";
	private static final String PREMIUM = "premium";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";

	private ServicioPersistencia servPersistencia;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private static TDSUsuarioDAO unicaInstancia = null;

	public TDSUsuarioDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static TDSUsuarioDAO getInstancia() {
        if (unicaInstancia == null)
            unicaInstancia = new TDSUsuarioDAO();
        return unicaInstancia;
    }

	private Usuario entidadToUsuario(Entidad eUsuario) {
        String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
        String telefono = servPersistencia.recuperarPropiedadEntidad(eUsuario, TELEFONO);
        String password = servPersistencia.recuperarPropiedadEntidad(eUsuario, PASSWORD);
        String foto = servPersistencia.recuperarPropiedadEntidad(eUsuario, FOTO);
        String saludo = servPersistencia.recuperarPropiedadEntidad(eUsuario, SALUDO);
        Boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM));
        String fechaStr = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
        Date fecha=null;
		try {
			fecha = dateFormat.parse(fechaStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Usuario usuario = new Usuario(nombre, telefono, password, foto, fecha, saludo, premium);
        usuario.setId(eUsuario.getId());

        return usuario;
    }

    private Entidad usuarioToEntidad(Usuario usuario) {
        Entidad eUsuario = new Entidad();
        eUsuario.setNombre(usuario.getNombre());

        eUsuario.setPropiedades(new ArrayList<Propiedad>(
                Arrays.asList(
                        new Propiedad(NOMBRE, usuario.getNombre()),
                        new Propiedad(TELEFONO, usuario.getTelefono()),
                        new Propiedad(PASSWORD, usuario.getContraseña()),
                        new Propiedad(FOTO, usuario.getLink()),
                        new Propiedad(PREMIUM, usuario.getPremiumString()),
                        new Propiedad(FECHA_NACIMIENTO, dateFormat.format(usuario.getFechaNacimiento())),
                        new Propiedad(SALUDO, usuario.getSaludo()))));
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
	        if (prop.getNombre().equals(NOMBRE)) {
	            prop.setValor(usuario.getNombre());
	        } else if (prop.getNombre().equals(TELEFONO)) {
	            prop.setValor(usuario.getTelefono());
	        } else if (prop.getNombre().equals(PASSWORD)) {
	            prop.setValor(usuario.getContraseña());
	        } else if (prop.getNombre().equals(FOTO)) {
	            prop.setValor(usuario.getLink());
	        } else if (prop.getNombre().equals(SALUDO)) {
	            prop.setValor(usuario.getSaludo());
	        } else if (prop.getNombre().equals(PREMIUM)) {
	            prop.setValor(usuario.getPremiumString());
	        } else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
	            prop.setValor(usuario.getFechaNacimiento().toString());
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
