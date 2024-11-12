package Aplicacion;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;

import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.TitledBorder;

import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;

public class Mensajeria {

    private JFrame frame;
   

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Mensajeria window = new Mensajeria();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Mensajeria() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.getContentPane().setBackground(new Color(0, 255, 127));
        frame.setBounds(100, 100, 820, 700);
        frame.setTitle("APP CHAT");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon icono = new ImageIcon(getClass().getResource("/logo.png")); // LOGO
        frame.setIconImage(icono.getImage()); // LOGO

        JPanel panel_MensajeriaNORTE = new JPanel();
        panel_MensajeriaNORTE.setOpaque(false);
        panel_MensajeriaNORTE.setPreferredSize(new Dimension(10, 80));
        frame.getContentPane().add(panel_MensajeriaNORTE, BorderLayout.NORTH);

        JPanel listaArribaMensajeria = new JPanel(new FlowLayout(FlowLayout.LEFT));
        listaArribaMensajeria.setOpaque(false);
        listaArribaMensajeria.setPreferredSize(new Dimension(820, 70));
        panel_MensajeriaNORTE.add(listaArribaMensajeria);

        Component horizontalStrut = Box.createHorizontalStrut(20);
        horizontalStrut.setPreferredSize(new Dimension(5, 0));
        horizontalStrut.setMinimumSize(new Dimension(5, 0));
        listaArribaMensajeria.add(horizontalStrut);

        JComboBox comboBox = new JComboBox(); // busqieda para contacto//////////////////////////////////////////77

        comboBox.setPreferredSize(new Dimension(120, 21));
        listaArribaMensajeria.add(comboBox);

        // Ajustar la imagen FLECHA al botonCargaConv/////////////////////////////////////////////////////////77
        JButton btnCargarConv = new JButton();
        btnCargarConv.setIcon(new ImageIcon(getClass().getResource("/flecha.png")));
        btnCargarConv.setMinimumSize(new Dimension(60, 60));
        btnCargarConv.setMaximumSize(new Dimension(60, 60));
        btnCargarConv.setPreferredSize(new Dimension(60, 60));
        btnCargarConv.setAlignmentY(JComponent.TOP_ALIGNMENT);
        listaArribaMensajeria.add(btnCargarConv);

        // Ajustar imagen al BOTON !!!/////////////////////////////////////////////////////////////////////7
        JButton btnBusqueda = new JButton();
        btnBusqueda.setPreferredSize(new Dimension(60, 60));
        btnBusqueda.setMinimumSize(new Dimension(60, 60));
        btnBusqueda.setMaximumSize(new Dimension(60, 60));
        ImageIcon lupa = new ImageIcon(getClass().getResource("/lupa.png"));
        btnBusqueda.setIcon(lupa);

        listaArribaMensajeria.add(btnBusqueda);

        ImageIcon contc = new ImageIcon(getClass().getResource("/contacto.png"));
        JButton btnContactos = new JButton("CONTACTOS", contc);
        btnContactos.setHorizontalAlignment(SwingConstants.LEFT);
        btnContactos.setPreferredSize(new Dimension(180, 60));
        btnContactos.setMaximumSize(new Dimension(180, 60));
        btnContactos.setMinimumSize(new Dimension(180, 60));
        listaArribaMensajeria.add(btnContactos);

        ImageIcon corona = new ImageIcon(getClass().getResource("/corona.png"));
        JButton btnPremium = new JButton("PREMIUM", corona);
        btnPremium.setPreferredSize(new Dimension(160, 60));
        btnPremium.setMaximumSize(new Dimension(160, 60));
        btnPremium.setMinimumSize(new Dimension(160, 60));
        btnPremium.setHorizontalAlignment(SwingConstants.LEFT);
        listaArribaMensajeria.add(btnPremium);

        JPanel panel_usuario = new JPanel();
        panel_usuario.setPreferredSize(new Dimension(180, 60));
        listaArribaMensajeria.add(panel_usuario);

        JPanel panel_MensajeriaESTE = new JPanel();
        panel_MensajeriaESTE.setOpaque(false);
        panel_MensajeriaESTE.setPreferredSize(new Dimension(470, 350));
        frame.getContentPane().add(panel_MensajeriaESTE, BorderLayout.EAST);

        JPanel panel_MensajeriaOESTE = new JPanel();
        panel_MensajeriaOESTE.setOpaque(false);
        panel_MensajeriaOESTE.setPreferredSize(new Dimension(350, 350));
        frame.getContentPane().add(panel_MensajeriaOESTE, BorderLayout.WEST);

        Usuario usu = new Usuario("Sergio", "696918622", "hola", new ImageIcon(getClass().getResource("/mcclovin.png")), false);
        JList<Elemento> lista = new JList<Elemento>();
        DefaultListModel<Elemento> model = new DefaultListModel<Elemento>();
        model.addElement(new Elemento(usu));
        lista.setModel(model);
        lista.setCellRenderer(new ElementoListRenderer());

        JScrollPane scroll = new JScrollPane(lista);
        scroll.setPreferredSize(new Dimension(350, 570));
        scroll.setMinimumSize(new Dimension(350, 570)); // mismo tamaño que modelo
        scroll.setMaximumSize(new Dimension(350, 570));

        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panel_MensajeriaOESTE.add(scroll);

        JPanel panel_MensajeriaSUR = new JPanel();
        frame.getContentPane().add(panel_MensajeriaSUR, BorderLayout.SOUTH);

        // Agrega un JLabel al panel_MensajeriaESTE para mostrar la imagen ampliada
        JLabel lblImagenAmpliada = new JLabel();
        lblImagenAmpliada.setPreferredSize(new Dimension(460, 560)); // Ajusta el tamaño según sea necesario
        panel_MensajeriaESTE.add(lblImagenAmpliada);

        // Agrega un MouseListener a la lista para actualizar la variable selectedElement
        lista.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	if(e.getClickCount()==2) {
            		Elemento selectedElement = lista.getSelectedValue();
            		if (selectedElement != null) {
                        ImageIcon iconoOriginal = selectedElement.getFto();
                        Image imagenOriginal = iconoOriginal.getImage();
                        Image imagenAmpliada = imagenOriginal.getScaledInstance(460, 560, Image.SCALE_SMOOTH); // Ajusta el tamaño según sea necesario
                        lblImagenAmpliada.setIcon(new ImageIcon(imagenAmpliada));
                    }
            	}
                
            }
        });
    }

    void Mostrar() {
        this.frame.setVisible(true);
    }
}
