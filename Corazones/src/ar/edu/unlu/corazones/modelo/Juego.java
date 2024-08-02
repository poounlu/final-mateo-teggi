package ar.edu.unlu.corazones.modelo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ar.edu.unlu.corazones.observer.Observable;
import ar.edu.unlu.corazones.observer.Observador;

/**
 * CLASE CORAZONES
 * Encarga de manejar todo el flujo del juego, que abarca el pasaje de cartas, control de rondas, puntajes, etc.
 * 
 */

public class Juego implements Observable {

	// *************************************************************
	// 						CONSTANTES
	// *************************************************************

	private static final int cantCartasRepartidas = 13; // TESTING (REAL 13)
	private static final int cantCartasIntercambio = 0; // TESTING (REAL 3)
	private static final int puntajeMaximo = 12; // TESTING (REAL 100)
	private static final int cantJugadores = 4;

	// *************************************************************
	// 						ATRIBUTOS
	// *************************************************************

	// Mazo del juego
	private Mazo mazo;

	// Array donde estaran los jugadores
	private Jugador[] jugadores;

	// Numero de ronda
	private int ronda;

	// Jugadas (En una ronda hay 13 jugadas)
	private List<Mano> jugadas;

	// Turno del jugador actual
	private int turno = 0;

	// Direccion del pasaje de cartas
	private Direccion direccion;

	// Atributo para guardar la carta a tirar por parte del jugador
	private Carta cartaAJugar;

	// Posicion del jugador ganador
	private int posJugadorGanador;

	// Funcionalidad corazones rotos
	private boolean corazonesRotos;

	// Lista de observadores
	private List<Observador> observadores;

	// *************************************************************
	// 						CONSTRUCTOR
	// *************************************************************

	// Constructor de la clase corazones
	public Juego() {
		// Creo la instancia de los jugadores
		jugadores = new Jugador[cantJugadores];
		ronda = 1;
		// Cargo default
		agregarJugadores("Jugador A");
		agregarJugadores("Jugador B");
		agregarJugadores("Jugador C");
		agregarJugadores("Jugador D");

		this.observadores = new ArrayList<>();
		this.jugadas = new ArrayList<>();
	}

	// *************************************************************
	// 						COMPORTAMIENTO
	// ************************************************************

	// *************************************************************
	// INICIO DE JUEGO:
	// .Tiene que dar por iniciado el juego y terminar una vez que se supere la
	// cantidad maxima de puntos
	// .Tiene que crear las jugadas y llevarlas a cabo
	// .Tiene que mostrar al jugador ganador
	// **************************************************************

	public void iniciarJuego() {
		boolean juegoTerminado = false;
		while (!juegoTerminado) {
			mazo = new Mazo();
			repartirCartas();
			pasajeDeCartas();
			this.corazonesRotos = false;
			for (int j = 0; j < cantCartasRepartidas; j++) { // Abarco las 13 jugadas de esta ronda
				int i = 0;
				Mano jugada = new Mano(this.jugadores);
				jugadas.add(jugada);

				if (j == 0) { // Primer jugada, donde se inicia con el 2 de trebol
					primercarta2Trebol(jugada);
					i++; // Aumento i porque ya jugo uno de los jugadores
				}

				while (i < jugadores.length) { // Recorro todos los jugadores
					notificar(EventosObservador.PEDIR_CARTA);
					boolean tieneOtraCartaParaJugar = false;
					//Si estamos sin corazones rotos, consulta si peude jugar otra carta que su mano que no sea la misma de la mesa
					if (!this.corazonesRotos) {
						tieneOtraCartaParaJugar = jugadores[turno].tieneCartasDelMismoPalo(jugada.getPrimeraCarta());
					}
					if (jugada.tirarCartaEnMesa(turno, cartaAJugar, tieneOtraCartaParaJugar, this.corazonesRotos)) { // Si jugo la carta correcta
						jugadores[turno].tirarCarta(jugadores[turno].buscarCarta(cartaAJugar));
						tiroCorazones();
						turno = (turno + 1) % jugadores.length; // Obtengo el siguiente jugador
						i++;
					} else {
						tiroCartaIncorrecta();
					}
				}

				// Determino el perdedor de esta jugada, obteniendo el siguiente a jugar
				turno = jugada.determinarPerdedor();
				notificar(EventosObservador.GANADOR_JUGADA);
			}
			
			// Finalizada la ronda, se comprueba si se llego al puntaje maxixo para finalizar el juego
			if (puntajeMaximoActual() >= puntajeMaximo) {
				juegoTerminado = true;
			}
			notificar(EventosObservador.FIN_DE_RONDA);
			Mano.reiniciarContadorJugadas();
			ronda++;
		}
		// Fin del juego, determino al ganador
		determinarGanador();
		notificar(EventosObservador.FIN_DE_JUEGO);
	}

