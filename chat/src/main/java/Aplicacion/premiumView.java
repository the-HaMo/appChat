package Aplicacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class premiumView {
	
	private JFrame ventanaDescuento;
    private JComboBox<String> comboDescuentos;
    private JLabel etiquetaPrecio;
    private double precioBase = 100.0;
	private JFrame Descuentos;

	public premiumView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
	        ventanaDescuento = new JFrame();
	        ventanaDescuento.setTitle("Seleccionar Descuento Premium");
	        ventanaDescuento.setSize(400, 200);
	        ventanaDescuento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	        ventanaDescuento.getContentPane().setLayout(new BorderLayout());
	        ventanaDescuento.setResizable(false);

	        // Panel para el título
	        JPanel panelTitulo = new JPanel();
	        panelTitulo.setBackground(Color.WHITE);
	        ventanaDescuento.getContentPane().add(panelTitulo, BorderLayout.NORTH);

	        JLabel titulo = new JLabel("Usuario Premium");
	        titulo.setFont(new Font("Arial", Font.BOLD, 14));
	        panelTitulo.add(titulo);

	        // Panel central con los controles
	        JPanel panelCentral = new JPanel();
	        panelCentral.setLayout(new GridLayout(2, 2, 10, 10));
	        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
	        ventanaDescuento.getContentPane().add(panelCentral, BorderLayout.CENTER);

	        // Etiqueta y ComboBox para seleccionar el descuento
	        JLabel etiquetaDescuento = new JLabel("Selecciona un descuento:");
	        comboDescuentos = new JComboBox<>(new String[] {
	            "Sin Descuento", "Descuento Mayores", "Descuento Estudiantes", "Descuento Especial"
	        });

	        panelCentral.add(etiquetaDescuento);
	        panelCentral.add(comboDescuentos);

	        // Etiqueta para mostrar la cantidad a pagar
	        JLabel etiquetaCantidad = new JLabel("Cantidad a pagar:");
	        etiquetaPrecio = new JLabel(String.format("%.2f€", precioBase)); // Mostrar precio inicial

	        panelCentral.add(etiquetaCantidad);
	        panelCentral.add(etiquetaPrecio);

	        // Panel inferior con botones
	        JPanel panelInferior = new JPanel();
	        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
	        ventanaDescuento.getContentPane().add(panelInferior, BorderLayout.SOUTH);

	        JButton botonAceptar = new JButton("Aceptar");
	        JButton botonCancelar = new JButton("Cancelar");

	        panelInferior.add(botonAceptar);
	        panelInferior.add(botonCancelar);

	        // Lógica de cálculo al seleccionar un descuento
	        comboDescuentos.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                actualizarPrecio();
	            }
	        });

	        // Acción para el botón Aceptar
	        botonAceptar.addActionListener(e -> {
	            JOptionPane.showMessageDialog(ventanaDescuento, "Descuento aplicado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
	            ventanaDescuento.dispose();
	        });

	        // Acción para el botón Cancelar
	        botonCancelar.addActionListener(e -> ventanaDescuento.dispose());
	    }

	    private void actualizarPrecio() {
	        String descuentoSeleccionado = (String) comboDescuentos.getSelectedItem();
	        double precioFinal = precioBase;

	        switch (descuentoSeleccionado) {
	        // Aqui seria Controlador.INSTANCE.setDescuento(precioBase)... 
	            case "Sin Descuento":
	                precioFinal = precioBase;
	                break;
	            case "Descuento Mayores":
	                precioFinal = precioBase;
	                break;
	            case "Descuento Estudiantes":
	                precioFinal = precioBase;
	                break;
	            case "Descuento Especial":
	                precioFinal = precioBase;
	                break;
	        }

	        etiquetaPrecio.setText(String.format("%.2f€", precioFinal));
	    }

	    public void show() {
	        ventanaDescuento.setVisible(true);
	    }
	    
}

