package DAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import Clases.*;
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
	private static final String GRUPOS = "grupos";
	private static final String REGISTRO = "fechaRegistro";

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
        String registroStr = servPersistencia.recuperarPropiedadEntidad(eUsuario, REGISTRO);
        LocalDate registro=LocalDate.parse(registroStr);
        String fechaStr = servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO);
        Date fecha=null;
		try {
			fecha = dateFormat.parse(fechaStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Usuario usuario = new Usuario(nombre, telefono, password, foto, fecha, saludo, premium, registro);
        usuario.setId(eUsuario.getId());
        PoolDAO.getInstancia().addObjeto(usuario.getId(), usuario);
        
        List<ContactoIndividual> contactos = codigosAContactos(servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTACTOS));
        List<Grupo> grupos = codigosAGrupos(servPersistencia.recuperarPropiedadEntidad(eUsuario, GRUPOS));
		for (ContactoIndividual c : contactos)
			usuario.addContacto(c);
		for (Grupo g : grupos)
			usuario.addContacto(g);

		
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
                        new Propiedad(GRUPOS, GruposACodigo(usuario.getListaContactos())),
        				new Propiedad(CONTACTOS, ContactoACodigo(usuario.getListaContactos())),
        				new Propiedad(REGISTRO, usuario.getFechaRegistro().toString())
        		)));
        return eUsuario;
    }

	public void create(Usuario usuario) {
		Entidad eUsuario = this.usuarioToEntidad(usuario);
		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		usuario.setId(eUsuario.getId());
		PoolDAO.getInstancia().addObjeto(usuario.getId(), usuario);
	}

	public boolean delete(Usuario usuario) {
		Entidad eUsuario;
		eUsuario = servPersistencia.recuperarEntidad(usuario.getId());
		return servPersistencia.borrarEntidad(eUsuario);
	}


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
            prop.setValor(dateFormat.format(usuario.getFechaNacimiento()));
        } else if (prop.getNombre().equals(CONTACTOS)) {
            prop.setValor(ContactoACodigo(usuario.getListaContactos()));
        } else if (prop.getNombre().equals(GRUPOS)) {
            prop.setValor(GruposACodigo(usuario.getListaContactos()));
		} else if (prop.getNombre().equals(REGISTRO)) {
			prop.setValor(usuario.getFechaRegistro().toString());
		}
        servPersistencia.modificarPropiedad(prop);
    	}
    }

	public Usuario get(int id) {
		if(!PoolDAO.getInstancia().contiene(id)) {
			Entidad eUsuario = servPersistencia.recuperarEntidad(id);
			return entidadToUsuario(eUsuario);
		}else {
			return (Usuario) PoolDAO.getInstancia().getObjeto(id);
		}
            
		
	}

	public List<Usuario> getAll() {
	    List<Entidad> entidades = servPersistencia.recuperarEntidades(USUARIO);
	    List<Usuario> usuarios = new LinkedList<>();
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
	

	private List<Grupo> codigosAGrupos(String ids) {
		List<Grupo> grupos = new LinkedList<>();
		StringTokenizer str = new StringTokenizer(ids, " ");
		TDSGrupoDAO adaptadorG = TDSGrupoDAO.getInstancia();
		while (str.hasMoreTokens()) {
			grupos.add(adaptadorG.get(Integer.valueOf((String) str.nextElement())));
		}
		return grupos;
	}
	
	private String GruposACodigo(List<Contacto> contactos) {
	    if (contactos == null || contactos.isEmpty()) {
	        return "";
	    }
	    return contactos.stream()
	        .filter(c -> c instanceof Grupo) // me quedo con los contactos individuales
	        .map(c -> String.valueOf(c.getId()))
	        .reduce("", (x, y) -> x + y + " ") // concateno todos
	        .trim();
	}
}
