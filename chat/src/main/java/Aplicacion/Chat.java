package Aplicacion;

import java.awt.EventQueue;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import Clases.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

import Controlador.Controlador;
import tds.BubbleText;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.border.CompoundBorder;
import java.awt.Font;

import javax.swing.border.EtchedBorder;

public class Chat {

    private JFrame frame;
    private JPanel usuarioActualPanel;
    private JPanel chat;
    private JTextField Message;
    private JList<Elemento> lista;
    private DefaultListModel<Elemento> model;
    private Emoji emojiWindow = null;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                	Chat window = new Chat();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Chat() {
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

    usuarioActualPanel = new JPanel();
    usuarioActualPanel.setBackground(Color.GREEN);
    usuarioActualPanel.setPreferredSize(new Dimension(220, 10));
    panelNorte.add(usuarioActualPanel, BorderLayout.WEST);
    usuarioActualPanel.setLayout(new BoxLayout(usuarioActualPanel, BoxLayout.X_AXIS));

    // Profile picture
    ImageIcon img = actual.getImageIcon();
    ElementoInterfaz usuarioFactory = new UsuarioElementoFactoria(actual);
    Image imgcir = usuarioFactory.createElemento().imagenCircular(img.getImage());
    JLabel perfil = new JLabel(new ImageIcon(imgcir));
    usuarioActualPanel.add(perfil);

    JSeparator separator = new JSeparator();
    separator.setBackground(Color.GREEN);
    separator.setMaximumSize(new Dimension(10, 2));
    separator.setPreferredSize(new Dimension(10, 2));
    usuarioActualPanel.add(separator);

    JPanel panel = new JPanel();
    panel.setBackground(Color.GREEN);
    panel.setBorder(new CompoundBorder());
    usuarioActualPanel.add(panel);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

    // Username
    JLabel nombre = new JLabel(actual.getNombre());
    actualizarColorNombre(actual,nombre);
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
    
    JPopupMenu popupContacto = new JPopupMenu();
    JMenuItem añadirContacto = new JMenuItem("Añadir Contacto");
    JMenuItem modContacto = new JMenuItem("Modificar Contacto");
    popupContacto.add(añadirContacto);
    popupContacto.add(modContacto);
    
    addContacto.addActionListener(e -> {
    	popupContacto.show(addContacto, addContacto.getWidth()/2, addContacto.getHeight());
    	
    });
    
    añadirContacto.addActionListener(e -> {
        AddContacto Contacto = new AddContacto(this);
        Contacto.Mostrar();
    });
    
    modContacto.addActionListener(e -> {
    	ModificarContacto modificar=null;
    	if (lista.getSelectedIndex() != -1) {
    		if(model.getElementAt(lista.getSelectedIndex()).getContacto() instanceof ContactoIndividual) {
    			ContactoIndividual contacto = (ContactoIndividual) model.getElementAt(lista.getSelectedIndex()).getContacto();
    			modificar = new ModificarContacto(contacto,this);
    			modificar.Mostrar();
    		}else {
    			JOptionPane.showMessageDialog(null, "No puedes modificar un grupo aqui");
    		}
    	}else{
    		JOptionPane.showMessageDialog(null, "Seleccione un contacto");
    	}
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
    JPopupMenu popupMenu = new JPopupMenu();
    JMenuItem crearGrupo = new JMenuItem("Crear Grupo");
    JMenuItem modificarGrupo = new JMenuItem("Modificar Grupo");
    popupMenu.add(crearGrupo);
    popupMenu.add(modificarGrupo);

    group.addActionListener( e ->{
			popupMenu.show(group, group.getWidth()/2, group.getHeight());
	});

    crearGrupo.addActionListener(e -> {
    	CreateGrupoView grupoView = new CreateGrupoView(this);
		grupoView.Mostrar();
    });
    
    modificarGrupo.addActionListener(e -> {
    	ModificateGrupoView grupoView = new ModificateGrupoView(this);
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
    
    search.addActionListener(e -> {
    	BuscadorView window = new BuscadorView();
    	window.show();
    });

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
    JPopupMenu popupPremium = new JPopupMenu();
    JMenuItem serPremium = new JMenuItem("Hacerse Premium");
    JMenuItem PDF = new JMenuItem("Exportar Mensajes a PDF");
    JMenuItem noserPremium = new JMenuItem("Dejar de ser  Premium");
    popupPremium.add(serPremium);
    popupPremium.add(PDF);
    popupPremium.add(noserPremium);
    
    premium.addActionListener(e -> {
    	popupPremium.show(premium, premium.getWidth()/2, premium.getHeight());
    	
    });
    
	serPremium.addActionListener(e -> {
		PremiumView premiumView = new PremiumView(usuarioActualPanel, nombre);
    	premiumView.show();
	});
	
	noserPremium.addActionListener(e -> {
		if (actual.isPremium()) {
			Controlador.INSTANCE.deshacerPremium();
			actualizarColorNombre(actual, nombre);
			usuarioActualPanel.repaint();
			JOptionPane.showMessageDialog(null, "Ya no eres PREMIUM");
		}else {
			JOptionPane.showMessageDialog(null, "No eres PREMIUM");
		}
	});
	
	PDF.addActionListener(e -> {
		if(actual.isPremium()) {
			JFileChooser fileChooser = new JFileChooser();
			fileChooser.setDialogTitle("Guardar PDF");
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			int userSelection = fileChooser.showSaveDialog(frame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File fileToSave = fileChooser.getSelectedFile();
				String filePath = fileToSave.getAbsolutePath();
				if (!filePath.endsWith(".pdf")) {
					filePath += ".pdf";
				}
				if(Controlador.INSTANCE.generarPDF(filePath)) {
					JOptionPane.showMessageDialog(null, "PDF generado correctamente");
				}else {
					JOptionPane.showMessageDialog(null, "Error al generar PDF");
				}
			}
		}else{
			JOptionPane.showMessageDialog(null, "No eres PREMIUM");
		}
	});
    
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
    sendEmoji.addActionListener(e -> {
    	if (lista.getSelectedIndex() != -1) {
    		emojiWindow = new Emoji(model.getElementAt(lista.getSelectedIndex()).getContacto(), this);
    		if (emojiWindow.getFrame().isVisible()) {
                emojiWindow.hide();
            } else {
                Point locationOnScreen = Message.getLocationOnScreen();
                int emojiPanelHeight = emojiWindow.getFrame().getHeight();
                int x = (int) locationOnScreen.getX() - emojiPanelHeight / 2 + 5;
                int y = (int) locationOnScreen.getY() - emojiPanelHeight;
                emojiWindow.getFrame().setLocation(x, y);
                emojiWindow.show();
            }
        }
    });


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
			if (lista.getSelectedIndex() != -1) {
			Contacto c = model.getElementAt(lista.getSelectedIndex()).getContacto();
			if (!texto.equals("")) {
				enviarMensajeTexto(c,texto);
				Message.setText("");
			}
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
     
    frame.repaint();
    frame.revalidate();
}




public void enviarMensajeTexto(Contacto contacto, String texto) {
	int selectContacto = lista.getSelectedIndex();
	Controlador.INSTANCE.enviarMensaje(contacto, texto);
	cargarConversacion(contacto);
	actualizarListaContactos();
	if (selectContacto != -1 && selectContacto < model.getSize()) {
		lista.setSelectedIndex(selectContacto);
	}
}

public void enviarMensajeEmoji(Contacto contacto, int emoji) {
	int selectContacto = lista.getSelectedIndex();
	Controlador.INSTANCE.enviarMensaje(contacto, emoji);
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
	int heights = 0;
	List<Mensaje> mensajes = Controlador.INSTANCE.getMensajesDeContacto(contacto);
	for (Mensaje mensaje : mensajes) {
		String displayName;
		if(mensaje.getTexto() != null) {
			if (mensaje.getEmisor().equals(Controlador.INSTANCE.getUsuarioActual())) { // Si soy el emisor...
				displayName = mensaje.getEmisor().getNombre();
				bubbleText = new BubbleText(chat, mensaje.getTexto(), Color.green, displayName + " " + mensaje.getHora(), BubbleText.SENT);
				heights += bubbleText.getHeight();
			} else { // Si no lo soy
				displayName = contacto.getNombre();
				bubbleText = new BubbleText(chat, mensaje.getTexto(), Color.white, displayName + " " + mensaje.getHora(), BubbleText.RECEIVED);
				heights += bubbleText.getHeight();
			}

		}else {
			if (mensaje.getEmisor().equals(Controlador.INSTANCE.getUsuarioActual())) { // Si soy el emisor...
				displayName = mensaje.getEmisor().getNombre();
				bubbleText = new BubbleText(chat, mensaje.getEmoticono(), Color.green, displayName + " " + mensaje.getHora(), BubbleText.SENT,18);
				heights += bubbleText.getHeight();
			} else { // Si no lo soy
				displayName = contacto.getNombre();
				bubbleText = new BubbleText(chat, mensaje.getEmoticono(), Color.white, displayName + " " + mensaje.getHora(), BubbleText.RECEIVED,18);
				heights += bubbleText.getHeight();
			}
		}
		chat.add(bubbleText);
	}
	chat.setSize(new Dimension(10, heights));
    chat.setPreferredSize(new Dimension(10, heights));
    chat.setMinimumSize(new Dimension(10, heights));
    chat.setMaximumSize(new Dimension(10, heights));
	chat.scrollRectToVisible(new Rectangle(0, heights, 1, 1));
	chat.revalidate();
	chat.repaint();
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
    List<Mensaje> mensajesRecibidos = Controlador.INSTANCE.getMensajesReceptorUsuarioActual();	//mensajes de usuarios no añadidos
    for(Mensaje m: mensajesRecibidos) {
    	ContactoIndividual contacto = Controlador.INSTANCE.crearContacto("",m.getEmisor().getTelefono());
		ElementoInterfaz contactoFactory = new ContactoElementoFactoria(contacto, m);
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

private void actualizarColorNombre(Usuario usuario, JLabel nombre) {
    if (usuario.isPremium()) {
        nombre.setForeground(Color.YELLOW);
    } else {
        nombre.setForeground(Color.BLACK);
    }
    
}

public void Mostrar() {
        this.frame.setVisible(true);
    }
}
