package ar.edu.unlu.corazones.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ar.edu.unlu.corazones.controlador.Controlador;
import ar.edu.unlu.corazones.modelo.Jugador;
import ar.vistaGUI.CartaClickeable;
import ar.vistaGUI.CartaGrafica;
import ar.vistaGUI.Clickeable;
import net.miginfocom.swing.MigLayout;

public class VistaGrafica extends JFrame implements Clickeable, IVista{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	protected static final int BTN_AGREGAR = 1;
	protected static final int BTN_INICIAR = 2;
	protected static final int BTN_MOSTRARJ = 3;
	protected static final int BTN_ELEGIR_TRES_CARTAS = 4;
	protected static final int BTN_TIRAR = 5;
	protected static final int BTN_TERMINARMANO = 7;
	protected static final int BTN_VERREGLAS = 8;
	protected static final int BTN_CARGARPARTIDA = 9;
	protected static final int BTN_GUARDARPARTIDA = 10;
	protected static final int BTN_VERTOP5 = 11;
	private Controlador miControlador;
	private CartaClickeable[] cartasJugador;
	private Jugador miJugador;
	private JMenu mnArchivo;
	private JButton btnAgregarJugador;
	private JMenuBar menuGeneral;
	private JButton btnTirar;
	private JTextField txtJugadorActual;
	private JTextField textField;
	private JPanel panelPrincipal;
	private JPanel panelContenedorCartas;
	private JButton btnIniciarJuego;
	private JPanel areaJugadores;
	private JPanel panelBotones;
	private JTable tblJugadores;
	private JScrollPane scrollPane;
	private JMenuItem mntmVerReglas;
	private JMenuItem mntmCargarPartida;
	private JMenuItem mntmGuardarPartida;
	private JMenuItem mntmVerTop;
	private JMenu mnReglas;
	private JMenuItem mnSalir;
	private JLabel JLmensajes;
	private JPanel panelCartas;
	private TablaTop tablaTop;
	private JPanel mesa;
	private GridBagConstraints gbc_descarte;
	private JTextField txtMensajes;
	private CartaGrafica cartaReverso;
	private GridBagConstraints gbc_reverso;
	private JTextField txtJugador;
	private JPanel panelJugadorOeste;
	private JPanel panelJugadorNorte;
	private JPanel panelJugadorEste;
	private JPanel panelJugadorSur;
	/**
	 * Create the application.
	 */
	public VistaGrafica() {
	
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
        setTitle("Corazones");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 700);
		
		menuGeneral = new JMenuBar();
		setJMenuBar(menuGeneral);
		mnArchivo = new JMenu("Archivo");
		menuGeneral.add(mnArchivo);
		setJMenuBar(menuGeneral);
		
		mnReglas = new JMenu("Reglas");
		menuGeneral.add(mnReglas);
		
