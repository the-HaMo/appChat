package Aplicacion;

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

import Clases.Usuario;


public class Elemento extends JPanel{
	
	private String nombre;
	private String telf;
	private ImageIcon fto;
	
	public Elemento(Usuario usu) {
		this.nombre=usu.getNombre();
		this.telf=usu.getTelefono();
		this.fto=usu.getImagen();
		
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		fixSize(this,300,100);
		this.setBackground(Color.LIGHT_GRAY);
		this.setBorder(new TitledBorder(""));
		
		JLabel lblimagen=new JLabel();
		Image Imagen= fto.getImage();
		lblimagen.setIcon(new ImageIcon(imagenCircular(Imagen)));
		fixSize(lblimagen,75,84);

		InfoModelo nomb= new InfoModelo(nombre);
		InfoModelo tlf=new InfoModelo(telf);
		
		JPanel info=new JPanel();
		info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
		fixSize(info,200,100);
		info.setOpaque(false);
		info.add(nomb);
		info.add(tlf);
		
		this.add(lblimagen);
		this.add(info);

		
	}
	
	private Image imagenCircular(Image img) {
		BufferedImage  imgCicrculo=new BufferedImage(72, 72, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D graphics=imgCicrculo.createGraphics();
		Ellipse2D.Double forma=new Ellipse2D.Double(0,0,72,72);
		graphics.setClip(forma);
		graphics.drawImage(img,0,0,72,72,null);
		graphics.dispose();
		return imgCicrculo;
	}
	
	private void fixSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x,y));
		c.setMaximumSize(new Dimension(x,y));
		c.setPreferredSize(new Dimension(x,y));
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

}