	//Metodo que me dice si es posible iniciar el juego con esa cantidad de jugadores
	public boolean cantidadDeJugadoresValida() {
		int jugadorValido = 0;
		for (int i = 0; i < jugadores.length; i++) {
			if (jugadores[i] != null) { jugadorValido++;}
		}
		return (jugadorValido == cantJugadores);
	}

	// Metodo para indicar que un jugador tiro la carta de corazones
	private void tiroCorazones() {
		if (cartaAJugar.getPalo() == Palo.CORAZONES && !this.corazonesRotos) {
			notificar(EventosObservador.CORAZONES_ROTOS);
			this.corazonesRotos = true;
		}
	}

	// Metodo para indicar que se tiro mal la carta por parte del usuario
	private void tiroCartaIncorrecta() {
		if (cartaAJugar.getPalo() == Palo.CORAZONES) {
			notificar(EventosObservador.CARTA_TIRADA_INCORRECTA_CORAZONES);
		} else {
			notificar(EventosObservador.CARTA_TIRADA_INCORRECTA);
		}
	}

	// Metodo para determinar cual es el puntaje maximo actual de un jugador en el
	// juego. Es necesario obtener ek puntaje maximo de jugadores para comprobar si supero el max
	// establecido 
	private int puntajeMaximoActual() {
		int max = 0;
		for (Jugador jugadoresCorazones : jugadores) {
			if (max <= jugadoresCorazones.getPuntaje()) {
				max = jugadoresCorazones.getPuntaje();
			}
		}
		return max;
	}

	// Metodo que determina quien es el ganador del juego
	private void determinarGanador() {
		int minPuntaje = 100;
		for (int i = 0; i < cantJugadores; i++) {
			if (minPuntaje >= jugadores[i].getPuntaje()) {
				minPuntaje = jugadores[i].getPuntaje();
				this.posJugadorGanador = i;
			}
		}
	}

	// *************************************************************
	// 					FUNCIONALIDAD JUGADAS
	// *************************************************************

	// Metodo para que el jugador que tiene el 2 de trebol tire la carta
	private void primercarta2Trebol(Mano jugada) {
		boolean encontrado = false;
		int pos = 0;
		while (!encontrado && pos < jugadores.length) {
			int dosTrebol = jugadores[pos].tieneDosDeTrebol();
			if (dosTrebol != -1) {
				encontrado = true;
				turno = pos;
				jugada.tirarCartaEnMesa(turno, jugadores[pos].tirarCarta(dosTrebol), false, this.corazonesRotos); //Tira el 2 trebol
				notificar(EventosObservador.JUGO_2_DE_TREBOL);
				turno = (turno + 1) % jugadores.length;
				;
			} else {
				pos++;
			}
		}
	}

	// Metodo que me dice las cartas que puede jugar el jugador
	public String cartasPosiblesAJugar() {
		// Cartas jugables usa como parametro la primera carta que se tiro en la mesa y si los corazones estan rotos o no
		return jugadores[turno].cartasJugables(jugadas.get(jugadas.size() - 1).getPrimeraCarta(), this.corazonesRotos);
	}

	// Metodo para guardar la carta que va a ser jugada en mesa
	public void jugarCarta(int i) {
		cartaAJugar = jugadores[turno].obtenerCarta(i);
	}

	// *************************************************************
	// 					FUNCIONALIDAD RONDA
	// *************************************************************

	// Metodo que reparte las cartas a cada jugador, como se hace de forma habitual
	// 1 1 1 1, 2 2 2 2, 3 3 3 3, etc.
	private void repartirCartas() {
		for (int i = 0; i < cantCartasRepartidas; i++) {
			for (Jugador jugador : jugadores) {
				jugador.recibirCarta(mazo.sacarCarta());
			}
		}
	}

