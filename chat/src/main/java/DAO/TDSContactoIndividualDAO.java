
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
    private static final String CONTACTO = "contacto";
	
	
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
        eContacto.setNombre(CONTACTO);
        eContacto.setPropiedades(new ArrayList<Propiedad>(
                Arrays.asList(
                        new Propiedad(NOMBRE, contacto.getNombre()),
                        new Propiedad(TELEFONO, contacto.getTelefono()),
                        new Propiedad(USUARIO, String.valueOf(contacto.getUsuario().getId())),
                        new Propiedad(MENSAJES, mensajesACodigo(contacto.getMensajes()))
                )));
        return eContacto;
    }
    
    private ContactoIndividual entidadToContacto(Entidad eContacto) {
        String nombre = servPersistencia.recuperarPropiedadEntidad(eContacto, NOMBRE);
        String telefono = servPersistencia.recuperarPropiedadEntidad(eContacto, TELEFONO);
        ContactoIndividual contacto = new ContactoIndividual(nombre, telefono, null, new LinkedList<Mensaje>());
        contacto.setId(eContacto.getId());
        PoolDAO.getInstancia().addObjeto(contacto.getId(), contacto);
        
        int usuarioId = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eContacto, USUARIO));
        Usuario usuario = TDSUsuarioDAO.getInstancia().get(usuarioId);
        contacto.setUsuario(usuario);
        List<Mensaje> mensajes = mensajesDesdeID(servPersistencia.recuperarPropiedadEntidad(eContacto, MENSAJES));
		for (Mensaje mensaje : mensajes) {
			contacto.addMensaje(mensaje);
		}
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

		for (Propiedad prop : eContact.getPropiedades()) {
	        if (prop.getNombre().equals(NOMBRE)) {
	            prop.setValor(contacto.getNombre());
	        } else if (prop.getNombre().equals(TELEFONO)) {
	            prop.setValor( String.valueOf(contacto.getTelefono()));
	        } else if (prop.getNombre().equals(MENSAJES)) {
	            prop.setValor(mensajesACodigo(contacto.getMensajes()));
	        }else if (prop.getNombre().equals(USUARIO)) {
                prop.setValor(String.valueOf(contacto.getUsuario().getId()));
            }
	        servPersistencia.modificarPropiedad(prop);
	    	}
    }

    @Override
    public ContactoIndividual get(int id) {
    	
    	if(!PoolDAO.getInstancia().contiene(id)) {
    		Entidad eContacto = servPersistencia.recuperarEntidad(id);
            return entidadToContacto(eContacto);
		} else {
			return (ContactoIndividual) PoolDAO.getInstancia().getObjeto(id);
		}
    }
    
    

    
    @Override
    public List<ContactoIndividual> getAll() {
        LinkedList<ContactoIndividual> contactos = new LinkedList<>();
        List<Entidad> eContactos = servPersistencia.recuperarEntidades(CONTACTO);

        for (Entidad eContacto : eContactos) {
            contactos.add(get(eContacto.getId()));
        }

        return contactos;
    }



	@Override
	public void create(ContactoIndividual contacto) {
		Entidad eContacto = contactoToEntidad(contacto);
		eContacto = servPersistencia.registrarEntidad(eContacto);
        contacto.setId(eContacto.getId());
        PoolDAO.getInstancia().addObjeto(contacto.getId(), contacto);
		
	}
	
	
	private String mensajesACodigo(List<Mensaje> mensajes) {
        return mensajes.stream()
                .map(m -> String.valueOf(m.getId()))
                .reduce("", (x, y) -> x + y + " ")
                .trim();
    }
 
	private List<Mensaje> mensajesDesdeID(String idMensajes) {
	    List<Mensaje> mensajes = new LinkedList<>();
	    StringTokenizer token = new StringTokenizer(idMensajes, " ");
	    TDSMensajeDAO mensajeDAO = TDSMensajeDAO.getInstancia();
	    while (token.hasMoreTokens()) {
	        String id = token.nextToken();
	        mensajes.add(mensajeDAO.get(Integer.valueOf(id)));
	    }
	    return mensajes;
	}
}
