package Aplicacion;
// UsuarioElementoFactory.java
import Clases.Usuario;

public class UsuarioElementoFactoria implements ElementoInterfaz {
    private Usuario usuario;

    public UsuarioElementoFactoria(Usuario usuario) {
        this.usuario = usuario;
    }

    public Elemento createElemento() {
        return new Elemento(usuario);
    }

	@Override
	public Elemento createElementoGrupo() {
		// TODO Auto-generated method stub
		return null;
	}
}
