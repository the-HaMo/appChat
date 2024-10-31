package Aplicacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Registro{

	private JFrame ventanaReg;
	private JPanel contenedorReg;
	private JTextField textNombre;
	private JTextField textApellidos;
	private JTextField textTelefono;
	private JPasswordField textContraseña1;
	private JPasswordField textContraseña2;
	private JTextField textFieldFecha;
	private JButton botonregistro;
	private JLabel lblImagen;

	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Registro window = new Registro();
					window.ventanaReg.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Registro() {
		initialize();
	}
	public Registro(JButton botonregistro) {
		this.botonregistro=botonregistro;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		ventanaReg = new JFrame();
		ventanaReg.getContentPane().setBackground(new Color(0, 255, 127));
		ventanaReg.setTitle("Registro");
		ventanaReg.setBounds(100, 100, 600, 400);
		ventanaReg.setPreferredSize(new Dimension(600,400));
		ventanaReg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		ImageIcon icono= new ImageIcon(getClass().getResource("/logo.png"));	//LOGO
		ventanaReg.setIconImage(icono.getImage());	//LOGO
		
		contenedorReg=(JPanel)ventanaReg.getContentPane();
		ventanaReg.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panelReg_NORTE = new JPanel();
		panelReg_NORTE.setBackground(new Color(0, 255, 127));
		panelReg_NORTE.setMinimumSize(new Dimension(600, 100));
		panelReg_NORTE.setPreferredSize(new Dimension(600, 160));
		ventanaReg.getContentPane().add(panelReg_NORTE, BorderLayout.NORTH);
		panelReg_NORTE.setLayout(new BoxLayout(panelReg_NORTE,BoxLayout.Y_AXIS));
		
		// Nombre
        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNombre.setPreferredSize(new Dimension(600, 25));
        panelNombre.setOpaque(false);
        JLabel label_2 = new JLabel("Nombre:");
        label_2.setPreferredSize(new Dimension(55, 13));
        panelNombre.add(label_2);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setPreferredSize(new Dimension(11, 0));
        panelNombre.add(horizontalStrut);
        textNombre = new JTextField(20);
        textNombre.setPreferredSize(new Dimension(150, 19));
        panelNombre.add(textNombre);
        panelReg_NORTE.add(panelNombre);

        // Apellidos
        JPanel panelApellidos = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelApellidos.setOpaque(false);
        JLabel label_1 = new JLabel("Apellidos:");
        label_1.setPreferredSize(new Dimension(55, 13));
        panelApellidos.add(label_1);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setPreferredSize(new Dimension(11, 0));
        panelApellidos.add(horizontalStrut_1);
        textApellidos = new JTextField(20);
        panelApellidos.add(textApellidos);
        panelReg_NORTE.add(panelApellidos);

        // Teléfono
        JPanel panelTelefono = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelTelefono.setOpaque(false);
        JLabel lblTelfono = new JLabel("Teléfono:");
        lblTelfono.setMinimumSize(new Dimension(55, 13));
        lblTelfono.setMaximumSize(new Dimension(55, 13));
        lblTelfono.setPreferredSize(new Dimension(55, 13));
        panelTelefono.add(lblTelfono);
        
        Component horizontalStrut_2 = Box.createHorizontalStrut(20);
        horizontalStrut_2.setPreferredSize(new Dimension(11, 0));
        panelTelefono.add(horizontalStrut_2);
        textTelefono = new JTextField(20);
        panelTelefono.add(textTelefono);
        panelReg_NORTE.add(panelTelefono);
        
     // Contraseña
        JPanel panelContraseña = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelContraseña.add(new JLabel("Contraseña:"));
        panelContraseña.setOpaque(false);
        textContraseña1 = new JPasswordField(20);
        textContraseña1.setMinimumSize(new Dimension(60, 19));
        textContraseña1.setPreferredSize(new Dimension(75, 19));
        panelContraseña.add(textContraseña1);
        panelReg_NORTE.add(panelContraseña);
     // Repetir contraseña
        JLabel lblContrasea = new JLabel("Contraseña:");
        panelContraseña.add(lblContrasea);
        textContraseña2 = new JPasswordField(20);
        textContraseña2.setMinimumSize(new Dimension(60, 19));
        textContraseña2.setPreferredSize(new Dimension(75, 19));
        panelContraseña.add(textContraseña2);

     
		
		JPanel panelReg_SUR = new JPanel();
		panelReg_SUR.setBackground(new Color(0, 255, 127));
		panelReg_SUR.setMinimumSize(new Dimension(0, 0));
		panelReg_SUR.setPreferredSize(new Dimension(600, 35));
		ventanaReg.getContentPane().add(panelReg_SUR, BorderLayout.SOUTH);
		
		JButton btnRegCancelar = new JButton("Cancelar");	//NO SE GUARDA INFO DE USUARIO
		panelReg_SUR.add(btnRegCancelar);
		btnRegCancelar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaReg.setVisible(false);
				botonregistro.setEnabled(true);
			}
		});
		
		JButton btnRegAceptar = new JButton("Aceptar");	//aqui se guarda la info de USUARIO
		panelReg_SUR.add(btnRegAceptar);
		btnRegAceptar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ventanaReg.setVisible(false);
				botonregistro.setEnabled(true);
				
			}
		});
		
		Component horizontalStrut_3 = Box.createHorizontalStrut(20);
		horizontalStrut_3.setPreferredSize(new Dimension(120, 0));
		panelReg_SUR.add(horizontalStrut_3);
		
	
		JPanel panelReg_OESTE = new JPanel();
		panelReg_OESTE.setBackground(new Color(0, 255, 127));
		panelReg_OESTE.setPreferredSize(new Dimension(290, 200));
		ventanaReg.getContentPane().add(panelReg_OESTE, BorderLayout.WEST);
		
		JPanel panelRegFecha = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelRegFecha.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelRegFecha.setOpaque(false);
		panelRegFecha.setPreferredSize(new Dimension(290, 50));
		panelReg_OESTE.add(panelRegFecha);
		
		JLabel lblFecha = new JLabel("Fecha:");
		panelRegFecha.add(lblFecha);
		
		Component horizontalStrut_4 = Box.createHorizontalStrut(20);
		horizontalStrut_4.setMinimumSize(new Dimension(0, 0));
		horizontalStrut_4.setPreferredSize(new Dimension(12, 0));
		panelRegFecha.add(horizontalStrut_4);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 13));
		dateChooser.setDateFormatString("dd-MM-yyyy");
		dateChooser.setPreferredSize(new Dimension(120, 20));
		panelRegFecha.add(dateChooser);
		
		
		JPanel panelRegSaludo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelRegSaludo.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelRegSaludo.setOpaque(false);
		panelRegSaludo.setPreferredSize(new Dimension(290, 100));
		panelReg_OESTE.add(panelRegSaludo);
		
		JLabel lblSaludo = new JLabel("Saludo:");
		panelRegSaludo.add(lblSaludo);
	
		JTextArea textAreaSaludo = new JTextArea();
		textAreaSaludo.setBorder(new LineBorder(SystemColor.activeCaptionBorder));
		textAreaSaludo.setPreferredSize(new Dimension(200, 70));
		panelRegSaludo.add(textAreaSaludo);
		
		JPanel panelReg_ESTE = new JPanel();
		panelReg_ESTE.setBackground(new Color(0, 255, 127));
		panelReg_ESTE.setPreferredSize(new Dimension(290, 200));
		ventanaReg.getContentPane().add(panelReg_ESTE, BorderLayout.CENTER);
		
		panelReg_ESTE.setLayout(new BoxLayout(panelReg_ESTE, BoxLayout.Y_AXIS));
		
		JPanel panelTituloImagen = new JPanel();
		panelTituloImagen.setPreferredSize(new Dimension(290, 8));
		panelTituloImagen.setMinimumSize(new Dimension(0, 0));
		panelTituloImagen.setOpaque(false);
		panelReg_ESTE.add(panelTituloImagen);
		
		lblImagen = new JLabel("IMAGEN:");
		lblImagen.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		panelTituloImagen.add(lblImagen);
		
		JPanel panelFoto = new JPanel();
		panelFoto.setPreferredSize(new Dimension(280, 120));
		panelFoto.setOpaque(false);
		panelReg_ESTE.add(panelFoto);
		
		JButton btnRegCargarArchivo = new JButton("Cargar Imagen");
		panelReg_SUR.add(btnRegCargarArchivo);
		
		btnRegCargarArchivo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			chooser.showOpenDialog(btnRegCargarArchivo);
			File img = chooser.getSelectedFile();
			if (img.getName().toLowerCase().endsWith(".png")) {
				ImageIcon imgPerfil = new ImageIcon(img.getAbsolutePath());
				Image imgEscalada = imgPerfil.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
				JLabel imagenLabel = new JLabel(new ImageIcon(imgEscalada));
				panelFoto.removeAll();
				panelFoto.add(imagenLabel);
				panelFoto.revalidate();
				panelFoto.repaint();
			}
			
			System.out.println(img);
				
			}
		});
		
		
		
		
	}
	
	public void Mostrar() {
		this.ventanaReg.setVisible(true);
	}
	
	public void setFecha(String fecha) {
		textFieldFecha.setText(fecha);
	}
	
}