package DAO;
import java.util.List;

import Clases.*;

public interface MensajeDAO {

	public void create(Mensaje mensaje);
	public void delete(Mensaje mensaje);
	public Mensaje get(int id);
	public List<Mensaje> recuperarMensajes();
}