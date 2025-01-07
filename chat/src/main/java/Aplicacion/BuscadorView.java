package Aplicacion;

import java.awt.EventQueue;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
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
        
        JPanel Búsqueda = new JPanel();
        Búsqueda.setBackground(Color.GREEN);
        Búsqueda.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0)), "buscar", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
        panelBúsqueda.add(Búsqueda, BorderLayout.CENTER);
        Búsqueda.setLayout(new BorderLayout(0, 10));
        
        JPanel panelCampos = new JPanel();
        panelCampos.setBackground(Color.GREEN);
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.X_AXIS)); // Usamos BoxLayout en horizontal
        Búsqueda.add(panelCampos, BorderLayout.CENTER);
      
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
        Búsqueda.add(buscar, BorderLayout.EAST);
        
        JPanel mensaje = new JPanel();
        mensaje.setBackground(Color.GREEN);
        Búsqueda.add(mensaje, BorderLayout.NORTH);
        mensaje.setLayout(new BorderLayout(0, 0));
        
        txtMensaje = new JTextField();
        txtMensaje.setText("mensaje");
        mensaje.add(txtMensaje, BorderLayout.CENTER);
        txtMensaje.setColumns(10);
        
        JPanel rellenoDerecha = new JPanel();
        rellenoDerecha.setBackground(Color.GREEN);
        rellenoDerecha.setPreferredSize(new Dimension(25, 10));
        panelBúsqueda.add(rellenoDerecha, BorderLayout.WEST);
        
        JPanel rellenoIzquierda = new JPanel();
        rellenoIzquierda.setPreferredSize(new Dimension(25, 10));
        rellenoIzquierda.setBackground(Color.GREEN);
        panelBúsqueda.add(rellenoIzquierda, BorderLayout.EAST);
        
        // Panel de resultados de búsqueda
        JPanel panelResultado = new JPanel();
        panelResultado.setBackground(Color.GREEN);
        frame.getContentPane().add(panelResultado, BorderLayout.CENTER);

        panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.Y_AXIS)); 

        // Añadir mensajes
       
        buscar.addActionListener(e -> {
            panelResultado.removeAll(); // Limpiar resultados previos

            String textoBuscado = txtMensaje.getText().trim();
            String telefonoBuscado = telefono.getText().trim();

            resultadoTexto = Controlador.INSTANCE.resultadoTexto(textoBuscado);
            resultadoTlf = Controlador.INSTANCE.resultadoTelefono(telefonoBuscado);
            
            boolean buscarPorMensaje = !textoBuscado.isEmpty();
            boolean buscarPorTelefono = !telefonoBuscado.isEmpty();
            
            if (!buscarPorMensaje && !buscarPorTelefono) {
                // Si ambos campos están vacíos, no hacer nada
                JLabel mensajeError = new JLabel("Por favor, ingrese un mensaje o un número de teléfono.");
                panelResultado.add(mensajeError);
            } else {
                resultadoTexto.forEach((receptor, mensajes) -> {
                	String tlfReceptor = Controlador.INSTANCE.getTelefonoPorNombre(receptor);
                     if ((!buscarPorTelefono || resultadoTlf.contains(tlfReceptor)) && (!buscarPorMensaje || !mensajes.isEmpty())) {
                        for (String mensajeTexto : mensajes) {
                            JPanel message = new JPanel();
                            message.setLayout(new BorderLayout());
                            message.setBorder(BorderFactory.createLineBorder(Color.GRAY));

                            JLabel senderLabel = new JLabel("Emisor: " + Controlador.INSTANCE.getUsuarioActual().getNombre());
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