package DAO;

import java.util.List;

import Clases.Usuario;

public interface UsuarioDAO {
	
	void create(Usuario usuario);
	void update(Usuario usuario);
	Usuario get(int id);
	List<Usuario> getAll();
	
}
