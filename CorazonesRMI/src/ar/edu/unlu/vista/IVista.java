package ar.edu.unlu.vista;

import java.rmi.RemoteException;

import ar.edu.unlu.controlador.Controlador;

public interface IVista {

	//Menu principal del programa
	void mostrarMenu() throws RemoteException;

	//Iniciar
	void iniciar() throws RemoteException;

	void pedirCarta() throws RemoteException;

	void mostrarGanadorJugada() throws RemoteException;

	void jugador2deTrebol() throws RemoteException;

	void pasajeDeCartas() throws RemoteException;

	void pedirCartaPasaje() throws RemoteException;

	void corazonesRotos() throws RemoteException;

	void cartaTiradaIncorrecta() throws RemoteException;

	void cartaTiradaIncorrectaCorazones() throws RemoteException;

	void ganadorJuego() throws RemoteException;

	void finDeRonda() throws RemoteException;

	void finPasajeDeCartas() throws RemoteException;

	//Metodo que setea el controlador
	void setControlador(Controlador controlador) throws RemoteException;

}