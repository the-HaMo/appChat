package Aplicacion;

import Clases.Contacto;
import Clases.Mensaje;

public class ContactoElementoFactoria implements ElementoInterfaz {

	    private Contacto contacto;
	    private Mensaje mensaje;

	    public ContactoElementoFactoria(Contacto contacto, Mensaje mensaje) {
	        this.contacto = contacto;
	        this.mensaje = mensaje;
	    }
	    
	    public ContactoElementoFactoria(Contacto c) {
	    	this.contacto=c;
	    }

	    public Elemento createElementoGrupo() {
	    	return new Elemento(contacto);
	    }
	    
	    public Elemento createElemento() {
	        return new Elemento(contacto, mensaje);
	    }
	

}
