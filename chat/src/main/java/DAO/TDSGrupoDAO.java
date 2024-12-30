package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import Clases.*;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;

public class TDSGrupoDAO implements GrupoDAO{

	private static final String NOMBRE = "nombre";
	private static final String CONTACTOS = "contactos";
	private static final String MENSAJES = "mensajes";
	private static final String FOTO = "foto";
	private static final String GRUPO = "grupo";

	private static ServicioPersistencia servPersistencia;
	private static TDSGrupoDAO unicaInstancia = null;

	public TDSGrupoDAO() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}

	public static TDSGrupoDAO getInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new TDSGrupoDAO();
		}
		return unicaInstancia;
	}

	private Grupo entidadToGrupo(Entidad eGrupo) {
		String nombre = servPersistencia.recuperarPropiedadEntidad(eGrupo, NOMBRE);
		List<Mensaje> mensajes = MensajesDesdeID(servPersistencia.recuperarPropiedadEntidad(eGrupo, MENSAJES));
		List<ContactoIndividual> contactos = codigosAContactos(servPersistencia.recuperarPropiedadEntidad(eGrupo, CONTACTOS));
		String foto = servPersistencia.recuperarPropiedadEntidad(eGrupo, FOTO);
		return new Grupo(nombre,mensajes, contactos,foto);
	}

	private Entidad grupoToEntidad(Grupo grupo) {
		Entidad eGrupo = new Entidad();
		eGrupo.setNombre(GRUPO);

		eGrupo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(
						new Propiedad(NOMBRE, grupo.getNombre()),
						new Propiedad(MENSAJES, CodigoMensajes(grupo.getMensajes())),
						new Propiedad(FOTO, grupo.getLink()),
						new Propiedad(CONTACTOS, ContactoACodigo(grupo.getContactos())))));
		return eGrupo;
	}



	@Override
	public void create(Grupo grupo) {
		Entidad eGrupo = this.grupoToEntidad(grupo);
		eGrupo = servPersistencia.registrarEntidad(eGrupo);
		grupo.setId(eGrupo.getId());
	}

	@Override
	public void delete(Grupo grupo) {
		Entidad eGrupo= servPersistencia.recuperarEntidad(grupo.getId());
		servPersistencia.borrarEntidad(eGrupo);
	}

	@Override
	public void update(Grupo grupo) {
		Entidad eGrupo = servPersistencia.recuperarEntidad(grupo.getId());
		
		for (Propiedad prop : eGrupo.getPropiedades()) {
	        if (prop.getNombre().equals(NOMBRE)) {
	            prop.setValor(grupo.getNombre());
	        } else if (prop.getNombre().equals(CONTACTOS)) {
	            prop.setValor(ContactoACodigo(grupo.getContactos()));
	        } else if (prop.getNombre().equals(MENSAJES)) {
	            prop.setValor(CodigoMensajes(grupo.getMensajes()));
			} else if (prop.getNombre().equals(FOTO)) {
				prop.setValor(grupo.getLink());
			}
	        servPersistencia.modificarPropiedad(prop);
	    	}
	}


	@Override
	public Grupo get(int id) {
		Entidad eGrupo = servPersistencia.recuperarEntidad(id);
		return entidadToGrupo(eGrupo);
	}

	@Override
	public List<Grupo> getAll() {

		List<Entidad> entidades = servPersistencia.recuperarEntidades(GRUPO);
		List<Grupo> grupos = new LinkedList<Grupo>();
		for (Entidad eGrupo : entidades) {
			grupos.add(get(eGrupo.getId()));
		}
		return grupos;
	}

	private List<Mensaje> MensajesDesdeID(String idMensajes) {
	    List<Mensaje> mensajes = new LinkedList<>();
	    StringTokenizer token = new StringTokenizer(idMensajes, " ");
	    TDSMensajeDAO mensajeDAO = TDSMensajeDAO.getInstancia();
	    while (token.hasMoreTokens()) {
	        String id = token.nextToken();
	        mensajes.add(mensajeDAO.get(Integer.valueOf(id)));
	    }
	    return mensajes;
	}

	private String CodigoMensajes(List<Mensaje> mensajes) {
		return mensajes.stream()
				.map(m -> String.valueOf(m.getId()))
				.reduce("", (x, y) -> x + y + " ")
				.trim();
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

	private String ContactoACodigo(List<ContactoIndividual> contactos) {
		if (contactos == null || contactos.isEmpty()) {
			return "";
		}
		return contactos.stream()
				.map(c -> String.valueOf(c.getId()))
				.reduce("", (x, y) -> x + y + " ") // concateno todos
				.trim();
	}


}