		mntmVerReglas = new JMenuItem("Ver reglas");
		mntmVerReglas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					seleccionarAccion(BTN_VERREGLAS);
			}
		});
		
		mnReglas.add(mntmVerReglas);
		
		mntmCargarPartida = new JMenuItem("Cargar Partida");
		mntmCargarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					seleccionarAccion(BTN_CARGARPARTIDA);
			}
		});
		
		mntmGuardarPartida = new JMenuItem("Guardar partida");
		mntmGuardarPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					seleccionarAccion(BTN_GUARDARPARTIDA);
			}
		});
		
		mntmVerTop = new JMenuItem("Ver Top 5");
		mntmVerTop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
					seleccionarAccion(BTN_VERTOP5);
			}
		});
		
		mnSalir = new JMenuItem("Salir");
		mnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);;
			}
		});
		
		mnArchivo.add(mntmCargarPartida);
		mnArchivo.add(mntmGuardarPartida);
		mnArchivo.add(mntmVerTop);
		mnArchivo.add(mnSalir);
		
		panelPrincipal = new JPanel();
		panelPrincipal.setForeground(Color.GREEN);
		panelPrincipal.setBorder(new LineBorder(new Color(13, 12, 8)));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		
		JScrollPane scrollPane = new JScrollPane();
		panelPrincipal.add(scrollPane, BorderLayout.EAST);
		
		panelBotones = new JPanel();
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[] { 113, 0 };
		gbl_panelBotones.rowHeights = new int[] { 23, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelBotones.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panelBotones.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		panelBotones.setLayout(gbl_panelBotones);
		
		JLabel lblMenuPrincipal = new JLabel("Menu Principal");
		lblMenuPrincipal.setBackground(Color.gray);
		lblMenuPrincipal.setOpaque(true);
		
		GridBagConstraints gbc_MenuPrincipal = new GridBagConstraints();
		gbc_MenuPrincipal.fill = GridBagConstraints.HORIZONTAL;
		gbc_MenuPrincipal.insets = new Insets(0, 0, 5, 0);
		gbc_MenuPrincipal.gridx = 0;
		gbc_MenuPrincipal.gridy = 1;
		lblMenuPrincipal.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblMenuPrincipal, gbc_MenuPrincipal);
		
		btnAgregarJugador = new JButton("Agregar Jugador");
		btnAgregarJugador.setEnabled(true);
		btnAgregarJugador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {	
					seleccionarAccion(BTN_AGREGAR);
			}
		});
		
		GridBagConstraints gbc_btnAgregarJugador = new GridBagConstraints();
		gbc_btnAgregarJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnAgregarJugador.insets = new Insets(0, 0, 5, 0);
		gbc_btnAgregarJugador.gridx = 0;
		gbc_btnAgregarJugador.gridy = 2;
		panelBotones.add(btnAgregarJugador, gbc_btnAgregarJugador);
		
		btnIniciarJuego = new JButton("Comenzar Partida");
		btnIniciarJuego.setEnabled(true);
		btnIniciarJuego.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					seleccionarAccion(BTN_INICIAR);
			}
		});
		
		GridBagConstraints gbc_btnIniciarJuego = new GridBagConstraints();
		gbc_btnIniciarJuego.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnIniciarJuego.insets = new Insets(0, 0, 5, 0);
		gbc_btnIniciarJuego.gridx = 0;
		gbc_btnIniciarJuego.gridy = 3;
		panelBotones.add(btnIniciarJuego, gbc_btnIniciarJuego);
		
		JLabel lblJugador = new JLabel("Jugador");
		lblJugador.setBackground(Color.gray);
		lblJugador.setOpaque(true);
		
		GridBagConstraints gbc_lblJugador = new GridBagConstraints();
		gbc_lblJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblJugador.insets = new Insets(0, 0, 5, 0);
		gbc_lblJugador.gridx = 0;
		gbc_lblJugador.gridy = 6;
		lblJugador.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblJugador, gbc_lblJugador);
		
		txtJugador = new JTextField();
		txtJugador.setFont(new Font("Tahoma", Font.BOLD, 11));
		txtJugador.setForeground(Color.BLACK);
		txtJugador.setBackground(Color.WHITE);
		txtJugador.setEditable(false);
		txtJugador.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_txtJugador = new GridBagConstraints();
		gbc_txtJugador.insets = new Insets(0, 0, 5, 0);
		gbc_txtJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtJugador.gridx = 0;
		gbc_txtJugador.gridy = 7;
		panelBotones.add(txtJugador, gbc_txtJugador);
		//txtJugador.setText(miJugador.getNombre());
		
		JLabel lblMenuJugador = new JLabel("Menu Jugador");
		lblMenuJugador.setBackground(Color.gray);
		lblMenuJugador.setOpaque(true);
		
		GridBagConstraints gbc_menuJugador = new GridBagConstraints();
		gbc_menuJugador.fill = GridBagConstraints.HORIZONTAL;
		gbc_menuJugador.insets = new Insets(0, 0, 5, 0);
		gbc_menuJugador.gridx = 0;
		gbc_menuJugador.gridy = 9;
		lblMenuJugador.setHorizontalAlignment(SwingConstants.CENTER);
		panelBotones.add(lblMenuJugador, gbc_menuJugador);
		
		
		btnTirar = new JButton("Tirar Carta");
		btnTirar.setEnabled(false);
		btnTirar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					seleccionarAccion(BTN_TIRAR);
			}
		});
		
		GridBagConstraints gbc_btnTirar = new GridBagConstraints();
		gbc_btnTirar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTirar.insets = new Insets(0, 0, 5, 0);
		gbc_btnTirar.gridx = 0;
		gbc_btnTirar.gridy = 12;
		panelBotones.add(btnTirar, gbc_btnTirar);
		gbc_btnTirar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnTirar.insets = new Insets(0, 0, 5, 0);
		gbc_btnTirar.gridx = 0;
		gbc_btnTirar.gridy = 10;
		
		scrollPane.setViewportView(panelBotones);
		panelPrincipal.add(scrollPane, BorderLayout.EAST);
		
		JPanel panelJugadorActual = new JPanel();
		GridBagLayout gbl_panelJugadorActual = new GridBagLayout();
		gbl_panelJugadorActual.columnWidths = new int[] { 180, 86, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelJugadorActual.rowHeights = new int[] { 20, 0 };
		gbl_panelJugadorActual.columnWeights = new double[] { 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gbl_panelJugadorActual.rowWeights = new double[] { 0.0, Double.MIN_VALUE };
		panelJugadorActual.setLayout(gbl_panelJugadorActual);
		
		
		txtJugadorActual = new JTextField();
		txtJugadorActual.setForeground(Color.WHITE);
		txtJugadorActual.setBackground(Color.BLUE);
		txtJugadorActual.setEditable(false);
		txtJugadorActual.setHorizontalAlignment(SwingConstants.CENTER);
		txtJugadorActual.setText("Jugador Actual");
		GridBagConstraints gbc_txtJugadorActual = new GridBagConstraints();
		gbc_txtJugadorActual.insets = new Insets(0, 0, 0, 5);
		gbc_txtJugadorActual.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtJugadorActual.gridx = 0;
		gbc_txtJugadorActual.gridy = 0;
		txtJugadorActual.setColumns(10);
		panelJugadorActual.add(txtJugadorActual, gbc_txtJugadorActual);
		
		textField = new JTextField();
		textField.setEditable(false);
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 7;
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 0;
		textField.setColumns(10);
		panelJugadorActual.add(textField, gbc_textField);
		
		panelPrincipal.add(panelJugadorActual, BorderLayout.NORTH);
		
		panelCartas = new JPanel();
		GridBagLayout gbl_panelCartas = new GridBagLayout();
		gbl_panelCartas.columnWidths = new int[] { 900, 0 };
		gbl_panelCartas.rowHeights = new int[] { 50, 0, 80, 0 };
		gbl_panelCartas.columnWeights = new double[] { 10, Double.MIN_VALUE };
		gbl_panelCartas.rowWeights = new double[] { 1.0, 1.0, 0.0, Double.MIN_VALUE };
		panelCartas.setLayout(gbl_panelCartas);
		
		txtMensajes = new JTextField();
		GridBagConstraints gbc_txtDados = new GridBagConstraints();
		txtMensajes.setEditable(false);
		gbc_txtDados.insets = new Insets(0, 0, 5, 0);
		gbc_txtDados.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDados.gridx = 0;
		gbc_txtDados.gridy = 0;
		panelCartas.add(txtMensajes, gbc_txtDados);
		txtMensajes.setColumns(10);
		
		
		panelContenedorCartas = new JPanel();
		GridBagConstraints gbc_panelContenedorCartas = new GridBagConstraints();
		gbc_panelContenedorCartas.anchor = GridBagConstraints.WEST;
		gbc_panelContenedorCartas.fill = GridBagConstraints.VERTICAL;
		gbc_panelContenedorCartas.gridx = 0;
		gbc_panelContenedorCartas.gridy = 2;
		panelContenedorCartas.setLayout(new GridLayout(0, 10, 5, 25));
		panelCartas.add(panelContenedorCartas, gbc_panelContenedorCartas);
		panelPrincipal.add(panelCartas, BorderLayout.SOUTH);
		
		buildListaJugadores();
		

		
		mesa = new JPanel();
		mesa.setBackground(new Color(0, 128, 0));
		
		cartaReverso = new CartaGrafica("reverso");
		gbc_reverso = new GridBagConstraints();
		gbc_reverso.fill = GridBagConstraints.HORIZONTAL;
		gbc_reverso.insets = new Insets(0, 0, 0, 0);
		gbc_reverso.gridx = 1;
		gbc_reverso.gridy = 1;
		
		
		
		panelPrincipal.add(mesa, BorderLayout.CENTER);
		mesa.setLayout(new BorderLayout(0, 0));
		
		panelJugadorOeste = new JPanel();
		mesa.add(panelJugadorOeste, BorderLayout.WEST);
		
		panelJugadorNorte = new JPanel();
		mesa.add(panelJugadorNorte, BorderLayout.NORTH);
		
		panelJugadorEste = new JPanel();
		mesa.add(panelJugadorEste, BorderLayout.EAST);
		
		panelJugadorSur = new JPanel();
		mesa.add(panelJugadorSur, BorderLayout.SOUTH);
	}
	
	private void seleccionarAccion(int action) {
		switch (action) {
		case BTN_VERREGLAS:
			VentanaReglas vr = new VentanaReglas();
			vr.setVisible(true);
			break;
		case BTN_CARGARPARTIDA:
			//miControlador.guardarPartida();
			break;
		case BTN_GUARDARPARTIDA:
			//miControlador.guardarPartida();
			break;
		case BTN_VERTOP5:
			verTop();
			break;
		case BTN_AGREGAR:
			agregarJugador();
			break;
		case BTN_INICIAR:
			miControlador.comenzarJuego();
			break;
		case BTN_TIRAR:
			txtMensajes.setText("");
			tiraCarta();
			break;
		case BTN_TERMINARMANO:
			//miControlador.terminaMano();
			break;
		}
	}	
	
	public void buildListaJugadores() {
		areaJugadores = new JPanel();
		panelPrincipal.add(areaJugadores, BorderLayout.WEST);
		areaJugadores.setLayout(new MigLayout("", "[150px:n:180px]", "[grow]"));
				
		darFormatoJugadores(miControlador.getJugadoresAsList());
		
	}
	
	private int posicionCartaClickeada() {
		for (int i = 0; i < cartasJugador.length; i++) {
			if (cartasJugador[i].isClickeada())
				return i;
		}
		return 0;
	}
	
	private void tiraCarta()  {
		CartaGrafica cartaTirada;
		if (manejaCartaClickeada() != null) {
		miControlador.cartaJugada(posicionCartaClickeada()+1);
			int dialogButton = JOptionPane.showConfirmDialog(null,"Terminar mano?", "En condiciones de terminar la mano",JOptionPane.YES_NO_OPTION);
			if (dialogButton == JOptionPane.YES_OPTION)
				seleccionarAccion(BTN_TERMINARMANO);
		cartaTirada = getClickedImage(miControlador.jugadorActual().getMano().get(posicionCartaClickeada()+1).mostrarCarta());
		panelJugadorSur.add(cartaTirada);
		}
				
//		if (miControlador.verEstadoJuego() != EventosCorazones.FIN_JUEGO)
//			 {
//				miControlador.cambiarTurno();
//				turno();
//			 }
//		} else
//			 notificarError("Debe seleccionar una carta antes de tirar");
		getContentPane().validate();
		getContentPane().repaint();
	}


	public void actualizarCartas()   {
		panelContenedorCartas.removeAll();
		//if (miControlador.verEstadoJuego() != EventosObservador.FIN_JUEGO) 
			mostrarCartas();
		
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	private TableModel datosJugador(List<Jugador> jugadores) {
		// Crea el conjunto de datos
		Object[][] datos;
		if (jugadores == null){
			datos = new Object[][]{}; 
		} else {
			datos = new Object[jugadores.size()][2];
			int i = 0 ;
			for(Jugador j: jugadores){
				datos[i][0] = j.getNombre();
				datos[i++][1] = j.getPuntaje();
			}
		}
	
		@SuppressWarnings("serial")
		DefaultTableModel dtm = new DefaultTableModel(
				datos,
				new String[] {
					"Jugador", "Puntos"
				}
			) {
				Class[] columnTypes = new Class[] {
					String.class, Integer.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		return dtm;
	}
	
	public void darFormatoJugadores(List<Jugador> jugadores) {
		tblJugadores = new JTable();
		tblJugadores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblJugadores.setModel(datosJugador(null));
		tblJugadores.getColumnModel().getColumn(0).setResizable(true);
		tblJugadores.getColumnModel().getColumn(0).setPreferredWidth(65);
		tblJugadores.getColumnModel().getColumn(0).setMinWidth(30);
		tblJugadores.getColumnModel().getColumn(0).setMaxWidth(85);
		tblJugadores.getColumnModel().getColumn(1).setResizable(false);
		tblJugadores.getColumnModel().getColumn(1).setMinWidth(45);
		tblJugadores.getColumnModel().getColumn(1).setMaxWidth(45);
		tblJugadores.getColumnModel().getColumn(1).setPreferredWidth(45);
		areaJugadores.setLayout(new MigLayout("", "[452px]", "[402px]"));
		tblJugadores.setModel(datosJugador(jugadores));
		
		scrollPane = new JScrollPane();
		scrollPane.setViewportView(tblJugadores);
		areaJugadores.add(scrollPane, "cell 0 0,alignx left,aligny top");
		areaJugadores.repaint();
	}
	
	
	public void actualizaPuntaje()   {
		tblJugadores.setModel(datosJugador(miControlador.getJugadoresAsList()));
	}
	

	private void agregarJugador()   {
		String nombre = "";
		txtMensajes.setText("");
		nombre = JOptionPane.showInputDialog(null,"Nombre del Jugador", "Agregar Jugador", JOptionPane.INFORMATION_MESSAGE);
		//if (nombre != null && miControlador.mostrarJugadores().size() != 4) {
			miControlador.agregarJugador(nombre);
			txtMensajes.setText(nombre);
			//miJugador = new Jugador(nombre);
		}
	//}

	@Override
	public void setControlador(Controlador controlador) {
		this.miControlador = controlador;
	}

	
	public void reiniciarMano()  {
		txtMensajes.setForeground(Color.BLUE);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText("Mano finalizada. El ganador fue " + miControlador.jugadorActual().getNombre());
		descarteVacio();
		panelContenedorCartas.removeAll();
		actualizaPuntaje();
		getContentPane().validate();
		getContentPane().repaint();
	}

	
	public void finJuego()   {
	//	mesa.remove(cartaDescarte);
		for (int i = 0; i < cartasJugador.length; i++) {
			panelContenedorCartas.remove(cartasJugador[i]);
		}
		btnTirar.setEnabled(false);
		btnAgregarJugador.setEnabled(true);
		btnIniciarJuego.setEnabled(true);
		actualizaPuntaje();
		mesa.remove(cartaReverso);
		txtMensajes.setText("");
		txtMensajes.setBackground(Color.BLACK);
		txtMensajes.setForeground(Color.GREEN);
		txtMensajes.setText("Corazones Finalizado. El ganador es " + miControlador.ganadorJuego() + " . Cantidad de rondas : " + miControlador.numeroRonda());
		getContentPane().validate();
		getContentPane().repaint();
	}

	
	public void notificarMensaje(String mensaje) {
		txtMensajes.setForeground(Color.BLACK);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText(mensaje);
		getContentPane().validate();
		getContentPane().repaint();
	}

	
	public void notificarError(String error) {
		txtMensajes.setForeground(Color.RED);
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText(error);
		getContentPane().validate();
		getContentPane().repaint();
	}

	
	public void juegoIniciado()   {
		txtMensajes.setBackground(Color.WHITE);
		txtMensajes.setText("");
		textField.setText(miControlador.jugadorActual().getNombre());
		mostrarCartas();
		turno();
		btnTirar.setEnabled(true);
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	private void mostrarCartas()   {
		cartasJugador = new CartaClickeable[miControlador.jugadorActual().cartasEnMano()];
		for (int i = 0; i < miControlador.jugadorActual().cartasEnMano(); i++) {
			cartasJugador[i] = getClickedImage(miControlador.jugadorActual().getMano().get(i).mostrarCarta());
			panelContenedorCartas.add(cartasJugador[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}

	
	public void turno()   {
		textField.setText(miControlador.jugadorActual().getNombre());
	}
	
					
	private CartaGrafica getImage(String archivo) {
		CartaGrafica imagen_1 = new CartaGrafica(archivo);
		imagen_1.setLayout(new BoxLayout(imagen_1, BoxLayout.X_AXIS));
		return imagen_1;

	}

	private CartaClickeable getClickedImage(String archivo) {
		CartaClickeable imagen_1 = new CartaClickeable(archivo, this);
		imagen_1.setLayout(new BoxLayout(imagen_1, BoxLayout.X_AXIS));
		return imagen_1;
		
	}

	private CartaClickeable manejaCartaClickeada() {
		for (int i = 0; i < cartasJugador.length; i++) {
			if (cartasJugador[i].isClickeada())
				return cartasJugador[i];
		}
		return null;	
	}
	
	@Override
	public void MouseClicked(Object objeto) {
		// TODO Auto-generated method stub
		CartaClickeable cartaClickeada = manejaCartaClickeada();
		if (cartaClickeada != null)
			for (int i = 0; i < cartasJugador.length; i++) {
				if (cartasJugador[i] != cartaClickeada)
					cartasJugador[i].reset();
			}
	}
	

	public void verTop() throws HeadlessException {
//		tablaTop = new TablaTop(miControlador.verTop());
	}


	public void cargaJuego() {
		turno();
		actualizaPuntaje();
		actualizarCartas();
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		btnTirar.setEnabled(true);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	@Override
	public void iniciar()   {
		this.setVisible(true);
		this.initialize();
		juegoIniciado();
	}

	public void cambiaTurno()   {
		actualizarCartas();
		turno();	
		btnTirar.setEnabled(true);
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void limpiaTurno()   {
		turno();
		btnTirar.setEnabled(false);
		getContentPane().validate();
		getContentPane().repaint();
	}
	
	public void deshabilitaBotones() {
		btnAgregarJugador.setEnabled(false);
		btnIniciarJuego.setEnabled(false);
		btnTirar.setEnabled(false);
	}
	
	public void mostrarCartasJugadorNoActual()   {
		Jugador jugador;
		if (miControlador.jugadorActual().getNombre().equals(miControlador.getJugadoresAsList().get(0).getNombre()))
			jugador = miControlador.getJugadoresAsList().get(1);
		else
			jugador = miControlador.getJugadoresAsList().get(0);
		cartasJugador = new CartaClickeable[jugador.cartasEnMano()];
		for (int i = 0; i < jugador.cartasEnMano(); i++) {
			cartasJugador[i] = getClickedImage(jugador.getMano().get(i).mostrarCarta());
			panelContenedorCartas.add(cartasJugador[i]);
		}
		getContentPane().validate();
		getContentPane().repaint();
	}


	
	public void muestraMesa() {
		mesa.add(cartaReverso,gbc_reverso);
	}

	public int mostrarMenuParaJugador() {
		return 0;
		// TODO Auto-generated method stub
		
	}


	public void mostrarMenu() {
		// TODO Auto-generated method stub
		
	}

	
	public void comenzarJuego() {
		// TODO Auto-generated method stub
		
	}


	public void descarteVacio() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pedirCarta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mostrarGanadorJugada() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void jugador2deTrebol() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pasajeDeCartas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pedirCartaPasaje() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void corazonesRotos() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartaTiradaIncorrecta() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cartaTiradaIncorrectaCorazones() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ganadorJuego() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finDeRonda() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void finPasajeDeCartas() {
		// TODO Auto-generated method stub
		
	}
	
}
