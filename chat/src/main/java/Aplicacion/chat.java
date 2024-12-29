package Aplicacion;

import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import Clases.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import Controlador.Controlador;
import tds.BubbleText;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Image;
import java.awt.Rectangle;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;

public class chat {

    private JFrame frame;
    private JPanel chat;
    private JTextField Message;
    private JList<Elemento> lista;
    private DefaultListModel<Elemento> model;

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

    public chat() {
        initialize();
    }


private void initialize() {
    Usuario actual = Controlador.INSTANCE.getUsuarioActual();

    frame = new JFrame("AppChat");
    frame.setResizable(true);
    frame.getContentPane().setPreferredSize(new Dimension(750, 720));
    frame.getContentPane().setLayout(new BorderLayout(0, 0));
    frame.setBounds(100, 100, 820, 700);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ImageIcon icono = new ImageIcon(getClass().getResource("/logo.png"));
    frame.setIconImage(icono.getImage());

    JPanel panelNorte = new JPanel();
    panelNorte.setBorder(new LineBorder(new Color(0, 0, 0), 5));
    panelNorte.setBackground(Color.GREEN);
    panelNorte.setPreferredSize(new Dimension(750, 75));
    frame.getContentPane().add(panelNorte, BorderLayout.NORTH);
    panelNorte.setLayout(new BorderLayout(0, 0));

    JPanel usuarioActual = new JPanel();
    usuarioActual.setBackground(Color.GREEN);
    usuarioActual.setPreferredSize(new Dimension(220, 10));
    panelNorte.add(usuarioActual, BorderLayout.WEST);
    usuarioActual.setLayout(new BoxLayout(usuarioActual, BoxLayout.X_AXIS));

    // Profile picture
    ImageIcon img = actual.getImageIcon();
    ElementoInterfaz usuarioFactory = new UsuarioElementoFactoria(actual);
    Image imgcir = usuarioFactory.createElemento().imagenCircular(img.getImage());
    JLabel perfil = new JLabel(new ImageIcon(imgcir));
    usuarioActual.add(perfil);

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

    // Username
    JLabel nombre = new JLabel(actual.getNombre());
    panel.add(nombre);

    // Greeting
    JLabel saludoTex = new JLabel(actual.getSaludo());
    saludoTex.setForeground(Color.BLACK);
    saludoTex.setFont(new Font("Arial", Font.PLAIN, 11));
    panel.add(saludoTex);

    JPanel buttons = new JPanel();
    buttons.setBackground(Color.GREEN);
    buttons.setMaximumSize(new Dimension(40, 0));
    buttons.setPreferredSize(new Dimension(360, 10));
    panelNorte.add(buttons, BorderLayout.EAST);
    buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));

    JButton addContacto = new JButton();
    addContacto.setMinimumSize(new Dimension(65, 65));
    addContacto.setMaximumSize(new Dimension(65, 65));
    addContacto.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    addContacto.setSize(new Dimension(75, 75));
    addContacto.setBackground(new Color(0, 255, 0));
    addContacto.setPreferredSize(new Dimension(65, 65));
    ImageIcon addContactoPhoto = new ImageIcon(getClass().getResource("/addUsuario.png"));
    Image addContactoScalar = addContactoPhoto.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
    addContacto.setIcon(new ImageIcon(addContactoScalar));
    buttons.add(addContacto);
    
    addContacto.addActionListener(e -> {
        AddContacto Contacto = new AddContacto(this);
        Contacto.Mostrar();
    });

    JSeparator separator_3 = new JSeparator();
    separator_3.setMaximumSize(new Dimension(25, 2));

    JSeparator separator_1 = new JSeparator();
    separator_1.setBackground(Color.GREEN);
    separator_1.setMaximumSize(new Dimension(10, 2));
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

    group.addActionListener( e ->{
			GrupoView grupoView = new GrupoView(this);
			grupoView.Mostrar();
	});

    JSeparator separator_2 = new JSeparator();
    separator_2.setPreferredSize(new Dimension(10, 2));
    separator_2.setBackground(Color.GREEN);
    separator_2.setMaximumSize(new Dimension(8, 2));
    buttons.add(separator_2);

    JButton search = new JButton();
    search.setMinimumSize(new Dimension(90, 65));
    search.setMaximumSize(new Dimension(90, 65));
    search.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    search.setSize(new Dimension(75, 75));
    search.setBackground(Color.GREEN);
    search.setPreferredSize(new Dimension(90, 65));
    ImageIcon lup = new ImageIcon(getClass().getResource("/lupa.png"));
    Image scalara = lup.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
    search.setIcon(new ImageIcon(scalara));
    search.setPreferredSize(new Dimension(45, 45));
    buttons.add(search);

    JSeparator separator_4 = new JSeparator();
    separator_4.setPreferredSize(new Dimension(10, 2));
    separator_4.setBackground(Color.GREEN);
    separator_4.setMaximumSize(new Dimension(10, 2));
    buttons.add(separator_4);

    JButton premium = new JButton();
    premium.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    premium.setSize(new Dimension(75, 75));
    premium.setBackground(new Color(0, 255, 0));
    premium.setPreferredSize(new Dimension(60, 65));
    ImageIcon premiumPhoto = new ImageIcon(getClass().getResource("/corona.png"));
    Image premiumScalar = premiumPhoto.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
    premium.setIcon(new ImageIcon(premiumScalar));
    buttons.add(premium);
    
    JSeparator separator_5 = new JSeparator();
    separator_5.setPreferredSize(new Dimension(10, 2));
    separator_5.setMaximumSize(new Dimension(10, 2));
    separator_5.setBackground(Color.GREEN);
    buttons.add(separator_5);
    
    JButton logout = new JButton();
    logout.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
    logout.setSize(new Dimension(75, 75));
    logout.setBackground(new Color(0, 255, 0));
    logout.setPreferredSize(new Dimension(60, 65));
    ImageIcon logoutPhoto = new ImageIcon(getClass().getResource("/logout.png"));
    Image logoutScalar = logoutPhoto.getImage().getScaledInstance(65, 65, Image.SCALE_SMOOTH);
    logout.setIcon(new ImageIcon(logoutScalar));
    buttons.add(logout);
    
    logout.addActionListener(e -> {
    	Controlador.INSTANCE.logout();
    	frame.dispose();
    });

    JPanel title = new JPanel();
    title.setBackground(Color.GREEN);
    panelNorte.add(title, BorderLayout.CENTER);

    JLabel titulo = new JLabel("AppChat");
    titulo.setFont(new Font("Tahoma", Font.BOLD, 40));
    title.add(titulo);

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
    

    // Lista de contactos y usuario actual
    model = new DefaultListModel<>();
    lista = new JList<>(model); // Initialize lista
    
    // Añadir los contactos que se tienen en la base de datos
    actualizarListaContactos();

    sendButton.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			String texto = Message.getText();
			Contacto c = model.getElementAt(lista.getSelectedIndex()).getContacto();
			if (!texto.equals("")) {
				enviarMensaje(c,texto);
				Message.setText("");
			}
		}
	});
    
    panelWest.setLayout(new BorderLayout(0, 0));
    lista.setCellRenderer(new ElementoListRenderer());

    // JScrollPane para la lista
    JScrollPane scroll = new JScrollPane(lista);
    scroll.setBorder(null);
    scroll.setPreferredSize(new Dimension(350, 570));
    scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
    panelWest.add(scroll);


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

    lista.addMouseListener(new MouseAdapter() {
        public void mouseClicked(MouseEvent evt) {
            if (evt.getClickCount() == 2) {
                int index = lista.locationToIndex(evt.getPoint());
                if (index >= 0) {
                    Elemento elemento = model.getElementAt(index);
                    Contacto contacto = elemento.getContacto();
                    cargarConversacion(contacto);
                    }
                }
            }
    });
    /*
    BubbleText m1 = new BubbleText(chat, "hola", Color.green, "Paco " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.SENT);
    chat.add(m1);
    BubbleText m2 = new BubbleText(chat, "hola jefe", Color.white, "Jorge " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.RECEIVED);
    chat.add(m2);
    BubbleText m3 = new BubbleText(chat, 7, Color.white, "Jorge " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.RECEIVED, 18);
    chat.add(m3);
    BubbleText m4 = new BubbleText(chat, "hola que tal bien??.", Color.green, "Paco " + Clases.Mensaje.onlyHourNow(LocalDateTime.now()), BubbleText.SENT);
    chat.add(m4);*/
    chat.scrollRectToVisible(new Rectangle(0, 420, 1, 1));

    frame.repaint();
    frame.revalidate();
}