	// *************************************************************
	// 						ALTA Y MODIFICACION
	// *************************************************************
	
	// Metodo que agrega jugadores al juego,segun lo que devuelva indica si se cargo
	// de forma correcta o no el jugador
	// true = se cargo - false = no se cargo porque ya estan todos los jugadores
	public boolean agregarJugadores(String nombre) {
		boolean hayEspacio = false;
		int pos = 0;
		while (!hayEspacio && pos < jugadores.length) {
			if (jugadores[pos] == null) {
				jugadores[pos] = new Jugador(nombre, pos);
				hayEspacio = true;
			} else {
				pos++;
			}
		}
		return hayEspacio;
	}
	
	//Metodo que modifica un jugador en el juego
	//Solamente modifica si existe un jugador en la posicion que quiere
	//modificar el usuario, sino no lo hace
	public boolean reemplazarJugadores(String nombre,int posicion) {
		boolean seReemplazo = false;
		//Chequeo si no hay ningun jugador, o el referencial apunta a nulo
		if (!(jugadores[posicion - 1] == null)){
			//Cambia la bandera y modifico al jugador
			seReemplazo = true;
			jugadores[posicion - 1].setNombre(nombre);
		}
		return seReemplazo;
	}

	// *************************************************************
	// 						PASAJE DE CARTAS
	// *************************************************************

	// Metodo que determina a donde se van a pasar las cartas
	private void pasajeDeCartas() {
		int variablePasaje = 0;

		// *************************************************************
		// CASOS
		// 1. Pasaje de 1: Se realiza el pasaje a la izquierda -> variablePasaje = 1
		// 2. Pasaje de 2: Se realiza el pasaje al frente -> variablePasaje = 0
		// 3. Pasaje de 3: Se realiza el pasaje a la derecha -> variablePasaje = -1
		// 4. Pasaje de 0: No hay pasaje
		// **************************************************************

		// Determino a donde se van a pasar las cartas
		switch (this.ronda) {
		case 1: // Izq
			variablePasaje = 1;
			direccion = Direccion.Izquierda;
			break;
		case 2: // Frente
			variablePasaje = 2;
			direccion = Direccion.Frente;
			break;
		case 3: // Der
			variablePasaje = 3;
			direccion = Direccion.Derecha;
			break;
		// Caso 4: No hay intercambio
		default:
			break;
		}
		notificar(EventosObservador.PASAJE_DE_CARTAS);
		if (variablePasaje != 0) {
			intercambioDeCartas(variablePasaje);
			notificar(EventosObservador.FIN_PASAJE_DE_CARTAS);
		}
	}

	// Metodo privado que realiza el intercambio
	private void intercambioDeCartas(int valor) {
		// Esto funciona para que el intercambio se haga sobre el final y los otros
		// jugadores no tengan acceso a las cartas nuevas recibidas
		ArrayList<Carta[]> arregloDeCartasAIntercambiar = new ArrayList<Carta[]>(cantJugadores);

		// Inicializar cada posición del ArrayList con un arreglo de Carta vacío
		for (int i = 0; i < cantJugadores; i++) {
			arregloDeCartasAIntercambiar.add(new Carta[0]);
		}

		// Recorro todos los jugadores
		for (Jugador jugadorPasaje : jugadores) {

			// Obtengo la posicion del jugaodr actual y a quien le va a pasar las cartas
			int posicionJugadorActual = buscarJugador(jugadorPasaje);
			// Para saber a quien le paso las cartas, tengo que sumar la variable de pasaje
			// a la posicion del jugador actual, y a eso dividirlo por la cantidad de
			// jugadores
			int posicionJugadorPasaje = (posicionJugadorActual + valor + jugadores.length) % jugadores.length;

			// Creo la lista de las cartas que se van a pasar al otro jugador
			Carta[] cartasIntercambio = new Carta[cantCartasIntercambio];

			for (int i = 0; i < cantCartasIntercambio; i++) {
				// Obtengo la carta que jugo el jugador
				notificar(EventosObservador.PEDIR_CARTA_PASAJE);
				cartasIntercambio[i] = this.cartaAJugar;
			}

			// Guardo en el arreglo (POR POSICION DE JUGADOR) las cartas nuevas obtenidas
			arregloDeCartasAIntercambiar.set(posicionJugadorPasaje, cartasIntercambio);

			turno = (turno + 1) % jugadores.length; // Obtengo el siguiente jugador
		}

		otorgarCartasJugadores(arregloDeCartasAIntercambiar);
	}

