package Aplicacion;

import Clases.Grupo;

public class GrupoElementoFactoria implements ElementoInterfaz {
	private Grupo grupo;

	public GrupoElementoFactoria(Grupo grupo) {
		this.grupo = grupo;
	}

	@Override
	public Elemento createElemento() {
		return new Elemento(grupo);
	}
	
	@Override
	public Elemento createElementoGrupo() {
		return null;
	}
	
}
