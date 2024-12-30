
package Aplicacion;

import java.awt.BorderLayout;
import java.awt.Color;
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

import Controlador.Controlador;

public class premiumView {

    private JFrame ventanaDescuento;
    private JComboBox<String> comboDescuentos;
    private JLabel etiquetaPrecio;
    private double precioBase = Controlador.INSTANCE.getUsuarioActual().getPrecioPremium();
    private static final String DESCUENTOMENSAJES = "Descuento Contando Mensajes";
    private static final String DESCUENTOREGISTRO = "Descuento Registro";
    private static final String SINDESCUENTO = "Sin Descuento";

    public premiumView() {
        initialize();
    }

    private void initialize() {
        ventanaDescuento = new JFrame();
        ventanaDescuento.setTitle("Seleccionar Descuento Premium");
        ventanaDescuento.setSize(400, 200);
        ventanaDescuento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaDescuento.getContentPane().setLayout(new BorderLayout());
        ventanaDescuento.setResizable(false);

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 255, 0));
        ventanaDescuento.getContentPane().add(panelTitulo, BorderLayout.NORTH);

        JLabel titulo = new JLabel("Usuario Premium");
        titulo.setFont(new Font("Arial", Font.BOLD, 14));
        panelTitulo.add(titulo);

        JPanel panelCentral = new JPanel();
        panelCentral.setBackground(new Color(0, 255, 0));
        panelCentral.setLayout(new GridLayout(2, 2, 10, 10));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ventanaDescuento.getContentPane().add(panelCentral, BorderLayout.CENTER);

        JLabel etiquetaDescuento = new JLabel("Selecciona un descuento:");
        comboDescuentos = new JComboBox<>(new String[] {
            SINDESCUENTO, DESCUENTOMENSAJES, DESCUENTOREGISTRO
        });

        panelCentral.add(etiquetaDescuento);
        panelCentral.add(comboDescuentos);

        JLabel etiquetaCantidad = new JLabel("Cantidad a pagar:");
        etiquetaPrecio = new JLabel(String.format("%.2f€", precioBase));

        panelCentral.add(etiquetaCantidad);
        panelCentral.add(etiquetaPrecio);

        JPanel panelInferior = new JPanel();
        panelInferior.setBackground(new Color(0, 255, 0));
        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
        ventanaDescuento.getContentPane().add(panelInferior, BorderLayout.SOUTH);

        JButton botonAceptar = new JButton("Aceptar");
        botonAceptar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Controlador.INSTANCE.hacerPremium();
				ventanaDescuento.dispose();
			}
		});
        
        JButton botonCancelar = new JButton("Cancelar");
        botonCancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ventanaDescuento.dispose();
			}
		});

        panelInferior.add(botonAceptar);
        panelInferior.add(botonCancelar);

        comboDescuentos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarPrecio();
            }
        });

        botonAceptar.addActionListener(e -> {
            JOptionPane.showMessageDialog(ventanaDescuento, "Descuento aplicado correctamente.", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
            ventanaDescuento.dispose();
        });

        botonCancelar.addActionListener(e -> ventanaDescuento.dispose());
    }

    private void actualizarPrecio() {
        String descuentoSeleccionado = (String) comboDescuentos.getSelectedItem();
        double precioFinal = precioBase;
        Descuento descuento = null;

        switch (descuentoSeleccionado) {
            case DESCUENTOMENSAJES:
                descuento = new DescuentoNumMensajes();
                break;
            case DESCUENTOREGISTRO:
                descuento = new DescuentoFecha();
                break;
            case SINDESCUENTO:
                descuento = null;
                break;
        }

        if (descuento != null) {
            precioFinal = descuento.getDescuento(precioBase);
        }

        etiquetaPrecio.setText(String.format("%.2f€", precioFinal));
    }

    public void show() {
        ventanaDescuento.setVisible(true);
    }
}
