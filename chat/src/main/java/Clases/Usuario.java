package Clases;

import java.util.List;

import javax.swing.ImageIcon;

public class Usuario {
	
	private String nombre;
	private String telefono;
	private String contraseña;
	private ImageIcon imagen;
	private String link;
	private boolean premium=false; //propiedad calculada y Varia . AL PRINCIPIO NO ES PREMIUM
	private String saludo;
	private String fechaNacimiento;
	private int id=0;
	private List<Contacto> listaContactos;
	
	public Usuario(String nombre, String telefono, String contraseña, ImageIcon imagen, String fechaNacimiento) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.imagen = imagen;
		this.fechaNacimiento = fechaNacimiento;
	}
	
	

	public Usuario(String nombre, String telefono, String contraseña, String fechaNacimiento) {
		super();
		this.nombre = nombre;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		this.imagen=new ImageIcon(getClass().getResource("/sinFotoContc.png"));
	}



	public String getNombre() {
		return nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getContraseña() {
		return contraseña;
	}

	public ImageIcon getImagen() {
		return imagen;
	}
	
	public boolean isPremium() {
		return premium;
	}

	public void setPremium(boolean premium) {
		this.premium = premium;
	}
	
	public String getFechaNacimiento() {
        return fechaNacimiento;
    }
	
	public boolean isPassword(String Password) {
		return contraseña.equals(Password);
	}

	public void setImagen(ImageIcon imagen) {
		this.imagen = imagen;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
	public String getPremiumString() {
		return Boolean.toString(premium);
	}

	public List<Contacto> getListaContactos() {
		return listaContactos;
	}
	
	public boolean addContacto(Contacto c) {
		if (listaContactos.contains(c)) {
			return false;
		} else {
			listaContactos.add(c);
			return true;
		}
	}

	public boolean contieneContacto(String telf) {
		for (Contacto contact : listaContactos) {
			if (contact.getTelefono().equals(telf)) {
				return true;
			}
		}
		return false;
	}
}