	// Metodo que busca la posicion de un jugador determinado
	private int buscarJugador(Jugador jugador) {
		int posicionJugadorBuscar = 0;
		boolean encontrado = false;
		while (!encontrado && posicionJugadorBuscar < jugadores.length) {
			if (jugadores[posicionJugadorBuscar] == jugador) {
				encontrado = true;
			} else {
				posicionJugadorBuscar++;
			}
		}
		return posicionJugadorBuscar;
	}

	// Otorgo las cartas que se pasaron a cada jugador
	private void otorgarCartasJugadores(ArrayList<Carta[]> cartasPasaje) {
		for (int i = 0; i < cantJugadores; i++) {
			for (int j = 0; j < cantCartasIntercambio; j++) {
				jugadores[i].recibirCarta(cartasPasaje.get(i)[j]);
			}
		}
	}

	// Metodo para jugar la carta cuando se realize el pasaje
	public void jugarCartaPasaje(int i) {
		cartaAJugar = jugadores[turno].tirarCarta(i);
	}

	// Metodo que me dice las cartas que puede jugar el jugador
	public String cartasPosiblesAJugarPasaje() {
		return jugadores[turno].cartasJugables(null, this.corazonesRotos);
	}

	// *************************************************************
	// 						GETTERS
	// *************************************************************

	// Muestra el jugador de turno
	public Jugador getJugadorActual() {
		return jugadores[turno];
	}

	// Muestra las cartas que hay en mesa
	public String getCartasEnMesa() {
		return this.jugadas.get(jugadas.size() - 1).getCartasJugadas();
	}

	// Muestra el jugador perdedor de la jugada
	public String getPerdedorJugada() {
		return this.jugadas.get(jugadas.size() - 1).getPerdedorJugada();
	}

	// Muestra el numero de jugada
	public String getNumeroJugada() {
		return this.jugadas.get(jugadas.size() - 1).getNumeroJugada();
	}

	// Metodo que muestra los puntajes actuales
	public String getPuntajes() {
		String s = "---------- PUNTAJES -------------" + "\n";
		for (int i = 0; i < cantJugadores; i++) {
			s += (jugadores[i].getNombre()) + " -> " + jugadores[i].getPuntaje() + "\n";
		}
		return s;
	}

	// Metodo que me devuelve al jugador ganador
	public String getGanadorJuego() {
		return jugadores[this.posJugadorGanador].getNombre();
	}

	// Metodo que me devuelve el numero de ronda
	public String getRonda() {
		return String.valueOf(this.ronda);
	}
	
	// Getter que me dice direccion y cuantas cartas se van a pasar
	public String getDireccionPasaje() {
		String s = "No hay pasaje de cartas";
		if (this.direccion != null) {
			s = "Las cartas se pasan en la siguiente direccion: " + this.direccion.toString() + "\n";
			s += "Cantidad de cartas a pasar: " + this.getCantCartasPasaje();
		}
		return s;

	}
	// Getter para obtener la cantidad de cartas que se pueden pasar
	public String getCantCartasPasaje() {
		return String.valueOf(cantCartasIntercambio);
	}

	//Getter para saber si estan o no los corazones rotos
	public boolean getCorazonesRotos() {
		return this.corazonesRotos;
	}

	//Getter para tener todos los jugadores (en texto)	
	public String[] getJugadores()
	{
		String[] jugadores =  new String[this.jugadores.length];
		for (int i = 0; i < this.jugadores.length; i++)
		{
			jugadores[i] = this.jugadores[i].getNombre();
			//System.out.println(this.jugadores[i].getNombre());
		}
		return jugadores;
	}
	
	public List<Jugador> listaJugadores()
	{
		return Arrays.asList(jugadores);
	}
	
	public int cantidadJugadores() {
		return this.jugadores.length;
	}
	
	// *************************************************************
	//					 MVC Y OBSERVER
	// *************************************************************

	@Override // Notificar los eventos
	public void notificar(Object evento) {
		for (Observador observador : this.observadores) {
			observador.actualizar(evento, this);
		}
	}

	@Override // Agregar observadores
	public void agregarObservador(Observador observador) {
		this.observadores.add(observador);
	}


}
