package Aplicacion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Clases.Usuario;


public class GrupoView {

    private JFrame ventanaGrupo;
    private chat VentanaChat;


    public GrupoView(chat VentanaChat) {
    	this.VentanaChat = VentanaChat;
        initialize();
    }

    private void initialize() {
        ventanaGrupo = new JFrame();
        ventanaGrupo.setMinimumSize(new Dimension(720, 480));
        ventanaGrupo.setMaximumSize(new Dimension(720, 480));
        ventanaGrupo.setBounds(100, 100, 450, 300);
        ventanaGrupo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaGrupo.getContentPane().setLayout(new BorderLayout(0, 0));
        
        ImageIcon icono= new ImageIcon(getClass().getResource("/logo.png"));	//LOGO
		ventanaGrupo.setIconImage(icono.getImage());	//LOGO
		ventanaGrupo.setTitle("Grupo");
		
        // Panel izquierdo
        JPanel listaContactos = new JPanel();
        listaContactos.setMinimumSize(new Dimension(300, 200));
        listaContactos.setMaximumSize(new Dimension(300, 200));
        listaContactos.setBackground(Color.GREEN);
        listaContactos.setPreferredSize(new Dimension(325, 200));
        ventanaGrupo.getContentPane().add(listaContactos, BorderLayout.WEST);
        listaContactos.setLayout(new BorderLayout(0, 0));

        // Panel derecho
        JPanel panelGrupo = new JPanel();
        panelGrupo.setMinimumSize(new Dimension(300, 200));
        panelGrupo.setMaximumSize(new Dimension(300, 200));
        panelGrupo.setBackground(Color.GREEN);
        panelGrupo.setPreferredSize(new Dimension(325, 200));
        ventanaGrupo.getContentPane().add(panelGrupo, BorderLayout.EAST);
        panelGrupo.setLayout(new BorderLayout(0, 0));

        // Panel de botones
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.GREEN);
        ventanaGrupo.getContentPane().add(buttons, BorderLayout.CENTER);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));

        // Modelos para las listas
        DefaultListModel<Elemento> modelIzquierda = new DefaultListModel<>();
        DefaultListModel<Elemento> modelDerecha = new DefaultListModel<>();

        // JList para la izquierda
        JList<Elemento> listaIzquierda = new JList<>(modelIzquierda);
        JScrollPane scrollContactos = new JScrollPane(listaIzquierda);
        scrollContactos.setMinimumSize(new Dimension(300, 300));
        scrollContactos.setMaximumSize(new Dimension(300, 300));
        scrollContactos.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollContactos.setPreferredSize(new Dimension(300, 300));
        listaContactos.add(scrollContactos, BorderLayout.CENTER);

        // JList para la derecha
        JList<Elemento> listaDerecha = new JList<>(modelDerecha);
        JScrollPane scrollDerecha = new JScrollPane(listaDerecha);
        scrollDerecha.setBorder(null);
        scrollDerecha.setPreferredSize(new Dimension(300, 400));
        scrollDerecha.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panelGrupo.add(scrollDerecha, BorderLayout.CENTER);

        // Crear contactos de ejemplo
        Usuario usu = new Usuario("Moha", "625962740", "hola", null, null, "Hey! I'm using AppChat");
        Usuario usu2 = new Usuario("Ana", "123456789", "hello", null, null, "Hello there!");

        modelIzquierda.addElement(new Elemento(usu));
        modelIzquierda.addElement(new Elemento(usu2));

        // Configurar renderizador para listas
        listaIzquierda.setCellRenderer(new ElementoListRenderer());
        
        JPanel zonaButton = new JPanel();
        zonaButton.setBackground(Color.GREEN);
        zonaButton.setPreferredSize(new Dimension(50, 40));
        zonaButton.setMaximumSize(new Dimension(50, 50));
        zonaButton.setMinimumSize(new Dimension(50, 50));
        listaContactos.add(zonaButton, BorderLayout.SOUTH);
        
        listaDerecha.setCellRenderer(new ElementoListRenderer());
        
        JPanel grupoBoton = new JPanel();
        grupoBoton.setBackground(Color.GREEN);
        grupoBoton.setPreferredSize(new Dimension(40, 40));
        panelGrupo.add(grupoBoton, BorderLayout.SOUTH);
        
        JButton grupo = new JButton("Crear grupo");
        grupoBoton.add(grupo);
		

        // Botones
        JButton FlechaIzquierda = new JButton("\u2190");
        JButton FlechaDerecha = new JButton("\u2192");

        FlechaIzquierda.setAlignmentX(Component.CENTER_ALIGNMENT);
        FlechaDerecha.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons.add(Box.createVerticalGlue());
        buttons.add(FlechaIzquierda);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(FlechaDerecha);
        buttons.add(Box.createVerticalGlue());
        
        JPanel grupoDifusion = new JPanel();
        grupoDifusion.setBackground(Color.GREEN);
        grupoDifusion.setPreferredSize(new Dimension(10, 50));
        ventanaGrupo.getContentPane().add(grupoDifusion, BorderLayout.NORTH);
        grupoDifusion.setLayout(new BorderLayout(0, 0));
        
        JLabel lblNewLabel = new JLabel("Grupo de Difusión",SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        grupoDifusion.add(lblNewLabel, BorderLayout.CENTER);

        // Eventos de los botones
        FlechaDerecha.addActionListener(new ActionListener() {
        	 public void actionPerformed(ActionEvent e) {
        	        Elemento selected = listaIzquierda.getSelectedValue();
        	        if (selected != null) {
        	            // Verificar si el contacto ya está en el grupo
        	            if (!modelDerecha.contains(selected)) {
        	                modelDerecha.addElement(selected);
        	            } else {
        	                JOptionPane.showMessageDialog(
        	                    ventanaGrupo,
        	                    "El contacto ya está en el grupo.",
        	                    "Aviso",
        	                    JOptionPane.WARNING_MESSAGE
        	                );
        	            }
        	        }
        	    }
        });

        FlechaIzquierda.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Elemento selected = listaDerecha.getSelectedValue();
                if (selected != null) {
                    modelDerecha.removeElement(selected);
                }
            }
        });
    }
    
    public void Mostrar() {
        this.ventanaGrupo.setVisible(true);
    }
}
