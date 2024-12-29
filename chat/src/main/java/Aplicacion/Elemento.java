
package Aplicacion;

import Clases.Contacto;
import Clases.ContactoIndividual;
import Clases.Grupo;
import Clases.Mensaje;
import Clases.Usuario;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;


public class Elemento extends JPanel {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String telf;
    private ImageIcon fto;
    private Mensaje ultimoMensaje;
    private Contacto contacto;

    public Elemento(Contacto c, Mensaje m) {
        this.nombre = c.getNombre();
        this.telf = c.getTelefono();
        this.contacto = c;
        this.ultimoMensaje = m;
        if (c instanceof ContactoIndividual) {
            ContactoIndividual ci = (ContactoIndividual) c;
            this.fto = ci.getUsuario().getImageIcon();
        } else {
            Grupo g = (Grupo) c;
            this.fto = g.getImageIcon();
        }
        initializeComponent();
        addInfoComponent(new InfoModelo(ultimoMensaje.getTexto(), 12));
    }

    public Elemento(Contacto c) {
        this.nombre = c.getNombre();
        this.telf = c.getTelefono();
        this.contacto = c;
        if (c instanceof ContactoIndividual) {
            ContactoIndividual ci = (ContactoIndividual) c;
            this.fto = ci.getUsuario().getImageIcon();
        } else {
            Grupo g = (Grupo) c;
            this.fto = g.getImageIcon();
        }
        initializeComponent();
    }

    public Elemento(Usuario usuario) {
        this.nombre = usuario.getNombre();
        this.fto = usuario.getImageIcon();
        initializeComponent();
    }

    private void initializeComponent() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        fixSize(this, 300, 100);
        this.setBackground(Color.LIGHT_GRAY);
        this.setBorder(new TitledBorder(""));

        JLabel lblimagen = new JLabel();
        Image Imagen = fto.getImage();
        lblimagen.setIcon(new ImageIcon(imagenCircular(Imagen)));
        fixSize(lblimagen, 75, 84);

        InfoModelo nomb = new InfoModelo(nombre, 15);
        InfoModelo tlf = new InfoModelo(telf, 15);

        JPanel info = new JPanel();
        info.setLayout(new BoxLayout(info, BoxLayout.Y_AXIS));
        fixSize(info, 200, 100);
        info.setOpaque(false);
        info.add(nomb);
        if (telf != null) {
            info.add(tlf);
        }

        this.add(lblimagen);
        this.add(info);
    }

    private void addInfoComponent(InfoModelo infoModelo) {
        JPanel info = (JPanel) this.getComponent(1);
        info.add(infoModelo);
    }

    public Image imagenCircular(Image img) {
        BufferedImage imgCicrculo = new BufferedImage(72, 72, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D graphics = imgCicrculo.createGraphics();
        Ellipse2D.Double forma = new Ellipse2D.Double(0, 0, 72, 72);
        graphics.setClip(forma);
        graphics.drawImage(img, 0, 0, 72, 72, null);
        graphics.dispose();
        return imgCicrculo;
    }

    private void fixSize(JComponent c, int x, int y) {
        c.setMinimumSize(new Dimension(x, y));
        c.setMaximumSize(new Dimension(x, y));
        c.setPreferredSize(new Dimension(x, y));
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelf() {
        return telf;
    }

    public ImageIcon getFto() {
        return fto;
    }

    public Contacto getContacto() {
        return contacto;
    }
}
