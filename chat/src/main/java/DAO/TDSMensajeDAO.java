package DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import Clases.Contacto;
import Clases.Mensaje;
import Clases.Usuario;
import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;


public final class TDSMensajeDAO implements MensajeDAO {

    private static final String EMISOR = "emisor";
    private static final String RECEPTOR = "receptor";
    private static final String TEXTO = "texto";
    private static final String EMOTICONO = "emoticono";
    private static final String HORA = "hora";
    private static final String MENSAJE = "mensaje";

    private ServicioPersistencia servPersistencia;
    private static TDSMensajeDAO unicaInstancia = null;

    public TDSMensajeDAO() {
        servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
    }
    
	public static TDSMensajeDAO getInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new TDSMensajeDAO();
		return unicaInstancia;
	}

    private Mensaje entidadToMensaje(Entidad eMensaje) {
        String texto = servPersistencia.recuperarPropiedadEntidad(eMensaje, TEXTO);
        LocalDateTime hora = LocalDateTime.parse(servPersistencia.recuperarPropiedadEntidad(eMensaje, HORA));
        int emoticono = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, EMOTICONO));
        int emisorId = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, EMISOR));
        int receptorId = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, RECEPTOR));
        Mensaje mensaje=new Mensaje(hora,emoticono,texto);
        mensaje.setId(eMensaje.getId());
        PoolDAO.getInstancia().addObjeto(mensaje.getId(), mensaje);
        
        Usuario emisor = TDSUsuarioDAO.getInstancia().get(emisorId);
        Contacto receptor = TDSContactoIndividualDAO.getInstancia().get(receptorId);

        
        
        TDSUsuarioDAO uDAO = TDSUsuarioDAO.getInstancia();
		int idUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, EMISOR));
		emisor = uDAO.get(idUsuario);
		mensaje.setEmisor(emisor);

		// Contacto o grupo receptor
		int codigoContacto = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, RECEPTOR));
		TDSContactoIndividualDAO contactoDAO = TDSContactoIndividualDAO.getInstancia();
		receptor = contactoDAO.get(codigoContacto);
		mensaje.setReceptor(receptor);
        
        
        return mensaje;
    }

    private Entidad mensajeToEntidad(Mensaje mensaje) {
        Entidad eMensaje = new Entidad();
        eMensaje.setNombre(MENSAJE);
        eMensaje.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
                new Propiedad(TEXTO, mensaje.getTexto()),
                new Propiedad(HORA, mensaje.getFechaStr()),
                new Propiedad(EMOTICONO, String.valueOf(mensaje.getEmoticono())),
                new Propiedad(EMISOR, String.valueOf(mensaje.getEmisor().getId())),
                new Propiedad(RECEPTOR, String.valueOf(mensaje.getReceptor().getId()))
        )));
        return eMensaje;
    }

    public Mensaje get(int codigo) {
    	if(!PoolDAO.getInstancia().contiene(codigo)) {
    		Entidad eMensaje = servPersistencia.recuperarEntidad(codigo);
            return entidadToMensaje(eMensaje);
    	}else {
    		return (Mensaje) PoolDAO.getInstancia().getObjeto(codigo);
    	}
        
    }


	@Override
	public void create(Mensaje mensaje) {
		Entidad eMensaje = this.mensajeToEntidad(mensaje);
        eMensaje = servPersistencia.registrarEntidad(eMensaje);
        mensaje.setId(eMensaje.getId());
        PoolDAO.getInstancia().addObjeto(mensaje.getId(), mensaje);
	}


	@Override
	public List<Mensaje> getAll() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades(MENSAJE);
        List<Mensaje> mensajes = new LinkedList<>();
        for (Entidad eMensaje : entidades) {
            mensajes.add(get(eMensaje.getId()));
        }
        return mensajes;
	}

	@Override
	public void delete(Mensaje mensaje) {
		Entidad eMensaje = servPersistencia.recuperarEntidad(mensaje.getId());
		servPersistencia.borrarEntidad(eMensaje);
	}
}