package Aplicacion;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.awt.*;

import Clases.Contacto;
import Clases.ContactoIndividual;
import Clases.Grupo;
import Controlador.Controlador;

public class ModificateGrupoView {

    private JFrame ventanaGrupo;
    private Chat VentanaChat;

    public ModificateGrupoView(Chat VentanaChat) {
        this.VentanaChat = VentanaChat;
        initialize();
    }

    private void initialize() {
        ventanaGrupo = new JFrame();
        ventanaGrupo.setMinimumSize(new Dimension(720, 480));
        ventanaGrupo.setMaximumSize(new Dimension(720, 480));
        ventanaGrupo.setBounds(100, 100, 450, 300);
        ventanaGrupo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        ventanaGrupo.getContentPane().setLayout(new BorderLayout(0, 0));

        ImageIcon icono = new ImageIcon(getClass().getResource("/logo.png")); // LOGO
        ventanaGrupo.setIconImage(icono.getImage()); // LOGO
        ventanaGrupo.setTitle("Grupo");

        // Panel superior para el título principal
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.GREEN);
        panelTitulo.setPreferredSize(new Dimension(10, 50));
        ventanaGrupo.getContentPane().add(panelTitulo, BorderLayout.NORTH);
        panelTitulo.setLayout(new BorderLayout(0, 0));

        JLabel tituloPrincipal = new JLabel("Gestión de Grupo de Difusión", SwingConstants.CENTER);
        tituloPrincipal.setFont(new Font("Arial", Font.BOLD, 18));
        tituloPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelTitulo.add(tituloPrincipal, BorderLayout.CENTER);

        // Panel izquierdo con BoxLayout
        JPanel listaPanelIzquierda = new JPanel();
        listaPanelIzquierda.setLayout(new BoxLayout(listaPanelIzquierda, BoxLayout.Y_AXIS));
        listaPanelIzquierda.setPreferredSize(new Dimension(325, 400));
        listaPanelIzquierda.setBackground(Color.GREEN);
        ventanaGrupo.getContentPane().add(listaPanelIzquierda, BorderLayout.WEST);

        // Título para contactos individuales
        JLabel etiquetaContactos = new JLabel("Contactos Individuales: ", SwingConstants.CENTER);
        etiquetaContactos.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaContactos.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        listaPanelIzquierda.add(etiquetaContactos);

        DefaultListModel<Elemento> modeloContactos = new DefaultListModel<>();
        JList<Elemento> listaContactos = new JList<>(modeloContactos);
        JScrollPane scrollContactos = new JScrollPane(listaContactos);
        scrollContactos.setPreferredSize(new Dimension(300, 150));
        listaPanelIzquierda.add(scrollContactos);

        // Título para grupos existentes
        JLabel etiquetaGrupos = new JLabel("Grupos Existentes: ", SwingConstants.CENTER);
        etiquetaGrupos.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaGrupos.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        listaPanelIzquierda.add(etiquetaGrupos);

        DefaultListModel<Elemento> modeloGrupos = new DefaultListModel<>();
        JList<Elemento> listaGrupos = new JList<>(modeloGrupos);
        JScrollPane scrollGrupos = new JScrollPane(listaGrupos);
        scrollGrupos.setPreferredSize(new Dimension(300, 150));
        listaPanelIzquierda.add(scrollGrupos);

        // Panel derecho con título
        JPanel panelGrupo = new JPanel();
        panelGrupo.setMinimumSize(new Dimension(300, 200));
        panelGrupo.setMaximumSize(new Dimension(300, 200));
        panelGrupo.setBackground(Color.GREEN);
        panelGrupo.setPreferredSize(new Dimension(325, 200));
        ventanaGrupo.getContentPane().add(panelGrupo, BorderLayout.EAST);
        panelGrupo.setLayout(new BorderLayout(0, 0));

        JLabel tituloSeleccionados = new JLabel("Gestión Actual", SwingConstants.CENTER);
        tituloSeleccionados.setFont(new Font("Arial", Font.BOLD, 14));
        tituloSeleccionados.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelGrupo.add(tituloSeleccionados, BorderLayout.NORTH);

        DefaultListModel<Elemento> modeloSeleccionados = new DefaultListModel<>();
        JList<Elemento> listaSeleccionados = new JList<>(modeloSeleccionados);
        JScrollPane scrollSeleccionados = new JScrollPane(listaSeleccionados);
        scrollSeleccionados.setPreferredSize(new Dimension(300, 300));
        panelGrupo.add(scrollSeleccionados, BorderLayout.CENTER);

