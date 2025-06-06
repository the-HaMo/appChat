package DAO;
import java.util.List;

import Clases.*;

public interface ContactoIndividualDAO {
	
	void create(ContactoIndividual contacto);
	void delete(ContactoIndividual contacto);
	void update(ContactoIndividual contacto);
	ContactoIndividual get(int id);
	List<ContactoIndividual> getAll();
	
}
