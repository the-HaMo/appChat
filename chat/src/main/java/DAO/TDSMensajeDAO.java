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
        String horaStr = servPersistencia.recuperarPropiedadEntidad(eMensaje, HORA);
        LocalDateTime hora = LocalDateTime.parse(horaStr);
        int emoticono = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, EMOTICONO));
        int emisorId = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, EMISOR));
        int receptorId = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, RECEPTOR));

        Usuario emisor = TDSUsuarioDAO.getInstancia().get(emisorId);
        Contacto receptor = TDSContactoIndividualDAO.getInstancia().get(receptorId);

        Mensaje mensaje=new Mensaje(hora,emoticono,texto);
        
        TDSUsuarioDAO uDAO = TDSUsuarioDAO.getInstancia();
		int idUsuario = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, "emisor"));
		emisor = uDAO.get(idUsuario);
		mensaje.setEmisor(emisor);

		// Contacto o grupo receptor
		int codigoContacto = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eMensaje, "receptor"));
		TDSContactoIndividualDAO contactoDAO = TDSContactoIndividualDAO.getInstancia();
		receptor = contactoDAO.get(codigoContacto);
		mensaje.setReceptor(receptor);
        
        mensaje.setId(eMensaje.getId());
        return mensaje;
    }

    private Entidad mensajeToEntidad(Mensaje mensaje) {
        Entidad eMensaje = new Entidad();
        eMensaje.setNombre("mensaje");
        eMensaje.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
                new Propiedad(TEXTO, mensaje.getTexto()),
                new Propiedad(HORA, mensaje.getHora().toString()),
                new Propiedad(EMOTICONO, String.valueOf(mensaje.getEmoticono())),
                new Propiedad(EMISOR, String.valueOf(mensaje.getEmisor().getId())),
                new Propiedad(RECEPTOR, String.valueOf(mensaje.getReceptor().getId()))
        )));
        return eMensaje;
    }

    public Mensaje get(int codigo) {
        Entidad eMensaje = servPersistencia.recuperarEntidad(codigo);
        return entidadToMensaje(eMensaje);
    }


	@Override
	public void create(Mensaje mensaje) {
		Entidad eMensaje = this.mensajeToEntidad(mensaje);
        eMensaje = servPersistencia.registrarEntidad(eMensaje);
        mensaje.setId(eMensaje.getId());
	}


	@Override
	public List<Mensaje> recuperarMensajes() {
		List<Entidad> entidades = servPersistencia.recuperarEntidades("mensaje");
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
