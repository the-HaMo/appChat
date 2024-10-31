package Aplicacion;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

public class InfoModelo extends JPanel{
	
	public InfoModelo(String obj) {
		setMinimumSize(new Dimension(200,20));
		setMaximumSize(new Dimension(200,20));
		setPreferredSize(new Dimension(200,20));
		this.setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		//this.setBackground(new Color(200,0,0));
		this.setOpaque(false);
		//this.setBackground(new Color(200,200,100))
		JLabel nomb=new JLabel(obj,JLabel.LEFT);
		nomb.setFont(new Font("Tahoma", Font.BOLD, 15));
		nomb.setMinimumSize(new Dimension(90,20));
		nomb.setMaximumSize(new Dimension(90,20));
		nomb.setPreferredSize(new Dimension(90,20));
		/*
		JLabel tlf=new JLabel(telf,JLabel.LEFT);
		tlf.setMinimumSize(new Dimension(125,20));
		tlf.setMaximumSize(new Dimension(125,20));
		tlf.setPreferredSize(new Dimension(125,20));*/

		this.add(nomb);
		//this.add(tlf);
	}

}
