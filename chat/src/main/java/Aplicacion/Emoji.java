package Aplicacion;


import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Clases.Contacto;
import tds.BubbleText;

import java.awt.GridLayout;

public class Emoji {

    private JFrame frame;
    private Contacto contacto;
    private Chat chat;

    // Constructor para inicializar la ventana de emoticonos
    public Emoji(Contacto contacto,Chat chat) {
    	this.contacto = contacto;
    	this.chat = chat;
        initialize();
    }

    // Inicialización de la ventana de emoticonos
    private void initialize() {
        frame = new JFrame();
        
        // Configuración de la ventana
        frame.setSize(new Dimension(250, 100));  // Establece el tamaño de la ventana
        frame.setPreferredSize(new Dimension(250, 100));
        frame.setMinimumSize(new Dimension(250, 100));
        frame.setMaximumSize(new Dimension(250, 100));
        
        // Evitar que la ventana sea redimensionada y eliminar los bordes
        frame.setResizable(false);  // No se puede redimensionar
        frame.setUndecorated(true); // Eliminar el borde y los botones de la ventana
        
        // Asegurarse de que la ventana se cierre correctamente
        frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);  // No cerrar la app, solo ocultarla
        
        // Centrar la ventana en la pantalla
        frame.setLocationRelativeTo(null);
        
        // Configurar el layout del content pane
        frame.getContentPane().setLayout(new GridLayout(2, 1));  // 2 filas (una para norte y otra para sur)

        // Zona Norte (emojis 1 a 5)
        JPanel norte = new JPanel();
        norte.setLayout(new GridLayout(1, 5));  // 1 fila con 5 botones
        for (int i = 0; i < 5; i++) {
        	int emojiID = i + 1;
            JButton button = new JButton(BubbleText.getEmoji((emojiID)));
            button.setBackground(Color.WHITE);
            button.addActionListener(e -> {
            	enviarEmoji(emojiID);
            	frame.dispose();
            });
            norte.add(button);
        }
        frame.getContentPane().add(norte);  // Agregar el panel norte

        // Zona Sur (emojis 6 a 10)
        JPanel sur = new JPanel();
        sur.setLayout(new GridLayout(1, 5));  // 1 fila con 5 botones
        for (int i = 0; i < 5; i++) {
        	int emojiID = i + 6;
            JButton button = new JButton(BubbleText.getEmoji((emojiID)));
            button.setBackground(Color.WHITE);
            button.addActionListener(e -> {
            	enviarEmoji(emojiID);
            	frame.dispose();
            });
            sur.add(button);
        }
        frame.getContentPane().add(sur);  // Agregar el panel sur
    }
    
    // Método para mostrar la ventana
    public void show() {
        this.frame.setVisible(true);
    }
    
    // Método para ocultar la ventana
    public void hide() {
        this.frame.setVisible(false);
    }
    
	public void enviarEmoji(int emojiID) {
    	chat.enviarMensajeEmoji(contacto,emojiID);
    	}
    
    public JFrame getFrame() {
    	return this.frame;
    }
    
}
