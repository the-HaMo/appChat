package Aplicacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import Controlador.Controlador;

import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.ScrollPaneConstants;
import java.awt.Component;

public class BuscadorView {

    private JFrame frame;
    private JTextField telefono;
    private JTextField contacto;
    private JTextField txtMensaje;
    private Map<String, List<String>> resultadoTexto = new HashMap<String, List<String>>();
    private List<String> resultadoTlf = new LinkedList<String>(); 
    
    public BuscadorView() {
    	resultadoTexto = new HashMap<String, List<String>>();
    	resultadoTlf = new LinkedList<String>();
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        ImageIcon icono = new ImageIcon(getClass().getResource("/logo.png"));
        frame.setIconImage(icono.getImage());
        frame.setTitle("Buscador");
        
        JPanel panelBúsqueda = new JPanel();
        frame.getContentPane().add(panelBúsqueda, BorderLayout.NORTH);
        panelBúsqueda.setLayout(new BorderLayout(0, 0));
        
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(Color.GREEN);
        panelTitulo.setPreferredSize(new Dimension(10, 50));
        panelBúsqueda.add(panelTitulo, BorderLayout.NORTH);
        panelTitulo.setLayout(new BorderLayout(0, 0));

        JLabel lblTitulo = new JLabel("Buscador de Mensajes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 24)); // Fuente grande y negrita
        lblTitulo.setHorizontalAlignment(JLabel.CENTER); // Centrar el texto
        panelTitulo.add(lblTitulo, BorderLayout.CENTER);
        
        JPanel Busqueda = new JPanel();
        Busqueda.setBackground(Color.GREEN);
        Busqueda.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "buscar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelBúsqueda.add(Busqueda, BorderLayout.CENTER);
        Busqueda.setLayout(new BorderLayout(0, 10));
        
        JPanel panelCampos = new JPanel();
        panelCampos.setBackground(Color.GREEN);
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.X_AXIS)); // Usamos BoxLayout en horizontal
        Busqueda.add(panelCampos, BorderLayout.CENTER);
      
        telefono = new JTextField();
        telefono.setText("teléfono");
        telefono.setMinimumSize(new Dimension(165, 20));
        telefono.setMaximumSize(new Dimension(165, 20)); 
        telefono.setPreferredSize(new Dimension(165, 20));
        telefono.setColumns(10);
        panelCampos.add(telefono);

        panelCampos.add(Box.createHorizontalStrut(10)); 
        
        contacto = new JTextField();
        contacto.setText("contacto");
        contacto.setMinimumSize(new Dimension(165, 20));
        contacto.setMaximumSize(new Dimension(165, 20)); 
        contacto.setPreferredSize(new Dimension(165, 20)); 
        contacto.setColumns(10);
        panelCampos.add(contacto);
        
        Component horizontalStrut = Box.createHorizontalStrut(10);
        panelCampos.add(horizontalStrut);
        
        JButton buscar = new JButton("Buscar");
        buscar.setBackground(Color.WHITE);
        Busqueda.add(buscar, BorderLayout.EAST);
        
        JPanel mensaje = new JPanel();
        mensaje.setBackground(Color.GREEN);
        Busqueda.add(mensaje, BorderLayout.NORTH);
        mensaje.setLayout(new BorderLayout(0, 0));
        
        txtMensaje = new JTextField();
        txtMensaje.setText("mensaje");
        mensaje.add(txtMensaje, BorderLayout.CENTER);
        txtMensaje.setColumns(10);
        
        JPanel opciones = new JPanel();
        opciones.setBackground(Color.GREEN);
        opciones.setPreferredSize(new Dimension(10, 20));
        Busqueda.add(opciones, BorderLayout.SOUTH);
        opciones.setLayout(new BoxLayout(opciones, BoxLayout.X_AXIS));
        
        JRadioButton TelefonoEmisor = new JRadioButton("Emisor");
        TelefonoEmisor.setBackground(new Color(50, 205, 50));
        JRadioButton TelefonoReceptor = new JRadioButton("Receptor");
        TelefonoReceptor.setBackground(new Color(50, 205, 50));

        ButtonGroup group = new ButtonGroup();
        group.add(TelefonoEmisor);
        group.add(TelefonoReceptor);

        opciones.add(TelefonoEmisor);
        opciones.add(TelefonoReceptor);
        
        Component horizontalStrut_1 = Box.createHorizontalStrut(20);
        horizontalStrut_1.setMaximumSize(new Dimension(10, 32767));
        horizontalStrut_1.setMinimumSize(new Dimension(10, 0));
        horizontalStrut_1.setPreferredSize(new Dimension(10, 0));
        opciones.add(horizontalStrut_1);
        
