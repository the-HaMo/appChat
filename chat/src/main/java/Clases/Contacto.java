
package Clases;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public abstract class Contacto {

    private int id;
    private String nombre;
    private String telefono;
    private List<Mensaje> mensajes;
    
    //Grupo
	public Contacto(String nombre) {
		this.nombre = nombre;
		this.mensajes = new LinkedList<>();
	}

	
	//ContactoIndividual
    public Contacto(String nombre, String telefono) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mensajes = new LinkedList<>();
    }

    //Añadir contacto con una conversacion
    public Contacto(String nombre, String telefono, List<Mensaje> mensajes) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.mensajes = mensajes;
    }

    public String getNombre() {
        return nombre;
    }

    public abstract List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario);
    
    public String getTelefono() {
        return telefono;
    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }
    
	public void addMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
	}

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }  
    
}
