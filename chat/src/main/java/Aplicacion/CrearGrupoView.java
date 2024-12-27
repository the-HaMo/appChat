package Aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.Box;
import java.awt.Component;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListCellRenderer;
import java.awt.FlowLayout;

public class CrearGrupoView {

    private JFrame frame;
    private JTextField Nombre;
    private JList<String> contactList;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CrearGrupoView window = new CrearGrupoView();
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
    public CrearGrupoView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel Titulo = new JPanel();
        Titulo.setBackground(Color.GREEN);
        Titulo.setPreferredSize(new Dimension(10, 50));
        frame.getContentPane().add(Titulo, BorderLayout.NORTH);
        Titulo.setLayout(new BorderLayout(0, 0));

        JLabel lblNewLabel = new JLabel("Creación de Grupo", SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Arial", Font.BOLD, 18));
        lblNewLabel.setMinimumSize(new Dimension(43, 50));
        lblNewLabel.setPreferredSize(new Dimension(50, 50));
        Titulo.add(lblNewLabel, BorderLayout.CENTER);

        JPanel Campos = new JPanel();
        Campos.setBackground(Color.WHITE);
        frame.getContentPane().add(Campos, BorderLayout.CENTER);
        Campos.setLayout(new BorderLayout());

        JPanel nombrePanel = new JPanel();
        nombrePanel.setBackground(Color.WHITE);
        nombrePanel.setLayout(new BoxLayout(nombrePanel, BoxLayout.X_AXIS));
        nombrePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Campos.add(nombrePanel, BorderLayout.NORTH);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Arial", Font.PLAIN, 14));
        nombrePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Espaciado inicial
        nombrePanel.add(lblNombre);

        Nombre = new JTextField();
        Nombre.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        nombrePanel.add(Box.createRigidArea(new Dimension(10, 0))); // Espaciado entre etiqueta y campo
        nombrePanel.add(Nombre);
        Nombre.setColumns(10);

        JPanel contactosPanel = new JPanel();
        contactosPanel.setMaximumSize(new Dimension(32767, 100));
        contactosPanel.setBackground(Color.WHITE);
        contactosPanel.setLayout(new BorderLayout());
        contactosPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        Campos.add(contactosPanel, BorderLayout.CENTER);

        JLabel lblContactos = new JLabel("Contactos:");
        lblContactos.setFont(new Font("Arial", Font.PLAIN, 14));
        contactosPanel.add(lblContactos, BorderLayout.NORTH);

        String[] contactos = { "Contacto 1", "Contacto 2", "Contacto 3", "Contacto 4" };
        contactList = new JList<>(contactos);

        // Custom renderer para centrar y reducir el tamaño del texto
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER); // Centra el texto
        
        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setPreferredSize(new Dimension(70, 0));
        horizontalStrut.setMinimumSize(new Dimension(70, 0));
        horizontalStrut.setMaximumSize(new Dimension(70, 32767));
        contactosPanel.add(horizontalStrut, BorderLayout.EAST);
        contactList.setCellRenderer(renderer);
        contactList.setFont(new Font("Arial", Font.PLAIN, 12)); // Reduce el tamaño de la fuente

        JScrollPane scrollPane = new JScrollPane(contactList);
        scrollPane.setMinimumSize(new Dimension(23, 50));
        scrollPane.setPreferredSize(new Dimension(53, 40));
        scrollPane.setMaximumSize(new Dimension(32767, 50));
        contactosPanel.add(scrollPane, BorderLayout.CENTER);

        JPanel fotoPanel = new JPanel();
        fotoPanel.setBackground(Color.WHITE);
        fotoPanel.setPreferredSize(new Dimension(10, 140));
        fotoPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        contactosPanel.add(fotoPanel, BorderLayout.SOUTH);
        fotoPanel.setLayout(new BorderLayout(0, 0));
        
        JPanel fotoUbi = new JPanel();
        fotoUbi.setPreferredSize(new Dimension(10, 100));
        fotoPanel.add(fotoUbi, BorderLayout.NORTH);
        fotoUbi.setLayout(new BorderLayout(0, 0));
        
        JPanel photo = new JPanel();
        fotoUbi.add(photo, BorderLayout.CENTER);
        photo.setLayout(new BorderLayout(0, 0));
        
        JPanel boton = new JPanel();
        fotoPanel.add(boton, BorderLayout.CENTER);
        boton.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 0)); // Espaciado horizontal de 10 píxeles

        JButton btnNewButton = new JButton("Cargar");
        boton.add(btnNewButton, BorderLayout.WEST);
      

        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setPreferredSize(new Dimension(70, 0));
        horizontalStrut_1.setMinimumSize(new Dimension(70, 0));
        horizontalStrut_1.setMaximumSize(new Dimension(70, 32767));
        contactosPanel.add(horizontalStrut_1, BorderLayout.WEST);

        JPanel botonesPanel = new JPanel();
        botonesPanel.setBackground(Color.WHITE);
        frame.getContentPane().add(botonesPanel, BorderLayout.SOUTH);
        botonesPanel.setLayout(new BoxLayout(botonesPanel, BoxLayout.X_AXIS));

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonesPanel.add(Box.createHorizontalGlue()); // Espaciado flexible hacia la derecha
        botonesPanel.add(btnAceptar);
        botonesPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Espaciado entre botones

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Arial", Font.PLAIN, 14));
        botonesPanel.add(btnCancelar);
        botonesPanel.add(Box.createHorizontalGlue()); // Espaciado flexible hacia la izquierda
    }
}
