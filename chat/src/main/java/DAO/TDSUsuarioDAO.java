package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import Clases.Contacto;
import Clases.ContactoIndividual;
import Clases.Usuario;
import beans.Entidad;
import beans.Propiedad;

/**
 * 
 * Clase que implementa el Adaptador DAO concreto de Usuario para el tipo H2.
 * 
 */
public final class TDSUsuarioDAO implements UsuarioDAO {

	private static final String USUARIO = "Usuario";
	private static final String NOMBRE = "nombre";
	private static final String TELEFONO = "telefono";
	private static final String PASSWORD = "password";
	private static final String FOTO = "foto";
	private static final String SALUDO = "saludo";
	private static final String PREMIUM = "premium";
	private static final String FECHA_NACIMIENTO = "fechaNacimiento";
	private static final String CONTACTOS = "contactos";

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
        
        List<ContactoIndividual> contactos = codigosAContactos(servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTACTOS));

		for (ContactoIndividual c : contactos)
			usuario.addContacto(c);

        return usuario;
    }

    private Entidad usuarioToEntidad(Usuario usuario) {
        Entidad eUsuario = new Entidad();
        eUsuario.setNombre(USUARIO);

        eUsuario.setPropiedades(new ArrayList<Propiedad>(
                Arrays.asList(
                        new Propiedad(NOMBRE, usuario.getNombre()),
                        new Propiedad(TELEFONO, usuario.getTelefono()),
                        new Propiedad(PASSWORD, usuario.getContraseña()),
                        new Propiedad(FOTO, usuario.getLink()),
                        new Propiedad(PREMIUM, usuario.getPremiumString()),
                        new Propiedad(FECHA_NACIMIENTO, dateFormat.format(usuario.getFechaNacimiento())),
                        new Propiedad(SALUDO, usuario.getSaludo()),
        				new Propiedad(CONTACTOS, ContactoACodigo(usuario.getListaContactos())))));
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


public void update(Usuario usuario) {
    Entidad eUsuario = servPersistencia.recuperarEntidad(usuario.getId());

    // Remove existing properties
    servPersistencia.eliminarPropiedadEntidad(eUsuario, NOMBRE);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, TELEFONO);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, PASSWORD);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, FOTO);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, SALUDO);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, PREMIUM);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
    servPersistencia.eliminarPropiedadEntidad(eUsuario, CONTACTOS);

    // Add updated properties
    servPersistencia.anadirPropiedadEntidad(eUsuario, NOMBRE, usuario.getNombre());
    servPersistencia.anadirPropiedadEntidad(eUsuario, TELEFONO, usuario.getTelefono());
    servPersistencia.anadirPropiedadEntidad(eUsuario, PASSWORD, usuario.getContraseña());
    servPersistencia.anadirPropiedadEntidad(eUsuario, FOTO, usuario.getLink());
    servPersistencia.anadirPropiedadEntidad(eUsuario, SALUDO, usuario.getSaludo());
    servPersistencia.anadirPropiedadEntidad(eUsuario, PREMIUM, usuario.getPremiumString());
    servPersistencia.anadirPropiedadEntidad(eUsuario, FECHA_NACIMIENTO, dateFormat.format(usuario.getFechaNacimiento()));
    servPersistencia.anadirPropiedadEntidad(eUsuario, CONTACTOS, ContactoACodigo(usuario.getListaContactos()));
}

	public Usuario get(int id) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(id);

		return entidadToUsuario(eUsuario);
	}

	public List<Usuario> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);

		List<Usuario> usuarios = new LinkedList<Usuario>();
		for (Entidad eUsuario : entidades) {
			usuarios.add(get(eUsuario.getId()));
		}

		return usuarios;
	}
	
	private List<ContactoIndividual> codigosAContactos(String ids) {
		List<ContactoIndividual> contactos = new LinkedList<>();
		StringTokenizer str = new StringTokenizer(ids, " ");
		TDSContactoIndividualDAO adaptadorC = TDSContactoIndividualDAO.getInstancia();
		while (str.hasMoreTokens()) {
			contactos.add(adaptadorC.get(Integer.valueOf((String) str.nextElement())));
		}
		return contactos;
	}
	
	private String ContactoACodigo(List<Contacto> contactos) {
	    if (contactos == null || contactos.isEmpty()) {
	        return "";
	    }
	    return contactos.stream()
	        .filter(c -> c instanceof ContactoIndividual) // me quedo con los contactos individuales
	        .map(c -> String.valueOf(c.getId()))
	        .reduce("", (x, y) -> x + y + " ") // concateno todos
	        .trim();
	}

}
