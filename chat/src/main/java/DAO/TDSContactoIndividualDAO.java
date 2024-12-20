
package DAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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

    private static ServicioPersistencia servPersistencia;
    private static TDSContactoIndividualDAO unicaInstancia = null;

    public TDSContactoIndividualDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }

    @Override
    public void register(ContactoIndividual contacto) {
        Entidad eContacto = new Entidad();
        boolean guardado;

        try {
            eContacto = servPersistencia.recuperarEntidad(contacto.getId());
            guardado = true;
        } catch (NullPointerException e) {
            guardado = false;
        }

        if (guardado) {
            // Si esta guardado, no se sigue
            return;
        }

        eContacto.setNombre(contacto.getNombre());
        eContacto.setPropiedades(new ArrayList<Propiedad>(
            Arrays.asList(
                new Propiedad("nombre", contacto.getNombre()),
                new Propiedad("telefono", contacto.getTelefono()),
                new Propiedad("mensajes", contacto.getMensajes().toString()), // Assuming mensajes is a List<Mensaje>   HAY QUE PASARLO A STRING
                new Propiedad("usuario", String.valueOf(contacto.getUsuario().getId()))
            )
        ));

        servPersistencia.registrarEntidad(eContacto);
    }

    @Override
    public void delete(ContactoIndividual contacto) {
        // TODO Auto-generated method stub
    }

    @Override
    public void update(ContactoIndividual contacto) {
        // TODO Auto-generated method stub
    	Entidad eContact = servPersistencia.recuperarEntidad(contacto.getId());

		servPersistencia.eliminarPropiedadEntidad(eContact, "nombre");
		servPersistencia.anadirPropiedadEntidad(eContact, "nombre", contacto.getNombre());
		servPersistencia.eliminarPropiedadEntidad(eContact, "telefono");
		servPersistencia.anadirPropiedadEntidad(eContact, "telefono", String.valueOf(contacto.getTelefono()));
		servPersistencia.eliminarPropiedadEntidad(eContact, "mensajes");
		servPersistencia.anadirPropiedadEntidad(eContact, "mensajes",contacto.getMensajes().toString());		// HAY QUE PASARLO A STRING
		servPersistencia.eliminarPropiedadEntidad(eContact, "usuario");
		servPersistencia.anadirPropiedadEntidad(eContact, "usuario", String.valueOf(contacto.getUsuario().getId()));
    }

    @Override
    public ContactoIndividual get(int id) {
        // TODO Auto-generated method stub
        Entidad eContact = servPersistencia.recuperarEntidad(id);

		// recuperar propiedades que no son objetos
		String nombre = servPersistencia.recuperarPropiedadEntidad(eContact, "nombre");
		
		String telefono = servPersistencia.recuperarPropiedadEntidad(eContact, "telefono");
		
		ContactoIndividual contact = new ContactoIndividual(nombre, telefono,null, new LinkedList<Mensaje>());
		contact.setId(id);

		// Mensajes que el contacto tiene
		//List<Mensaje> mensajes = obtenerMensajesDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eContact, "mensajes"));
		//for (Mensaje m : mensajes)
		//	contact.addMensaje(m);

		// Obtener usuario del contacto
		//contact.setUsuario(obtenerUsuarioDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eContact, "usuario")));

		// Devolvemos el objeto contacto
		return contact;
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
    

    public static TDSContactoIndividualDAO getInstancia() {
        if (unicaInstancia == null) {
            unicaInstancia = new TDSContactoIndividualDAO();
        }
        return unicaInstancia;
    }
 
}
