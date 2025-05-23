package Clases;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import DAO.DAOException;
import DAO.FactoriaDAO;

public enum RepositorioUsuarios {
    INSTANCE;
    private FactoriaDAO factoria;

    private HashMap<Integer, Usuario> usuariosPorID;
    private HashMap<String, Usuario> usuariosPorTelf;

    private RepositorioUsuarios() {
        usuariosPorID = new HashMap<>();
        usuariosPorTelf = new HashMap<>();

        try {
            factoria = FactoriaDAO.getInstancia();
            List<Usuario> listausuarios = factoria.getUsuarioDAO().getAll();
            for (Usuario usuario : listausuarios) {
                usuariosPorID.put(usuario.getId(), usuario);
                usuariosPorTelf.put(usuario.getTelefono(), usuario);
            }
        } catch (DAOException eDAO) {
            eDAO.printStackTrace();
        }
    }

    public List<Usuario> findUsuarios() throws DAOException {
        return new LinkedList<>(usuariosPorTelf.values());
    }

    public Usuario findUsuario(String telf) {
        return usuariosPorTelf.get(telf);
    }

    public Usuario findUsuario(int id) {
        return usuariosPorID.get(id);
    }

    public void addUsuario(Usuario usuario) {
        usuariosPorID.put(usuario.getId(), usuario);
        usuariosPorTelf.put(usuario.getTelefono(), usuario);
    }
    
	public List<Usuario> getAllUsuarios() {
		List<Usuario> lista = new LinkedList<>(usuariosPorTelf.values());
		lista.sort((o1,o2) -> o1.getNombre().compareTo(o2.getNombre()));
		return lista;
	}
    /*
	public void removeUsuario(Usuario usuario) {
		usuariosPorID.remove(usuario.getId());
		usuariosPorTelf.remove(usuario.getTelefono());
	}*/
}