/*
public void actualizarListaContactos() {
    // Inicializar el Map con los elementos actuales
    Map<String, Elemento> elementosMap = new HashMap<>();
    for (Elemento el : getElementos()) {
        elementosMap.put(el.getContacto() != null ? el.getContacto().getTelefono() : el.getNombre(), el);
    }
    Usuario usuarioActual = Controlador.INSTANCE.getUsuarioActual();
    List<Mensaje> mensajes = Controlador.INSTANCE.getMensajesUsuario();
    List<String> contactosT = usuarioActual.getContactosTelf();
    
    // Procesar los mensajes
    for (Mensaje m : mensajes) {
        String clave = m.getEmisor().getTelefono();
        Elemento nuevoElemento;
        if (contactosT.contains(clave)) {
            // Contacto conocido
            Contacto contacto = usuarioActual.getContacto(clave);
            contacto.addMensaje(m);
            ElementoInterfaz contactoFactory = new ContactoElementoFactoria(contacto, m);
            nuevoElemento = contactoFactory.createElemento();
        } else {
            // Contacto desconocido
            ElementoInterfaz descFactoria = new DesconocidoElementoFactoria(m.getEmisor(), m);
            nuevoElemento = descFactoria.createElemento();
        }
        // Actualizar el Map
        elementosMap.put(clave, nuevoElemento);
    }
    // Procesar los contactos que no tienen mensajes
    List<Contacto> contactos = Controlador.INSTANCE.getContactosUsuarioActual();
    for (Contacto c : contactos) {
        String clave = c.getTelefono();
        if (!elementosMap.containsKey(clave)) {
            Mensaje mensaje = Controlador.INSTANCE.getUltimoMensaje(c);
            if (mensaje == null) {
                mensaje = new Mensaje(LocalDateTime.now(), 0, ""); // Crear mensaje vacío
            }
            ElementoInterfaz contactoFactory = new ContactoElementoFactoria(c, mensaje);
            elementosMap.put(clave, contactoFactory.createElemento());
        }
    }

    // Actualizar el modelo gráfico
    model.clear();
    for (Elemento elemento : elementosMap.values()) {
        model.addElement(elemento);
    }

    lista.repaint();
    lista.revalidate();
}*/


