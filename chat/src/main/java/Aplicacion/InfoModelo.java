package Aplicacion;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoModelo extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InfoModelo(String obj, int i) {
		setMinimumSize(new Dimension(200,20));
		setMaximumSize(new Dimension(200,20));
		setPreferredSize(new Dimension(200,20));
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		this.setOpaque(false);
		JLabel nomb=new JLabel(obj,JLabel.LEFT);
		nomb.setFont(new Font("Tahoma", Font.BOLD, i));
		nomb.setMinimumSize(new Dimension(90,20));
		nomb.setMaximumSize(new Dimension(90,20));
		nomb.setPreferredSize(new Dimension(90,20));

		this.add(nomb);
	}

}
