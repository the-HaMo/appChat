package Aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.BoxLayout;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddContacto {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtTelefono;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddContacto window = new AddContacto();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AddContacto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panelNorteAlerta = new JPanel();
		panelNorteAlerta.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		panelNorteAlerta.setPreferredSize(new Dimension(400, 50));
		panelNorteAlerta.setOpaque(false);
		frame.getContentPane().add(panelNorteAlerta, BorderLayout.NORTH);
		
		JLabel lblAlerta = new JLabel("ALERTA");
		lblAlerta.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAlerta.setPreferredSize(new Dimension(100, 40));
		panelNorteAlerta.add(lblAlerta);
		
		JPanel panelSurAlerta = new JPanel();
		panelSurAlerta.setPreferredSize(new Dimension(10, 40));
		frame.getContentPane().add(panelSurAlerta, BorderLayout.SOUTH);
		
		Component horizontalStrut = Box.createHorizontalStrut(20);
		horizontalStrut.setPreferredSize(new Dimension(200, 0));
		panelSurAlerta.add(horizontalStrut);
		
		JButton btnAceptar = new JButton("ACEPTAR");
		panelSurAlerta.add(btnAceptar);
		
		JButton btnCancelar = new JButton("CANCELAR");
		panelSurAlerta.add(btnCancelar);
		
		JPanel panelOesteAlerta = new JPanel();
		panelOesteAlerta.setPreferredSize(new Dimension(70, 10));
		frame.getContentPane().add(panelOesteAlerta, BorderLayout.WEST);
		panelOesteAlerta.setLayout(new BoxLayout(panelOesteAlerta, BoxLayout.Y_AXIS));

		
		JPanel panelEsteAlerta = new JPanel();
		panelEsteAlerta.setPreferredSize(new Dimension(70, 10));
		frame.getContentPane().add(panelEsteAlerta, BorderLayout.EAST);
		
		JPanel panelCentroAlerta = new JPanel();
		frame.getContentPane().add(panelCentroAlerta, BorderLayout.CENTER);
		panelCentroAlerta.setLayout(new BoxLayout(panelCentroAlerta, BoxLayout.Y_AXIS));
		
		Component verticalStrut = Box.createVerticalStrut(20);
		panelCentroAlerta.add(verticalStrut);
		
		JLabel lblNewLabel = new JLabel("Introduzca nombre y telefono del contacto");
		lblNewLabel.setPreferredSize(new Dimension(300, 13));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panelCentroAlerta.add(lblNewLabel);
		
		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setPreferredSize(new Dimension(0, 25));
		panelCentroAlerta.add(verticalStrut_3);
		
		txtNombre = new JTextField();
		txtNombre.setMinimumSize(new Dimension(7, 0));
		txtNombre.setText("NOMBRE");
		txtNombre.setPreferredSize(new Dimension(7, 10));
		panelCentroAlerta.add(txtNombre);
		txtNombre.setColumns(10);
		
		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 30));
		panelCentroAlerta.add(verticalStrut_1);
		
		txtTelefono = new JTextField();
		txtTelefono.setMinimumSize(new Dimension(7, 0));
		txtTelefono.setText("TELEFONO");
		txtTelefono.setPreferredSize(new Dimension(7, 10));
		panelCentroAlerta.add(txtTelefono);
		txtTelefono.setColumns(10);
		
		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setPreferredSize(new Dimension(0, 25));
		panelCentroAlerta.add(verticalStrut_2);
	}

	  public void Mostrar() {
	        this.frame.setVisible(true);
	    }
}
