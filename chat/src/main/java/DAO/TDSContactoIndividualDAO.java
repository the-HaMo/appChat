
package DAO;

import java.util.ArrayList;
import java.util.Arrays;
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
public final class TDSContactoIndividualDAO implements ContactoIndividualDAO {
	
    private static final String NOMBRE = "nombre";
    private static final String TELEFONO = "telefono";
    private static final String USUARIO = "usuario";
    private static final String MENSAJES = "mensajes";
	
	
    private static ServicioPersistencia servPersistencia;
    private static TDSContactoIndividualDAO unicaInstancia = null;

    public TDSContactoIndividualDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }
    
    public static TDSContactoIndividualDAO getInstancia() {
        if (unicaInstancia == null) {
            unicaInstancia = new TDSContactoIndividualDAO();
        }
        return unicaInstancia;
    }

    private Entidad contactoToEntidad(ContactoIndividual contacto) {
        Entidad eContacto = new Entidad();
        boolean guardado;

        try {
            eContacto = servPersistencia.recuperarEntidad(contacto.getId());
            guardado = true;
        } catch (NullPointerException e) {
            guardado = false;
        }

        if (guardado) {
            return eContacto;
        }

        eContacto.setNombre(contacto.getNombre());
        eContacto.setPropiedades(new ArrayList<Propiedad>(
            Arrays.asList(
                new Propiedad(NOMBRE, contacto.getNombre()),
                new Propiedad(TELEFONO, contacto.getTelefono()),
                new Propiedad(MENSAJES, CodigoMensajes(contacto.getMensajes())), // Assuming mensajes is a List<Mensaje>   HAY QUE PASARLO A STRING
                new Propiedad(USUARIO, String.valueOf(contacto.getUsuario().getId()))
            )
        ));

        return eContacto;
    }
    
	private ContactoIndividual entidadToContacto(Entidad eContacto) {
		String nombre = servPersistencia.recuperarPropiedadEntidad(eContacto, NOMBRE);
		String telefono = servPersistencia.recuperarPropiedadEntidad(eContacto, TELEFONO);
		ContactoIndividual contacto = new ContactoIndividual(nombre,telefono , null);
		contacto.setId(eContacto.getId());		
		// Mensajes que el contacto tiene
		List<Mensaje> mensajes = MensajesDesdeID(servPersistencia.recuperarPropiedadEntidad(eContacto, MENSAJES));
		for (Mensaje m : mensajes)
			contacto.addMensaje(m);

		// Obtener usuario del contacto
		contacto.setUsuario(getUsuario(servPersistencia.recuperarPropiedadEntidad(eContacto, USUARIO)));
		
		return contacto;
    }

    @Override
    public void delete(ContactoIndividual contacto) {
    	Entidad eContacto;
		TDSMensajeDAO mensajeDAO = TDSMensajeDAO.getInstancia();

		for (Mensaje mensaje : contacto.getMensajes()) {
			mensajeDAO.delete(mensaje);
		}

		eContacto = servPersistencia.recuperarEntidad(contacto.getId());
		servPersistencia.borrarEntidad(eContacto);
    }

    @Override
    public void update(ContactoIndividual contacto) {
        // TODO Auto-generated method stub
    	Entidad eContact = servPersistencia.recuperarEntidad(contacto.getId());

		servPersistencia.eliminarPropiedadEntidad(eContact, NOMBRE);
		servPersistencia.anadirPropiedadEntidad(eContact, NOMBRE, contacto.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eContact, TELEFONO);
		servPersistencia.anadirPropiedadEntidad(eContact, TELEFONO, String.valueOf(contacto.getTelefono()));
		servPersistencia.eliminarPropiedadEntidad(eContact, MENSAJES);
		servPersistencia.anadirPropiedadEntidad(eContact, MENSAJES,CodigoMensajes(contacto.getMensajes()));		
		servPersistencia.eliminarPropiedadEntidad(eContact, USUARIO);
		servPersistencia.anadirPropiedadEntidad(eContact, USUARIO, String.valueOf(contacto.getUsuario().getId()));
    }

    @Override
    public ContactoIndividual get(int id) {

    	Entidad eContacto = servPersistencia.recuperarEntidad(id);
        return entidadToContacto(eContacto);
    }
    
    

    
    @Override
    public List<ContactoIndividual> getAll() {
        LinkedList<ContactoIndividual> contactos = new LinkedList<>();
        List<Entidad> eContactos = servPersistencia.recuperarEntidades("contacto");

        for (Entidad eContacto : eContactos) {
            contactos.add(get(eContacto.getId()));
        }

        return contactos;
    }
    
    private Usuario getUsuario(String id) {
		TDSUsuarioDAO usuariosDAO = TDSUsuarioDAO.getInstancia();
		return usuariosDAO.get(Integer.valueOf(id));
	}


	@Override
	public void create(ContactoIndividual contacto) {
		Entidad eContacto = this.contactoToEntidad(contacto);
		eContacto = servPersistencia.registrarEntidad(eContacto);
        contacto.setId(contacto.getId());
		
	}
	
	
	private String CodigoMensajes(List<Mensaje> mensajes) {
		return mensajes.stream()
				.map(m -> String.valueOf(m.getId()))
				.reduce("", (x, y) -> x + y + " ")
				.trim();
	}
 
	private List<Mensaje> MensajesDesdeID(String idMensajes) {
		List<Mensaje> mensajes = new LinkedList<>();
		StringTokenizer token = new StringTokenizer(idMensajes, " ");
		TDSMensajeDAO mensajeDAO = TDSMensajeDAO.getInstancia();
		while (token.hasMoreTokens()) {
			String id = (String) token.nextElement();
			mensajes.add(mensajeDAO.get(Integer.valueOf(id)));
		}
		return mensajes;
	}
}
