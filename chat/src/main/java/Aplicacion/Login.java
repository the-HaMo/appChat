package Aplicacion;

import Controlador.*;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.Color;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.SwingConstants;
import javax.swing.JButton;

public class Login {

	private JFrame ventana;
	private JTextField casilla_telf;
	private JTextField casilla_contraseña;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.ventana.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		this.ventana.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		ventana = new JFrame();
		ventana.setResizable(false);
		ventana.setTitle("AppChat");
		ventana.setPreferredSize(new Dimension(500, 420));

		ventana.setBounds(100, 100, 500, 420);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new BorderLayout(0, 0));
		
		ImageIcon icono= new ImageIcon(getClass().getResource("/logo.png"));	//LOGO
		ventana.setIconImage(icono.getImage());	//LOGO
	
		
		JPanel panel_norte = new JPanel();
		panel_norte.setBackground(new Color(0, 255, 127));
		panel_norte.setPreferredSize(new Dimension(550, 80));
		ventana.getContentPane().add(panel_norte, BorderLayout.NORTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(35, 0));
		panel_norte.add(horizontalStrut);
		
		JLabel labelTitulo = new JLabel("AppChat");
		labelTitulo.setFont(new Font("Sitka Text", Font.BOLD, 40));
		labelTitulo.setPreferredSize(new Dimension(250, 80));
		panel_norte.add(labelTitulo);
		
		JPanel panel_este = new JPanel();
		panel_este.setBackground(new Color(0, 255, 127));
		ventana.getContentPane().add(panel_este, BorderLayout.WEST);
		
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		horizontalStrut_1.setPreferredSize(new Dimension(40, 0));
		panel_este.add(horizontalStrut_1);
		
		JPanel panel_oeste = new JPanel();
		panel_oeste.setBackground(new Color(0, 255, 127));
		ventana.getContentPane().add(panel_oeste, BorderLayout.EAST);
		
		Component horizontalStrut_2 = Box.createHorizontalStrut(20);
		horizontalStrut_2.setPreferredSize(new Dimension(40, 0));
		panel_oeste.add(horizontalStrut_2);
		
		JPanel panel_sur = new JPanel();
		panel_sur.setBackground(new Color(0, 255, 127));
		ventana.getContentPane().add(panel_sur, BorderLayout.SOUTH);
		
		JButton boton_registrar = new JButton("REGISTRAR");
		boton_registrar.setHorizontalTextPosition(SwingConstants.CENTER);
		panel_sur.add(boton_registrar);
		boton_registrar.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				Registro reg = new Registro();
				reg.Mostrar();

			}
		});
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(80, 0));
		panel_sur.add(horizontalStrut_3);
		
		Component verticalStrut = Box.createVerticalStrut(20);
		verticalStrut.setPreferredSize(new Dimension(0, 45));
		panel_sur.add(verticalStrut);
		
		
		JButton boton_cancelar = new JButton("CANCELAR");
		panel_sur.add(boton_cancelar);
		boton_cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana.dispose();
			}
		});
		
		JButton boton_aceptar = new JButton("ACEPTAR");
		panel_sur.add(boton_aceptar);
		
		
		
		JPanel panel_centro = new JPanel();
		panel_centro.setBackground(new Color(0, 255, 127));
		panel_centro.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 3, true), "LOGIN ", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		ventana.getContentPane().add(panel_centro, BorderLayout.CENTER);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(70, 120));
		panel_centro.add(verticalStrut_1);
		casilla_telf = new JTextField();
		casilla_telf.setHorizontalAlignment(SwingConstants.CENTER);
		casilla_telf.setFont(new Font("Tahoma", Font.PLAIN, 16));
		casilla_telf.setText("Teléfono"); // Placeholder inicial
		casilla_telf.setColumns(10);


		panel_centro.add(casilla_telf);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setPreferredSize(new Dimension(70, 120));
		panel_centro.add(verticalStrut_2);
		
		// Casilla Contraseña
		casilla_contraseña = new JTextField();
		casilla_contraseña.setText("Contraseña");
		casilla_contraseña.setHorizontalAlignment(SwingConstants.CENTER);
		casilla_contraseña.setFont(new Font("Tahoma", Font.PLAIN, 16));
		casilla_contraseña.setColumns(10);
		panel_centro.add(casilla_contraseña);
		
		boton_aceptar.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						
						boolean login = Controlador.INSTANCE.loginUsuario(
								casilla_telf.getText(),
								casilla_contraseña.getText());
						if (login) {
							chat mens=new chat();
							mens.Mostrar();
							ventana.dispose();
						} else {
							System.err.println("Nombre de usuario o contraseña no valido");
					}						
				}
			});
	}
	
public void Mostrar() {
    this.ventana.setVisible(true);
    }

}
