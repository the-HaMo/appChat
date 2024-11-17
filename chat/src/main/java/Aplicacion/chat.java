package Aplicacion;

import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;
import javax.swing.ScrollPaneConstants;

import Clases.Usuario;
import tds.BubbleText;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.border.LineBorder;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class chat {

    private JFrame frame;
    private JPanel chat;
    private JTextField Message;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    chat window = new chat();
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
    public chat() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        frame = new JFrame("AppChat");
        frame.setResizable(false);
        frame.getContentPane().setPreferredSize(new Dimension(700, 720));
        frame.getContentPane().setLayout(new BorderLayout(0, 0));
        frame.setBounds(100, 100, 820, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icono = new ImageIcon(getClass().getResource("/logo.png"));
        frame.setIconImage(icono.getImage());

        JPanel panelNorte = new JPanel();
        panelNorte.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelNorte.setBackground(Color.GREEN);
        panelNorte.setPreferredSize(new Dimension(720, 75));
        frame.getContentPane().add(panelNorte, BorderLayout.NORTH);

        // Configuración del panelEste
        JPanel panelWest = new JPanel();
        panelWest.setBorder(new LineBorder(new Color(0, 0, 0), 6));
        panelWest.setBackground(Color.WHITE);
        panelWest.setPreferredSize(new Dimension(405, 670));
        frame.getContentPane().add(panelWest, BorderLayout.WEST);

      
        // Configuración del panelOeste
        JPanel panelEast = new JPanel();
        panelEast.setBorder(new LineBorder(new Color(0, 0, 0), 5));
        panelEast.setBackground(Color.WHITE);
        panelEast.setPreferredSize(new Dimension(405, 670));
        frame.getContentPane().add(panelEast, BorderLayout.EAST);
        panelEast.setLayout(new BorderLayout(0, 0));
        
        JPanel writeChat = new JPanel();
        writeChat.setBorder(new LineBorder(new Color(0, 0, 0)));
        writeChat.setPreferredSize(new Dimension(10, 35));
        panelEast.add(writeChat, BorderLayout.SOUTH);
        writeChat.setLayout(new BorderLayout(0, 0));
        
        JPanel Emoji = new JPanel(new BorderLayout());
        Emoji.setBackground(Color.WHITE);
        Emoji.setPreferredSize(new Dimension(45, 45));
        writeChat.add(Emoji, BorderLayout.WEST);
        
        JButton sendEmoji = new JButton();
        sendEmoji.setBackground(Color.WHITE);
        sendEmoji.setPreferredSize(new Dimension(45, 45));
        ImageIcon lupa = new ImageIcon(getClass().getResource("/emoji.png"));
        Image scalar = lupa.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        sendEmoji.setIcon(new ImageIcon(scalar));
        Emoji.add(sendEmoji);
        
        JPanel Send = new JPanel(new BorderLayout());
        Send.setPreferredSize(new Dimension(45, 45));
        writeChat.add(Send, BorderLayout.EAST);
       
        JButton sendButton = new JButton(); 
        sendButton.setBackground(Color.WHITE);
        sendButton.setPreferredSize(new Dimension(45, 45));
        ImageIcon flecha = new ImageIcon(getClass().getResource("/arrowG.png"));
        Image scalar1 = flecha.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        sendButton.setIcon(new ImageIcon(scalar1));
        Send.add(sendButton, BorderLayout.CENTER);
        
        Message = new JTextField();
        Message.setPreferredSize(new Dimension(10, 50));
        writeChat.add(Message, BorderLayout.CENTER);
        Message.setColumns(10);
        
        // Lista de contactos
        Usuario usu = new Usuario("Sergio", "696918622", "hola", new ImageIcon(getClass().getResource("/sinFotoContc.png")), false, "24/09/2004");
        Usuario usu1 = new Usuario("Moha", "625962740", "hola", new ImageIcon(getClass().getResource("/mcclovin.png")), false, "10/10/1999");
        Usuario usu2 = new Usuario("Sergio", "696918622", "hola", new ImageIcon(getClass().getResource("/sinFotoContc.png")), false, "24/09/2004");
        Usuario usu3 = new Usuario("Moha", "625962740", "hola", new ImageIcon(getClass().getResource("/mcclovin.png")), false, "10/10/1999");
        Usuario usu4 = new Usuario("Sergio", "696918622", "hola", new ImageIcon(getClass().getResource("/sinFotoContc.png")), false, "24/09/2004");
        Usuario usu5 = new Usuario("Moha", "625962740", "hola", new ImageIcon(getClass().getResource("/mcclovin.png")), false, "10/10/1999");
        Usuario usu6 = new Usuario("Sergio", "696918622", "hola", new ImageIcon(getClass().getResource("/sinFotoContc.png")), false, "24/09/2004");
        Usuario usu7 = new Usuario("Moha", "625962740", "hola", new ImageIcon(getClass().getResource("/mcclovin.png")), false, "10/10/1999");

        JList<Elemento> lista = new JList<Elemento>();
        DefaultListModel<Elemento> model = new DefaultListModel<Elemento>();
        model.addElement(new Elemento(usu));
        model.addElement(new Elemento(usu1));
        model.addElement(new Elemento(usu2));
        model.addElement(new Elemento(usu3));
        model.addElement(new Elemento(usu4));
        model.addElement(new Elemento(usu5));
        model.addElement(new Elemento(usu6));
        model.addElement(new Elemento(usu7));
        panelWest.setLayout(new BorderLayout(0, 0));
        lista.setModel(model);
        lista.setCellRenderer(new ElementoListRenderer());

        // JScrollPane para la lista
        JScrollPane scroll = new JScrollPane(lista);
        scroll.setBorder(null);
        scroll.setPreferredSize(new Dimension(350, 570));
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panelWest.add(scroll); // Agregar el JScrollPane en el centro
        
        
        chat = new JPanel();
        chat.setVisible(true);
        chat.setPreferredSize(new Dimension(10, 565));
        chat.setMinimumSize(new Dimension(10, 550));
        chat.setMaximumSize(new Dimension(10, 550));
        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
        chat.setBorder(new LineBorder(new Color(0, 0, 0)));
        panelEast.add(chat); 
       
        BubbleText m1 = new BubbleText(chat, "hola", Color.green, "Paco 9:30", BubbleText.SENT);
        chat.add(m1);
        BubbleText m2 = new BubbleText(chat, "hola jefe", Color.white, "Jorge 9:33", BubbleText.RECEIVED, 10);
        chat.add(m2);
        BubbleText m3 = new BubbleText(chat, 7, Color.white, "Jorge 9:33", BubbleText.RECEIVED, 18);
        chat.add(m3);
        BubbleText m4 = new BubbleText(chat, "hola que tal bien yo tambien me alegro de verte.", Color.green, "Paco 9:30", BubbleText.SENT);
        chat.add(m4);
        chat.scrollRectToVisible(new Rectangle(0,400+m4.getHeight(),1,1));
        
        JScrollPane scrollChat = new JScrollPane(chat);
        scrollChat.setPreferredSize(new Dimension(10, 550));
        scrollChat.setMinimumSize(new Dimension(10, 550));
        scrollChat.setMaximumSize(new Dimension(10, 550));
       
        panelEast.add(scrollChat);
        
       
        frame.repaint();
        frame.revalidate();
    }
    
    void Mostrar() {
        this.frame.setVisible(true);
    }
}
