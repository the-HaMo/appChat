package Aplicacion;

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
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class FotoCuenta extends JPanel{
	
	
	public FotoCuenta(String fileName) {
		
		// Hola
		
		
		/*
		 * String path =
		"https://widget-assets.geckochat.io/69d33e2bd0ca2799b2c6a3a3870537a9.png";
		URL url = new URL(path);
		BufferedImage image = ImageIO.read(url);
		JLabel label = new JLabel(new ImageIcon(image));
		 * 
		 */
		JLabel lblimagen=new JLabel();
		Image foto=new ImageIcon(getClass().getResource("/"+fileName)).getImage();
		lblimagen.setIcon(new ImageIcon(imagenCuadrado(foto)));
		fixSize(this,100,120);
		lblimagen.setBorder(new LineBorder(Color.WHITE,3));
		this.add(lblimagen);
	}
	
	private void fixSize(JComponent c, int x, int y) {
		c.setMinimumSize(new Dimension(x,y));
		c.setMaximumSize(new Dimension(x,y));
		c.setPreferredSize(new Dimension(x,y));
	}
	
	private Image imagenCuadrado(Image img) {
		    // Crear un nuevo BufferedImage de 100x120
		    BufferedImage imgCuadrado = new BufferedImage(100, 120, BufferedImage.TYPE_4BYTE_ABGR);
		    Graphics2D graphics = imgCuadrado.createGraphics();
		    // Dibujar la imagen escalada directamente al tamaño 100x120
		    graphics.drawImage(img, 0, 0, 100, 120, null);
		    graphics.dispose();

		    return imgCuadrado;

	}
}