        // Botones entre las listas
        JPanel botonesPanel = new JPanel();
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.Y_AXIS));
        botonesPanel.setBackground(Color.GREEN);
        ventanaGrupo.getContentPane().add(botonesPanel, BorderLayout.CENTER);

        JButton botonDerecha = new JButton("\u2192");
        JButton botonIzquierda = new JButton("\u2190");

        botonDerecha.setAlignmentX(Component.CENTER_ALIGNMENT);
        botonIzquierda.setAlignmentX(Component.CENTER_ALIGNMENT);

        Component verticalGlue = Box.createVerticalGlue();
        verticalGlue.setMaximumSize(new Dimension(0, 75));
        verticalGlue.setPreferredSize(new Dimension(0, 75));
        botonesPanel.add(verticalGlue);
        botonesPanel.add(botonDerecha);
        botonesPanel.add(Box.createVerticalStrut(10));
        botonesPanel.add(botonIzquierda);
        botonesPanel.add(Box.createVerticalGlue());

        // Panel inferior con botón de confirmación
        JPanel panelInferior = new JPanel();
        panelInferior.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelInferior.setBackground(Color.GREEN);
        ventanaGrupo.getContentPane().add(panelInferior, BorderLayout.SOUTH);

        JButton botonConfirmar = new JButton("Confirmar grupo");
        JButton botonCancelar = new JButton("Cancelar");
        panelInferior.add(botonCancelar);
        panelInferior.add(botonConfirmar);
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setPreferredSize(new Dimension(40, 0));
        horizontalStrut.setMinimumSize(new Dimension(40, 0));
        horizontalStrut.setMaximumSize(new Dimension(40, 32767));
        panelInferior.add(horizontalStrut);

        // Renderizadores de lista
        listaContactos.setCellRenderer(new ElementoListRenderer());
        listaGrupos.setCellRenderer(new ElementoListRenderer());
        listaSeleccionados.setCellRenderer(new ElementoListRenderer());

        // Cargar datos en las listas de contactos
        for (Contacto contacto : Controlador.INSTANCE.getContactosUsuarioActual()) {
            if (contacto instanceof ContactoIndividual) {
                ElementoInterfaz contactoFactory = new ContactoElementoFactoria(contacto);
                modeloContactos.addElement(contactoFactory.createElementoGrupo());
            }
        }

        // Cargar datos en las listas de grupos
        for (Grupo grupo : Controlador.INSTANCE.getGruposUsuarioActual()) {
            ElementoInterfaz grupoFactory = new GrupoElementoFactoria(grupo);
            modeloGrupos.addElement(grupoFactory.createElemento());
        }

        listaGrupos.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    
                    Elemento grupoSeleccionado = listaGrupos.getSelectedValue();
                    if (grupoSeleccionado != null) {
                        Grupo grupo = (Grupo) grupoSeleccionado.getContacto(); 
                        modeloSeleccionados.clear();
                        for (Contacto contacto : grupo.getContactos()) {
                            ElementoInterfaz contactoFactory = new ContactoElementoFactoria(contacto);
                            modeloSeleccionados.addElement(contactoFactory.createElementoGrupo());
                        }
                    }
                }
            }
        });

     // Agregar un contacto al grupo
        botonDerecha.addActionListener(e -> {
            Elemento seleccionado = listaContactos.getSelectedValue();
            if (seleccionado != null && seleccionado.getContacto() instanceof ContactoIndividual) {
                // Obtener el grupo seleccionado
                Elemento grupoSeleccionado = listaGrupos.getSelectedValue();
                if (grupoSeleccionado != null) {
                    Grupo grupo = (Grupo) grupoSeleccionado.getContacto();
                    ContactoIndividual contacto = (ContactoIndividual) seleccionado.getContacto();
                    
                    // Verificar si el contacto ya está en la lista de seleccionados (gestión actual)
                    boolean contactoYaAgregado = false;
                    for (int i = 0; i < modeloSeleccionados.size(); i++) {
                        if (modeloSeleccionados.get(i).getContacto().equals(contacto)) {
                            contactoYaAgregado = true;
                            break;
                        }
                    }
                    
                    // Verificar si el contacto ya está en el grupo
                    boolean contactoEnGrupo = grupo.getContactos().contains(contacto);
                    
                    if (contactoYaAgregado) {
                        JOptionPane.showMessageDialog(ventanaGrupo, "Este contacto ya está en la lista de gestión.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else if (contactoEnGrupo) {
                        JOptionPane.showMessageDialog(ventanaGrupo, "Este contacto ya está en el grupo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    } else {
                        grupo.addContacto(contacto);
                        modeloSeleccionados.addElement(seleccionado);
                    }
                } else {
                    JOptionPane.showMessageDialog(ventanaGrupo, "Por favor, selecciona un grupo primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

     // Eliminar un contacto del grupo
        botonIzquierda.addActionListener(e -> {
            Elemento seleccionado = listaSeleccionados.getSelectedValue();
            if (seleccionado != null) {
                Elemento grupoSeleccionado = listaGrupos.getSelectedValue();
                if (grupoSeleccionado != null) {
                    Grupo grupo = (Grupo) grupoSeleccionado.getContacto();
                    ContactoIndividual contacto = (ContactoIndividual) seleccionado.getContacto();
                    grupo.removeContacto(contacto);
                    modeloSeleccionados.removeElement(seleccionado);
                } else {
                    JOptionPane.showMessageDialog(ventanaGrupo, "Por favor, selecciona un grupo primero.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        // Cancelar la ventana
        botonCancelar.addActionListener(e -> {
            this.ventanaGrupo.dispose();
        });
        
        // Mejor CrearGrupoView
        botonConfirmar.addActionListener(e -> {
        	Elemento grupoSeleccionado = listaGrupos.getSelectedValue();
            if (grupoSeleccionado == null) {
                JOptionPane.showMessageDialog(ventanaGrupo, "Por favor, selecciona o crea un grupo antes.", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
            	Controlador.INSTANCE.modificarGrupo(grupoSeleccionado.getNombreGrupo(), grupoSeleccionado.getContactosGrupo());
            	JOptionPane.showMessageDialog(ventanaGrupo, "Grupo Modificado!", "Confirmación", JOptionPane.INFORMATION_MESSAGE);
                this.ventanaGrupo.dispose();
            }
        });
    }

    public void Mostrar() {
        this.ventanaGrupo.setVisible(true);
    }
}
