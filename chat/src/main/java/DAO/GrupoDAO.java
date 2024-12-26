package DAO;

import java.util.List;

import Clases.Grupo;

public interface GrupoDAO {

	void create(Grupo grupo);
	void delete(Grupo grupo);
	void update(Grupo grupo);
	Grupo get(int id);
	List<Grupo> getAll();
}