public void enviarMensaje(Contacto contacto, String texto) {
	int selectContacto = lista.getSelectedIndex();
	Controlador.INSTANCE.enviarMensaje(contacto, texto);
	cargarConversacion(contacto);
	actualizarListaContactos();
	if (selectContacto != -1 && selectContacto < model.getSize()) {
		lista.setSelectedIndex(selectContacto);
	}
}

//aqui se supone que se carga la conversacion con un contacto ya añadido, si se intena con uno no AÑADIDO se petara
private void cargarConversacion(Contacto contacto) {
    chat.removeAll(); // Clear the current chat panel

    BubbleText bubbleText;
    List<Mensaje> mensajes = Controlador.INSTANCE.getMensajesDeContacto(contacto);
    for (Mensaje mensaje : mensajes) {
        String displayName;
        if (mensaje.getEmisor().equals(Controlador.INSTANCE.getUsuarioActual())) {
            displayName = mensaje.getEmisor().getNombre();
            bubbleText = new BubbleText(chat, mensaje.getTexto(), Color.green, displayName + " " + mensaje.getHora(), BubbleText.SENT);
        } else {
            if (Controlador.INSTANCE.getUsuarioActual().contieneContacto(mensaje.getEmisor().getTelefono())) {
                displayName = mensaje.getEmisor().getNombre();
            } else {
                displayName = mensaje.getEmisor().getTelefono();
                promptAddContact(mensaje.getEmisor());
            }
            bubbleText = new BubbleText(chat, mensaje.getTexto(), Color.white, displayName + " " + mensaje.getHora(), BubbleText.RECEIVED);
        }
        chat.add(bubbleText);
    }
    chat.revalidate();
    chat.repaint();
}

private void promptAddContact(Usuario emisor) {
    int response = JOptionPane.showConfirmDialog(frame, "Do you want to add " + emisor.getTelefono() + " as a contact?", "Add Contact", JOptionPane.YES_NO_OPTION);
    if (response == JOptionPane.YES_OPTION) {
        Controlador.INSTANCE.crearContacto(emisor.getNombre(), emisor.getTelefono());
        actualizarListaContactos();
    }
}


 public void actualizarListaContactos() {
    model.clear();
    List<Contacto> contactos = Controlador.INSTANCE.getContactosUsuarioActual();
    for (Contacto c : contactos) {
        Mensaje mensaje = Controlador.INSTANCE.getUltimoMensaje(c);
        if (mensaje == null) {
            mensaje = new Mensaje(LocalDateTime.now(), 0, ""); // Crear un mensaje vacío si no hay mensajes
        }
        ElementoInterfaz contactoFactory = new ContactoElementoFactoria(c, mensaje);
        model.addElement(contactoFactory.createElemento());
    }
    lista.repaint();
    lista.revalidate();
}

public List<Elemento> getElementos() {
	List<Elemento> elementos = new LinkedList<>();
	for (int i = 0; i < model.getSize(); i++) {
		elementos.add(model.getElementAt(i));
	}
	return elementos;
}

public void Mostrar() {
        this.frame.setVisible(true);
    }
}