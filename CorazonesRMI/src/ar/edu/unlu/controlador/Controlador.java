package ar.edu.unlu.controlador;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import ar.edu.unlu.modelo.Carta;
import ar.edu.unlu.modelo.EventosObservador;
import ar.edu.unlu.modelo.IJuego;
import ar.edu.unlu.modelo.Juego;
import ar.edu.unlu.modelo.Jugador;
import ar.edu.unlu.modelo.Persistir;
import ar.edu.unlu.rmimvc.cliente.IControladorRemoto;
import ar.edu.unlu.rmimvc.observer.IObservableRemoto;
import ar.edu.unlu.vista.IVista;
import ar.edu.unlu.vista.VistaGrafica;
import ar.edu.unlu.vista.VistaConsola;

public class Controlador implements IControladorRemoto{
	
	private IJuego modelo;
	
	private IVista vista;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Controlador(IVista vista) throws RemoteException {
		this.vista = vista;
		this.vista.setControlador(this);
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	//                    DESDE LA VISTA AL MODELO
	// *************************************************************

	public void comenzarJuego() throws RemoteException {
		this.modelo.iniciarJuego();
	}
	
	public String mostrarCartasPosiblesATirar() throws RemoteException {
		return this.modelo.cartasPosiblesAJugar();
	}

	public Jugador jugadorActual() throws RemoteException {
		return this.modelo.getJugadorActual();
	}

	public void cartaJugada(int i) throws RemoteException {
		this.modelo.jugarCarta(i);
	}

	public String cartasEnMesa() throws RemoteException {
		return this.modelo.getCartasEnMesa();
	}

	public String perdedorJugada() throws RemoteException {
		return this.modelo.getPerdedorJugada();
	}

	public String numeroJugada() throws RemoteException {
		return this.modelo.getNumeroJugada();
	}
	
	public String puntajesJugadores() throws RemoteException {
		return this.modelo.getPuntajes();
	}

	public String ganadorJuego() throws RemoteException {
		return this.modelo.getGanadorJuego();
	}

	public String numeroRonda() throws RemoteException {
		return this.modelo.getRonda();
	}

	public String direccionPasaje() throws RemoteException {
		return this.modelo.getDireccionPasaje();
	}

	public void cartaJugadaPasaje(int i) throws RemoteException {
		this.modelo.jugarCarta(i);
	}
	
	public String mostrarCartasPosiblesATirarPasaje() {
		return this.modelo.cartasPosiblesAJugarPasaje();
	}
	
	public boolean isCorazonesRotos() throws RemoteException {
		return this.modelo.getCorazonesRotos();
	}
	
	public String[] listaJugadores() throws RemoteException {
		return this.modelo.getJugadores();
	}
	
	public List<Jugador> getJugadoresAsList() throws RemoteException{
		return this.modelo.listaJugadores();
	}
	
	public void agregarJugador(String nombre) {
		this.modelo.agregarJugadores(nombre);
	}
	
	public boolean cantidadJugadoresValida() throws RemoteException {
		return this.modelo.cantidadDeJugadoresValida();
	}
	
	public boolean modificarJugador(String nombre, int pos) {
		return this.modelo.reemplazarJugadores(nombre, pos);
	}
	
	public int cantidadJugadores() throws RemoteException {
		return this.modelo.cantidadJugadores();
	}


	
	// *************************************************************
	//                    DESDE MODELO A VISTA
	// *************************************************************
	
//	public void actualizar(Object evento, Observable observado) {
//		if (evento instanceof EventosObservador) {
//			switch ((EventosObservador) evento) {
//			case PEDIR_CARTA:
//				this.vista.pedirCarta();
//				break;
//			case GANADOR_JUGADA:
//				this.vista.mostrarGanadorJugada();
//				break;
//			case FIN_DE_JUEGO:
//				this.vista.ganadorJuego();
//				break;
//			case FIN_DE_RONDA:
//				this.vista.finDeRonda();
//				break;
//			case JUGO_2_DE_TREBOL:
//				this.vista.jugador2deTrebol();
//				break;
//			case PASAJE_DE_CARTAS:
//				this.vista.pasajeDeCartas();
//				break;
//			case FIN_PASAJE_DE_CARTAS:
//				this.vista.finPasajeDeCartas();
//				break;
//			case PEDIR_CARTA_PASAJE:
//				this.vista.pedirCartaPasaje();
//				break;
//			case CARTA_TIRADA_INCORRECTA:
//				this.vista.cartaTiradaIncorrecta();
//				break;
//			case CARTA_TIRADA_INCORRECTA_CORAZONES:
//				this.vista.cartaTiradaIncorrectaCorazones();
//				break;
//			case CORAZONES_ROTOS:
//				this.vista.corazonesRotos();
//				break;
//		}}
//		
//	}

	@Override
	public <T extends IObservableRemoto> void setModeloRemoto(T modeloRemoto) {
		this.modelo = (IJuego) modeloRemoto;	
	}

	@Override
	public void actualizar(IObservableRemoto arg0, Object evento) throws RemoteException {
		if (evento instanceof EventosObservador) {
			switch ((EventosObservador) evento) {
			case PEDIR_CARTA:
				this.vista.pedirCarta();
				break;
			case GANADOR_JUGADA:
				this.vista.mostrarGanadorJugada();
				break;
			case FIN_DE_JUEGO:
				this.vista.ganadorJuego();
				break;
			case FIN_DE_RONDA:
				this.vista.finDeRonda();
				break;
			case JUGO_2_DE_TREBOL:
				this.vista.jugador2deTrebol();
				break;
			case PASAJE_DE_CARTAS:
				this.vista.pasajeDeCartas();
				break;
			case FIN_PASAJE_DE_CARTAS:
				this.vista.finPasajeDeCartas();
				break;
			case PEDIR_CARTA_PASAJE:
				this.vista.pedirCartaPasaje();
				break;
			case CARTA_TIRADA_INCORRECTA:
				this.vista.cartaTiradaIncorrecta();
				break;
			case CARTA_TIRADA_INCORRECTA_CORAZONES:
				this.vista.cartaTiradaIncorrectaCorazones();
				break;
			case CORAZONES_ROTOS:
				this.vista.corazonesRotos();
				break;
		}}
		
	}

}


	
		
	
	
	
	

