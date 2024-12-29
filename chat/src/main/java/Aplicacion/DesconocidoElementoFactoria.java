package Aplicacion;

import Clases.*;

public class DesconocidoElementoFactoria implements ElementoInterfaz {

	private Usuario usuario;
	private Mensaje mensaje;

	public DesconocidoElementoFactoria(Usuario usuario, Mensaje mensaje) {
		this.usuario = usuario;
		this.mensaje = mensaje;

	}
	@Override
	public Elemento createElemento() {
		// TODO Auto-generated method stub
		return new Elemento(usuario, mensaje);
	}

	@Override
	public Elemento createElementoGrupo() {
		// TODO Auto-generated method stub
		return null;
	}

}
