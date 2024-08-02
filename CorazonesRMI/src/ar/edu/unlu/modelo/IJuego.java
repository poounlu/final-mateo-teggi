package ar.edu.unlu.modelo;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unlu.rmimvc.observer.IObservableRemoto;

public interface IJuego extends IObservableRemoto{

	void iniciarJuego() throws RemoteException;

    boolean cantidadDeJugadoresValida() throws RemoteException;

    String cartasPosiblesAJugar() throws RemoteException;

    void jugarCarta(int i) throws RemoteException;

    Jugador getJugadorActual() throws RemoteException;

    String getCartasEnMesa() throws RemoteException;

    String getPerdedorJugada() throws RemoteException;

    String getNumeroJugada() throws RemoteException;

    String getPuntajes() throws RemoteException;

    String getGanadorJuego() throws RemoteException;

    String getRonda() throws RemoteException;

    String getDireccionPasaje() throws RemoteException;

    String getCantCartasPasaje() throws RemoteException;

    boolean getCorazonesRotos() throws RemoteException;

    String[] getJugadores() throws RemoteException;

    List<Jugador> listaJugadores() throws RemoteException;

    int cantidadJugadores() throws RemoteException;

	boolean agregarJugadores(String nombre);

	String cartasPosiblesAJugarPasaje();

	boolean reemplazarJugadores(String nombre, int pos);

   // void agregarObservador(Observador observador) throws RemoteException;
}
