package ar.edu.unlu.corazones.tests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Juego;
import ar.edu.unlu.corazones.modelo.Jugador;
import ar.edu.unlu.corazones.modelo.Palo;

class JuegoTest {

    private Juego juego;
    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;
    private Jugador jugador4;

    @BeforeEach
    void setUp() {
        juego = new Juego();
        jugador1 = new Jugador("Jugador1", 1);
        jugador2 = new Jugador("Jugador2", 2);
        jugador3 = new Jugador("Jugador3", 3);
        jugador4 = new Jugador("Jugador4", 4);

        // Reemplazamos los jugadores reales con mocks
        juego.agregarJugadores("Jugador A");
        juego.agregarJugadores("Jugador B");
        juego.agregarJugadores("Jugador C");
        juego.agregarJugadores("Jugador D");

        // Reemplazamos los jugadores reales con mocks
        juego.listaJugadores().set(0, jugador1);
        juego.listaJugadores().set(1, jugador2);
        juego.listaJugadores().set(2, jugador3);
        juego.listaJugadores().set(3, jugador4);
    }

    @Test
    void testCantidadDeJugadoresValida() {
        assertTrue(juego.cantidadDeJugadoresValida());
    }

    @Test
    void testAgregarJugadores() {
        Juego nuevoJuego = new Juego();
        assertTrue(nuevoJuego.agregarJugadores("Nuevo Jugador"));
    }

    @Test
    void testReemplazarJugadores() {
        assertTrue(juego.reemplazarJugadores("Jugador Nuevo", 1));
        assertEquals("Jugador Nuevo", juego.listaJugadores().get(0).getNombre());
    }

    @Test
    void testIniciarJuego() {
        // Configuración de mocks para el juego
        when(jugadorMock1.tieneDosDeTrebol()).thenReturn(0);
        when(jugadorMock1.tirarCarta(0)).thenReturn(new Carta(Palo.TREBOL, 2));
        juego.iniciarJuego();
        assertEquals("Jugador A", juego.getGanadorJuego());
    }

    @Test
    void testRepartirCartas() {
        juego.repartirCartas();
        for (Jugador jugador : juego.listaJugadores()) {
            verify(jugador, times(Juego.cantCartasRepartidas)).recibirCarta(any(Carta.class));
        }
    }
}