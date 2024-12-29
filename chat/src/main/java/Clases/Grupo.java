package Clases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Grupo extends Contacto{

	private List<ContactoIndividual> lista;
	private String link;
	


	public Grupo(String nombre, List<ContactoIndividual> contactos, String link) {
		super(nombre);
		this.lista = contactos;
		if ((link == "") || (link == null)) {
			this.link = getClass().getResource("/grupo.png").toString();
		} else {
			this.link = link;
		}
	}
	
	public Grupo(String nombre, List<Mensaje> mensajes, List<ContactoIndividual> contactos, String link) {
		super(nombre, null,mensajes);
		this.lista = contactos;
		if ((link == "") || (link == null)) {
			this.link = getClass().getResource("/grupo.png").toString();
		} else {
			this.link = link;
		}
	}

	public void addContacto(ContactoIndividual contacto) {
		if(!lista.contains(contacto)) {
			lista.add(contacto);
		}

	}

	public List<ContactoIndividual> getContactos() {
		return lista;
	}
	
	public String getLink() {
		return link;
	}

	
	public void addMensaje(Mensaje mensaje) {
		for (ContactoIndividual contacto : lista) {
			contacto.addMensaje(mensaje);
		}
	}
	
	public ImageIcon getImageIcon() {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(link));
			return new ImageIcon(image);
		} catch (IOException e) {
			BufferedImage defaultImage;
			try {
				defaultImage = ImageIO.read(getClass().getResource("/grupo.png"));
				return new ImageIcon(defaultImage);
			} catch (IOException e1) {
				return null;
			}
            
		}
        
    }

	@Override
	public List<Mensaje> getMensajesRecibidos(Optional<Usuario> usuario) {
		return this.lista.stream()
				.flatMap(c -> c.getUsuario().getListaContactos().stream())
				.filter(c -> c instanceof Grupo)
				.map(c -> (Grupo) c)
				.filter(g -> this.equals(g))
				.flatMap(g -> g.getMensajes().stream())
				.collect(Collectors.toList());
		}
	}




