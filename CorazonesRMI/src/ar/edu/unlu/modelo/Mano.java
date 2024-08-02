package ar.edu.unlu.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Mano implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// *************************************************************
	//                        CONSTANTES
	// *************************************************************
	
	//Constantes del valor de las cartas
	private final int DAMADEPICAS = 13;
	private final int CORAZONES = 1;
	private final int OTRAS = 0;
	
	//Cartas que puede haber en mesa
	public final int cartasEnMesa = 4;
	
	// *************************************************************
	//                        ATRIBUTOS
	// *************************************************************
	
	//Variables estatica que cuenta las jugadas
	private static int contadorJugadas = 0; 
	
	//Numero de jugada
	private int numeroJugada;

	//Vector aparedeado para determinar el jugador con la carta que tiro
	private Jugador[] jugadores; 
	private Carta[] cartasJugadas;
	
	private Carta primeraCartaJugada;
	private Jugador jugadorPerdedor;
	
	// *************************************************************
	//                       CONSTRUCTOR
	// *************************************************************
	
	public Mano(Jugador[] jugadores) {
		this.jugadores = jugadores;
		cartasJugadas = new Carta[cartasEnMesa];
		contadorJugadas++;
		this.numeroJugada = contadorJugadas;
	}
	
	// *************************************************************
	//                       COMPORTAMIENTO
	// *************************************************************
	
	public boolean tirarCartaEnMesa(int turnoProx, Carta cartaEnJuego, boolean puedeTirarOtraCarta, boolean corazonesRotos) {
		boolean isCartaValida = true;
		//Si es la primera carta, existes dos casos
		if(primeraCarta()) {
			//El primero, que sea cualquier carta que no sea de corazones: Eso es valido
			//El segundo, que sea una carta de corazones y quiera iniciar jugada sin que esten los corazones rotos: Eso es invalido
			if(cartaEnJuego.getPalo() == Palo.CORAZONES && !corazonesRotos) {
				isCartaValida = false;
			}else {primeraCartaJugada = cartaEnJuego;}
		}
		
		//El en caso de que no se la primera carta, analizo si la carta que tiro es
		//del mismo palo de la que esta en la mesa, o si puede tirar otra carta de su mano 
		else if ((puedeTirarOtraCarta) && (cartaEnJuego.getPalo() != primeraCartaJugada.getPalo())){
			isCartaValida = false;
		}
		
		//Si es una carta valida, la mando a la mesa
		if (isCartaValida){
			this.cartasJugadas[turnoProx] = cartaEnJuego;
		}
		
		return isCartaValida;
	}
	
	//Metodo que determina si es la primera carta jugada: Es fundamental ya que el palo de esta determinada cual es la carta mas alta
	private boolean primeraCarta() {
		boolean vacio = true;
		int pos = 0;
		while (vacio && pos < cartasJugadas.length) { //Busco en la mesa si hay alguna carta jugada
			if (cartasJugadas[pos] != null){
				vacio = false;
			}
			pos++;
		}
		return vacio;
	}
	
	//Metodo que determina el ganador de esta jugada
	public int determinarPerdedor() {
		//Obtengo palo y valor de la primer carta jugada
		Palo paloMejorCarta = primeraCartaJugada.getPalo();
		int valorMejorCarta = primeraCartaJugada.getValor();
		
		int posicionPerdedor = 0;
		
		//Recorro las 4 cartas en mesa y comparto
		for (int cartaJugada = 0; cartaJugada < cartasEnMesa; cartaJugada++) {
			
			//Si hay una carta del mismo palo que sobrepase a la mejor que esta en mesa, entonces esa es la mejor
			if (cartasJugadas[cartaJugada].getPalo() == paloMejorCarta
					&& cartasJugadas[cartaJugada].getValor() >= valorMejorCarta) {
				paloMejorCarta = cartasJugadas[cartaJugada].getPalo();
				valorMejorCarta = cartasJugadas[cartaJugada].getValor();
				posicionPerdedor = cartaJugada;
			}
		}
		contarPuntos(posicionPerdedor); //Agrego los puntos al perdedor
		jugadorPerdedor = jugadores[posicionPerdedor]; //Retorno posicion del ganador
		return posicionPerdedor;
	}
	
	//Metodo que le agrega los puntos al jugador perdedor
	private void contarPuntos(int posPerdedorJugadas) {
		for (Carta cartaConseguida : cartasJugadas) {
			jugadores[posPerdedorJugadas].agregarPuntaje(puntajeCarta(cartaConseguida));
		}
	}
	
	//Metodo privado que determina el puntaje en el juego Corazones
	private int puntajeCarta(Carta carta) {
		int puntaje;
		//Si la carta es de corazones, entonces vale 1 punto
		if (carta.getPalo() == Palo.CORAZONES){
			puntaje = CORAZONES;
		//En cambio, si es la dama de picas, vale 13 puntos
		} else if (carta.getPalo() == Palo.PICAS && carta.getValor() == 12) {
			puntaje = DAMADEPICAS;
		//Sino, la carta no vale nada en este juego
		} else {
			puntaje = OTRAS;
		}
		return puntaje;
	}
	
    // Método para reiniciar el contador de jugadas
    public static void reiniciarContadorJugadas() {
        contadorJugadas = 0;
    }
    
	// *************************************************************
	//                       GETTERS
	// *************************************************************

    //Metodo que muestra las cartas jugadas en mesa
	public String getCartasJugadas() {
		String s = "\n";
		for (int i = 0; i < cartasJugadas.length; i++) {
			if (cartasJugadas[i] != null) {
				s += (jugadores[i].getNombre()) + " -> " + cartasJugadas[i].mostrarCarta() + "\n";
			} else {
				s += (jugadores[i].getNombre()) + " -> Ninguna" + "\n" ;
			}
		}
		return s;
	}
	
	//Metodo que me dice el numero de jugada (determinante porque si es la primera comienza distinto)
	public String getNumeroJugada() {
		return String.valueOf(this.numeroJugada);
	}

	//Metodo que me dice el nombre del jugador perdedor
	public String getPerdedorJugada() {
		return this.jugadorPerdedor.getNombre();
	}
	
	public Carta getPrimeraCarta() {
		return this.primeraCartaJugada;
	}
	
	
	

	
	

}