        JRadioButton ContactoEmisor = new JRadioButton("Emisor");
        ContactoEmisor.setBackground(new Color(50, 205, 50));
        JRadioButton ContactoReceptor = new JRadioButton("Receptor");
        ContactoReceptor.setBackground(new Color(50, 205, 50));
        ButtonGroup groupContacto = new ButtonGroup();
        groupContacto.add(ContactoEmisor);
        groupContacto.add(ContactoReceptor);
        
        opciones.add(ContactoReceptor);
        opciones.add(ContactoEmisor);
        
        
        JPanel rellenoDerecha = new JPanel();
        rellenoDerecha.setBackground(Color.GREEN);
        rellenoDerecha.setPreferredSize(new Dimension(25, 10));
        panelBúsqueda.add(rellenoDerecha, BorderLayout.WEST);
        
        JPanel rellenoIzquierda = new JPanel();
        rellenoIzquierda.setPreferredSize(new Dimension(25, 10));
        rellenoIzquierda.setBackground(Color.GREEN);
        panelBúsqueda.add(rellenoIzquierda, BorderLayout.EAST);
        
        JPanel panelResultado = new JPanel();
        panelResultado.setBackground(Color.GREEN);
        frame.getContentPane().add(panelResultado, BorderLayout.CENTER);

        panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.Y_AXIS)); 

        // Añadir mensajes
       
        buscar.addActionListener(e -> {
            panelResultado.removeAll(); // Limpiar resultados previos

            String textoBuscado = txtMensaje.getText();
            String telefonoBuscado = telefono.getText();

            resultadoTexto = Controlador.INSTANCE.resultadoTexto(textoBuscado);
            resultadoTlf = Controlador.INSTANCE.resultadoTelefono(telefonoBuscado);
            
            System.out.println(resultadoTexto.size());
            
            boolean buscarPorMensaje = !textoBuscado.isEmpty();
            boolean buscarPorTelefono = !telefonoBuscado.isEmpty();
            
            if (!buscarPorMensaje && !buscarPorTelefono) {
                // Si ambos campos están vacíos, no hacer nada
                JLabel mensajeError = new JLabel("Por favor, ingrese un mensaje o un número de teléfono.");
                panelResultado.add(mensajeError);
            } else {
                resultadoTexto.forEach((receptorYemisor, mensajes) -> {
                	String[] partes = receptorYemisor.split("->");
                	String emisor = partes[0].trim();
                	String receptor = partes[1].trim();
                	String tlfReceptor = Controlador.INSTANCE.getTelefonoPorNombre(receptor);
                     if ((!buscarPorTelefono || resultadoTlf.contains(tlfReceptor)) && (!buscarPorMensaje || !mensajes.isEmpty())) {
                        for (String mensajeTexto : mensajes) {
                        	System.out.println(mensajeTexto);
                            JPanel message = new JPanel();
                            message.setLayout(new BorderLayout());
                            message.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                            JLabel senderLabel = new JLabel("Emisor: " + emisor);
                            JLabel receiverLabel = new JLabel("Receptor: " + receptor, SwingConstants.RIGHT);

                            JTextArea messageContent = new JTextArea(mensajeTexto);
                            messageContent.setLineWrap(true);
                            messageContent.setWrapStyleWord(true);
                            messageContent.setEditable(false);

                            message.add(senderLabel, BorderLayout.WEST);
                            message.add(new JScrollPane(messageContent), BorderLayout.CENTER);
                            message.add(receiverLabel, BorderLayout.EAST);

                            panelResultado.add(message);
                        }
                    }
                });
            }

            panelResultado.revalidate();  // Actualizar el layout
            panelResultado.repaint();     // Repintar la interfaz
        });

        
        // Scroll pane para los mensajes
        JScrollPane scrollPane = new JScrollPane(panelResultado);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        
        JPanel rellenoIzq = new JPanel();
        rellenoIzq.setPreferredSize(new Dimension(25, 10));
        rellenoIzq.setBackground(Color.GREEN);
        frame.getContentPane().add(rellenoIzq, BorderLayout.WEST);
        rellenoIzq.setLayout(new BorderLayout(0, 0));
        
        JPanel rellenoDer = new JPanel();
        rellenoDer.setPreferredSize(new Dimension(25, 10));
        rellenoDer.setBackground(Color.GREEN);
        frame.getContentPane().add(rellenoDer, BorderLayout.EAST);
        rellenoDer.setLayout(new BorderLayout(0, 0));
        
        JPanel rellenoSur = new JPanel();
        rellenoSur.setBackground(Color.GREEN);
        rellenoSur.setPreferredSize(new Dimension(10, 25));
        frame.getContentPane().add(rellenoSur, BorderLayout.SOUTH);
        rellenoSur.setLayout(new BorderLayout(0, 0));
    }
    
    public void show() {
    	this.frame.setVisible(true);
    }
    
    public void close() {
    	this.frame.dispose();
    }
}