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
import javax.swing.border.LineBorder;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.ScrollPaneConstants;
import java.awt.Component;

public class buscadorView {

    private JFrame frame;
    private JTextField telefono;
    private JTextField contacto;
    private JTextField txtMensaje;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    buscadorView window = new buscadorView();
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
    public buscadorView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel panelBúsqueda = new JPanel();
        frame.getContentPane().add(panelBúsqueda, BorderLayout.NORTH);
        panelBúsqueda.setLayout(new BorderLayout(0, 0));
        
        // Crear un JLabel para el título con fuente grande y centrado
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
        
        // Panel para teléfono y contacto (con BoxLayout)
        JPanel panelCampos = new JPanel();
        panelCampos.setBackground(Color.GREEN);
        panelCampos.setLayout(new BoxLayout(panelCampos, BoxLayout.X_AXIS)); // Usamos BoxLayout en horizontal
        Búsqueda.add(panelCampos, BorderLayout.CENTER);
        
        // Campo de teléfono
        telefono = new JTextField();
        telefono.setText("teléfono");
        telefono.setMinimumSize(new Dimension(165, 20));
        telefono.setMaximumSize(new Dimension(165, 20)); // Aseguramos que no se estire más allá del tamaño
        telefono.setPreferredSize(new Dimension(165, 20)); // Aseguramos que tenga un tamaño consistente
        telefono.setColumns(10);
        panelCampos.add(telefono);

        // Espaciador entre los campos
        panelCampos.add(Box.createHorizontalStrut(10)); // Separador de 10 píxeles

        // Campo de contacto
        contacto = new JTextField();
        contacto.setText("contacto");
        contacto.setMinimumSize(new Dimension(165, 20));
        contacto.setMaximumSize(new Dimension(165, 20)); // Aseguramos que no se estire más allá del tamaño
        contacto.setPreferredSize(new Dimension(165, 20)); // Aseguramos que tenga un tamaño consistente
        contacto.setColumns(10);
        panelCampos.add(contacto);
        
        Component horizontalStrut = Box.createHorizontalStrut(10);
        panelCampos.add(horizontalStrut);
        
        // Botón de búsqueda
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

        // Cambiar FlowLayout a BoxLayout para apilar mensajes uno debajo del otro
        panelResultado.setLayout(new BoxLayout(panelResultado, BoxLayout.Y_AXIS)); // Usamos BoxLayout en vertical

        // Añadir mensajes
     // Añadir mensajes
        for (int i = 0; i < 10; i++) {
            JPanel message = new JPanel();
            message.setLayout(new BorderLayout());
            message.setBorder(BorderFactory.createLineBorder(Color.GRAY));

            // Etiqueta para el emisor
            JLabel senderLabel = new JLabel("Emisor");
            senderLabel.setHorizontalAlignment(SwingConstants.LEFT);

            // Etiqueta para el receptor
            JLabel receiverLabel = new JLabel("Receptor");
            receiverLabel.setHorizontalAlignment(SwingConstants.RIGHT);

            // Contenedor del mensaje
            JPanel messagePanel = new JPanel();
            messagePanel.setLayout(new BorderLayout());
            messagePanel.setBackground(Color.WHITE);

            // Etiqueta para el contenido del mensaje
            JLabel messageContent = new JLabel("Texto del mensaje " + (i + 1));
            messageContent.setHorizontalAlignment(SwingConstants.CENTER); // Centrar horizontalmente
            messageContent.setVerticalAlignment(SwingConstants.CENTER);   // Centrar verticalmente
            messageContent.setFont(new Font("Arial", Font.PLAIN, 14));    // Ajustar fuente y tamaño
            messagePanel.add(messageContent, BorderLayout.CENTER);

            // Añadir componentes al panel principal del mensaje
            message.add(senderLabel, BorderLayout.WEST);    // Emisor a la izquierda
            message.add(messagePanel, BorderLayout.CENTER); // Mensaje centrado
            message.add(receiverLabel, BorderLayout.EAST);  // Receptor a la derecha

            panelResultado.add(message); // Añadir el mensaje al panel de resultados
        }


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
}
