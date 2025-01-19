package Clases;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Usuario {

	private static final double PRECIO_PREMIUM = 50.0;
	
    private String nombre;
    private String telefono;
    private String contraseña;
    private String link;
    private boolean premium;
    private String saludo;
    private Date fechaNacimiento;
    private int id = 0;
    private List<Contacto> listaContactos= new LinkedList<Contacto>();
    private LocalDate fechaRegistro;

    public Usuario(String nombre, String telefono, String contraseña, String url, Date fechaNacimiento, String saludo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.saludo = vacio(saludo);
        this.fechaRegistro =LocalDate.now() ;
        this.premium = false;
        if ((url=="")||(url==null)) {
            this.link = getClass().getResource("/sinFotoContc.png").toString();
        } else {
            this.link = url;
        }
    }
    
    
//Persistencia
    public Usuario(String nombre, String telefono, String contraseña, String url, Date fechaNacimiento, String saludo, boolean premium, LocalDate fecha) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.contraseña = contraseña;
        this.fechaNacimiento = fechaNacimiento;
        this.saludo = vacio(saludo);
        this.fechaRegistro =fecha;
        if ((url=="")||(url==null)){
            this.link = getClass().getResource("/sinFotoContc.png").toString();
        } else {
            this.link = url;
        }
        this.premium = premium;
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

    public String getLink() {
        return link;
    }
    
    public LocalDate getFechaRegistro() {
    	        return fechaRegistro;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean isPassword(String Password) {
        return contraseña.equals(Password);
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ImageIcon getImageIcon() {
        try {
            if (link.startsWith("https:")) {
                // Cargar imagen desde URL remota
                URL url = new URL(link);
                BufferedImage image = ImageIO.read(url);
                return new ImageIcon(image);
            } else {
                // Cargar imagen desde una ruta de archivo local
                BufferedImage image = ImageIO.read(new File(link));
                return new ImageIcon(image);
            }
        } catch (IOException e) {
            // Si hay una excepción, cargar una imagen por defecto desde recursos
            try {
                BufferedImage defaultImage = ImageIO.read(getClass().getResource("/sinFotoContc.png"));
                return new ImageIcon(defaultImage);
            } catch (IOException ioException) {
                // En caso extremo, devuelve null si la imagen predeterminada tampoco está disponible
                return null;
            }
        }
    }

	public String vacio(String s) {
		if (s.isBlank()) {
			s=("Hey, I'm using AppChat.");
		}
			return s;
	}

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getSaludo() {
        return saludo;
    }

    public String getPremiumString() {
        return Boolean.toString(premium);
    }

    public void setSaludo(String saludo) {
        this.saludo = saludo;
    }

    public List<Contacto> getListaContactos() {
        return listaContactos;
    }

    public void addContacto(Contacto contacto) {
        if (!listaContactos.contains(contacto)) {
            listaContactos.add(contacto);
        }
    }

    public boolean contieneTelf(String telf) {
    	return listaContactos.stream()
    	                     .anyMatch(c -> c.getTelefono().equals(telf));

    }
    
    public boolean contieneContacto(Contacto contacto) {
        return listaContactos.stream()
                             .anyMatch(c -> c.getTelefono() != null && c.getTelefono().equals(contacto.getTelefono()));
    }

    
    public List<String> getContactosTelf() {
    	return listaContactos.stream()
    						.map(Contacto::getTelefono)
    						.collect(Collectors.toList());
    }
    
    public Contacto getContacto(String telefono) {
    	        return listaContactos.stream()
						.filter(c -> c.getTelefono().equals(telefono))
						.findAny()
						.orElse(null);
    }
    
    public List<Grupo> getGrupos() {
		return listaContactos.stream()
				.filter(c -> c instanceof Grupo)
				.map(c -> (Grupo) c)
				.collect(Collectors.toList());
	}
    public boolean perteneceGrupo(String Grupo) {
		return listaContactos.stream()
				.anyMatch(c -> c instanceof Grupo && c.getNombre().equals(Grupo));
	}
    
    public boolean contieneContactoInd(String nombre) {
		return listaContactos.stream()
				.anyMatch(c -> c instanceof ContactoIndividual && c.getNombre().equals(nombre));
	}
    
    public String toString() {
    	return (nombre + "    Telefono: " + telefono);
    }
    
    
	public double getPrecioPremium() {
		return PRECIO_PREMIUM;
	}
}