package Aplicacion;


import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Clases.ContactoIndividual;
import Controlador.Controlador;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextField;
import javax.swing.JButton;

public class AddContacto {

	private JFrame frame;
	private JTextField txtNombre;
	private JTextField txtTelefono;
	private Chat VentanaChat;

	public AddContacto(Chat VentanaChat) {
		this.VentanaChat = VentanaChat;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ImageIcon icono= new ImageIcon(getClass().getResource("/logo.png"));	//LOGO
		frame.setIconImage(icono.getImage());	//LOGO
		frame.setTitle("Añadir Contacto");

		JPanel panelNorteAlerta = new JPanel();
		panelNorteAlerta.setPreferredSize(new Dimension(400, 50));
		panelNorteAlerta.setOpaque(false);
		frame.getContentPane().add(panelNorteAlerta, BorderLayout.NORTH);

		JLabel titulo = new JLabel("Añadir Contacto\r\n");
		titulo.setFont(new Font("Tahoma", Font.BOLD, 18));
		titulo.setPreferredSize(new Dimension(150, 40));
		panelNorteAlerta.add(titulo);

		JPanel panelOesteAlerta = new JPanel();
		panelOesteAlerta.setPreferredSize(new Dimension(70, 10));
		frame.getContentPane().add(panelOesteAlerta, BorderLayout.WEST);
		panelOesteAlerta.setLayout(new BoxLayout(panelOesteAlerta, BoxLayout.Y_AXIS));

		JPanel panelEsteAlerta = new JPanel();
		panelEsteAlerta.setPreferredSize(new Dimension(70, 10));
		frame.getContentPane().add(panelEsteAlerta, BorderLayout.EAST);

		JPanel panelCentroAlerta = new JPanel();
		panelCentroAlerta.setMaximumSize(new Dimension(32767, 60));
		panelCentroAlerta.setPreferredSize(new Dimension(10, 100));
		frame.getContentPane().add(panelCentroAlerta, BorderLayout.CENTER);
		panelCentroAlerta.setLayout(new BoxLayout(panelCentroAlerta, BoxLayout.Y_AXIS));

		Component verticalStrut_3 = Box.createVerticalStrut(20);
		verticalStrut_3.setPreferredSize(new Dimension(0, 25));
		panelCentroAlerta.add(verticalStrut_3);

		// Panel para el campo Nombre
		JPanel panelNombre = new JPanel();
		panelNombre.setLayout(new BorderLayout());
		panelNombre.setMaximumSize(new Dimension(400, 30)); // Tamaño máximo para ajustar diseño
		panelCentroAlerta.add(panelNombre);

		JLabel lblNombre = new JLabel("Nombre: ");
		lblNombre.setPreferredSize(new Dimension(75, 13));
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelNombre.add(lblNombre, BorderLayout.WEST);

		txtNombre = new JTextField();
		txtNombre.setMinimumSize(new Dimension(7, 0));
		panelNombre.add(txtNombre, BorderLayout.CENTER);
		txtNombre.setColumns(10);

		Component verticalStrut_1 = Box.createVerticalStrut(20);
		verticalStrut_1.setPreferredSize(new Dimension(0, 30));
		panelCentroAlerta.add(verticalStrut_1);

		// Panel para el campo Teléfono
		JPanel panelTelefono = new JPanel();
		panelTelefono.setLayout(new BorderLayout());
		panelTelefono.setMaximumSize(new Dimension(400, 30)); // Tamaño máximo para ajustar diseño
		panelCentroAlerta.add(panelTelefono);

		JLabel lblTelefono = new JLabel("Teléfono: ");
		lblTelefono.setPreferredSize(new Dimension(75, 13));
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelTelefono.add(lblTelefono, BorderLayout.WEST);

		txtTelefono = new JTextField();
		txtTelefono.setMinimumSize(new Dimension(7, 0));
		panelTelefono.add(txtTelefono, BorderLayout.CENTER);
		txtTelefono.setColumns(10);

		Component verticalStrut_2 = Box.createVerticalStrut(20);
		verticalStrut_2.setPreferredSize(new Dimension(0, 25));
		panelCentroAlerta.add(verticalStrut_2);

		// Panel para los botones
		JPanel panelBotones = new JPanel();
		panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // FlowLayout centrado con espaciado
		panelBotones.setMaximumSize(new Dimension(400, 40));
		panelCentroAlerta.add(panelBotones);

		JButton btnAceptar = new JButton("ACEPTAR");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean existe= Controlador.INSTANCE.esUsuarioRegistrado(txtTelefono.getText());
				if(existe) {
					ContactoIndividual c = Controlador.INSTANCE.crearContacto(txtNombre.getText(), txtTelefono.getText());
					if (c != null) {
						Controlador.INSTANCE.getUsuarioActual().addContacto(c);
						JOptionPane.showMessageDialog(frame, "Contacto añadido correctamente.", "Aviso",JOptionPane.INFORMATION_MESSAGE);
						VentanaChat.actualizarListaContactos();

					} else {
						JOptionPane.showMessageDialog(frame, "Error al añadir el contacto.", "Error",JOptionPane.ERROR_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(frame,"El contacto no existe.","Aviso",JOptionPane.WARNING_MESSAGE);
				}
				frame.dispose();
				frame.revalidate();
			}
		});
		panelBotones.add(btnAceptar);

		JButton btnCancelar = new JButton("CANCELAR");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		panelBotones.add(btnCancelar);
	}


	public void Mostrar() {
		this.frame.setVisible(true);
	}
}
