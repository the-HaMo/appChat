package Aplicacion;

import javax.swing.ImageIcon;

public class Usuario {
	
	private String nombre;
	private String telefono;
	private String contraseña;
	private ImageIcon imagen;
	private boolean premium; //propiedad calculada y Varia 
	private String fechaNac;
	
	public Usuario(String nombre, String telefono, String contraseña, ImageIcon imagen, boolean premium, String fechaNac) {
		this.nombre = nombre;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.imagen = imagen;
		this.premium = premium;
		this.fechaNac = fechaNac;
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
	
	public String getfechaNac() {
		return fechaNac;
	}
	
	public boolean isPassword(String Password) {
		return contraseña.equals(Password);
	}
	
}
