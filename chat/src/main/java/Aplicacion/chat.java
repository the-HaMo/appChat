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
import java.time.LocalDateTime;
import java.util.Date;

import javax.swing.border.TitledBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

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
        frame.setResizable(true);
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
        panelNorte.setLayout(new BorderLayout(0, 0));
        
        JPanel usuarioActual = new JPanel();
        usuarioActual.setBackground(Color.GREEN);
        usuarioActual.setPreferredSize(new Dimension(220, 10));
        panelNorte.add(usuarioActual, BorderLayout.WEST);
        usuarioActual.setLayout(new BoxLayout(usuarioActual, BoxLayout.X_AXIS));
        
        
        Usuario usu1 = new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        Elemento ele1 = new Elemento(usu1);
        ImageIcon img = usu1.getImageIcon();
        Image imgcir =  ele1.imagenCircular(img.getImage());
        JLabel perfil = new JLabel();
        perfil.setIcon(new ImageIcon(imgcir));
        JLabel saludo = new JLabel(usu1.getNombre()); // Para el salido
        
        JButton photo = new JButton();
        photo.setBackground(Color.GREEN);
        photo.setPreferredSize(new Dimension(45, 45));
        ImageIcon lupa1 = new ImageIcon(getClass().getResource("/emoji.png"));
        Image scalar21 = lupa1.getImage().getScaledInstance(35, 35, Image.SCALE_SMOOTH);
        photo.setIcon(new ImageIcon(imgcir));
        usuarioActual.add(photo);
        
        JSeparator separator = new JSeparator();
        separator.setBackground(Color.GREEN);
        separator.setMaximumSize(new Dimension(10, 2));
        separator.setPreferredSize(new Dimension(10, 2));
        usuarioActual.add(separator);
        
        JPanel panel = new JPanel();
        panel.setBackground(Color.GREEN);
        panel.setBorder(new CompoundBorder());
        usuarioActual.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel nombre = new JLabel(usu1.getNombre());
        panel.add(nombre);
        
        JLabel saludoTex = new JLabel("Hey! I'm using AppChat"); // Para el saludo
        saludoTex.setForeground(Color.BLACK);
        saludoTex.setFont(new Font("Arial", Font.PLAIN, 11));
        panel.add(saludoTex);
        
        JPanel buttons = new JPanel();
        buttons.setBackground(Color.GREEN);
        buttons.setMaximumSize(new Dimension(40, 0));
        buttons.setPreferredSize(new Dimension(300, 10));
        panelNorte.add(buttons, BorderLayout.EAST);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        

        
        JButton search = new JButton();
        search.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        search.setSize(new Dimension(75, 75));
        search.setBackground(Color.GREEN);
        search.setPreferredSize(new Dimension(65, 65));
        ImageIcon lup = new ImageIcon(getClass().getResource("/lupa.png"));
        Image scalara = lup.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        
        JSeparator separator_3 = new JSeparator();
        separator_3.setMaximumSize(new Dimension(25, 2));
        search.setIcon(new ImageIcon(scalara));
        search.setPreferredSize(new Dimension(45, 45));
        buttons.add(search);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBackground(Color.GREEN);
        separator_1.setMaximumSize(new Dimension(30, 2));
        separator_1.setPreferredSize(new Dimension(10, 2));
        buttons.add(separator_1);
        
        JButton group = new JButton();
        group.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        group.setSize(new Dimension(75, 75));
        group.setBackground(Color.GREEN);
        group.setPreferredSize(new Dimension(65, 65));
        ImageIcon grupo = new ImageIcon(getClass().getResource("/grupo.png"));
        Image groupScalar = grupo.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        group.setIcon(new ImageIcon(groupScalar));
        buttons.add(group);
        
        JSeparator separator_2 = new JSeparator();
        separator_2.setBackground(Color.GREEN);
        separator_2.setMaximumSize(new Dimension(30, 2));
        buttons.add(separator_2);
        
        JButton premium = new JButton();
        premium.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        premium.setSize(new Dimension(75, 75));
        premium.setBackground(new Color(0, 255, 0));
        premium.setPreferredSize(new Dimension(65, 65));
        ImageIcon premiumPhoto = new ImageIcon(getClass().getResource("/corona.png"));
        Image premiumScalar = premiumPhoto.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
        premium.setIcon(new ImageIcon(premiumScalar));
        buttons.add(premium);
        
        JPanel title = new JPanel();
        title.setBackground(Color.GREEN);
        panelNorte.add(title, BorderLayout.CENTER);
        
        JLabel titulo = new JLabel("AppChat");
        titulo.setFont(new Font("Tahoma", Font.BOLD, 40));
        title.add(titulo);
        
        
        
        // usuarioActual.add(perfil);
        
     
        
        
        
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
        Usuario usu = new Usuario("Moha", "625962740", "hola",null, null, "Hey! I'm using AppChat");
        Usuario usu2 =  new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        Usuario usu3 =  new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        Usuario usu4 =  new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        Usuario usu5 =  new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        Usuario usu6 = new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        Usuario usu7 =  new Usuario("Moha", "625962740", "hola","", null, "Hey! I'm using AppChat");
        
        JList<Elemento> lista = new JList<Elemento>();
        DefaultListModel<Elemento> model = new DefaultListModel<Elemento>();
        model.addElement(new Elemento(usu));
        model.addElement(ele1);
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
        chat.setSize(new Dimension(450, 570));
        chat.setPreferredSize(new Dimension(10, 565));
        chat.setMinimumSize(new Dimension(10, 550));
        chat.setMaximumSize(new Dimension(10, 550));
        chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
        chat.setBorder(new LineBorder(new Color(0, 0, 0)));
        JScrollPane scrollChat = new JScrollPane(chat);
        scrollChat.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS); 
        panelEast.add(scrollChat); 
 
        BubbleText m1 = new BubbleText(chat, "hola", Color.green, "Paco " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.SENT);
        chat.add(m1);
        BubbleText m2 = new BubbleText(chat, "hola jefe", Color.white, "Jorge " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.RECEIVED);
        chat.add(m2);
        BubbleText m3 = new BubbleText(chat, 7, Color.white, "Jorge " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.RECEIVED, 18);
        chat.add(m3);
        BubbleText m4 = new BubbleText(chat, "hola que tal bien??.", Color.green, "Paco "+ Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.SENT);
        chat.add(m4); 
        chat.scrollRectToVisible(new Rectangle(0, 400+m4.getHeight(),1,1));
        
        frame.repaint();
        frame.revalidate();
    }
    
    void Mostrar() {
        this.frame.setVisible(true);
    }
}
