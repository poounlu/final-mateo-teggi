package ar.edu.unlu.corazones.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.corazones.modelo.Jugador;

public class ManoTest {

    private Jugador jugador1;
    private Jugador jugador2;
    private Jugador jugador3;
    private Jugador jugador4;
    private Mano mano;
    private Carta carta1;
    private Carta carta2;
    private Carta carta3;
    private Carta carta4;

    @BeforeEach
    public void setUp() {
        jugador1 = new Jugador("Jugador1", 1);
        jugador2 = new Jugador("Jugador2", 2);
        jugador3 = new Jugador("Jugador3", 3);
        jugador4 = new Jugador("Jugador4", 4);
        Jugador[] jugadores = {jugador1, jugador2, jugador3, jugador4};
        mano = new Mano(jugadores);
        carta1 = new Carta(Palo.TREBOL, 2);
        carta2 = new Carta(Palo.CORAZONES, 10);
        carta3 = new Carta(Palo.PICAS, 12); // Dama de Picas
        carta4 = new Carta(Palo.DIAMANTES, 5);
    }

    @Test
    public void testConstructor() {
        assertEquals("1", mano.getNumeroJugada());
        assertEquals("\nJugador1 -> Ninguna\nJugador2 -> Ninguna\nJugador3 -> Ninguna\nJugador4 -> Ninguna\n", mano.getCartasJugadas());
    }

    @Test
    public void testTirarCartaEnMesa() {
        boolean resultado = mano.tirarCartaEnMesa(0, carta1, true, false);
        assertTrue(resultado);
        assertEquals(carta1, mano.getPrimeraCarta());
    }

    @Test
    public void testPrimeraCarta() {
        assertTrue(mano.tirarCartaEnMesa(0, carta1, true, false));
        assertTrue(mano.tirarCartaEnMesa(1, carta2, true, true));
        assertEquals(carta1, mano.getPrimeraCarta());
    }

    @Test
    public void testDeterminarPerdedor() {
        mano.tirarCartaEnMesa(0, carta1, true, false);
        mano.tirarCartaEnMesa(1, carta2, true, true);
        mano.tirarCartaEnMesa(2, carta3, true, true);
        mano.tirarCartaEnMesa(3, carta4, true, true);
        int perdedor = mano.determinarPerdedor();
        assertEquals(2, perdedor); // Jugador que tiró la Dama de Picas
        assertEquals("Jugador3", mano.getPerdedorJugada());
        assertEquals(13, jugador3.getPuntaje()); // 13 puntos por la Dama de Picas
    }

    @Test
    public void testPuntajeCarta() {
        assertEquals(1, mano.puntajeCarta(carta2)); // Carta de Corazones
        assertEquals(13, mano.puntajeCarta(carta3)); // Dama de Picas
        assertEquals(0, mano.puntajeCarta(carta1)); // Carta de otro palo
    }

    @Test
    public void testGetCartasJugadas() {
        mano.tirarCartaEnMesa(0, carta1, true, false);
        mano.tirarCartaEnMesa(1, carta2, true, true);
        String jugadas = mano.getCartasJugadas();
        assertTrue(jugadas.contains("Jugador1 -> 2 de Trebol"));
        assertTrue(jugadas.contains("Jugador2 -> 10 de Corazones"));
    }

    @Test
    public void testGetNumeroJugada() {
        assertEquals("1", mano.getNumeroJugada());
    }

    @Test
    public void testGetPerdedorJugada() {
        mano.tirarCartaEnMesa(0, carta1, true, false);
        mano.tirarCartaEnMesa(1, carta2, true, true);
        mano.tirarCartaEnMesa(2, carta3, true, true);
        mano.tirarCartaEnMesa(3, carta4, true, true);
        mano.determinarPerdedor();
        assertEquals("Jugador3", mano.getPerdedorJugada());
    }

    @Test
    public void testReiniciarContadorJugadas() {
        Mano.reiniciarContadorJugadas();
        Jugador[] jugadores = {jugador1, jugador2, jugador3, jugador4};
        Mano nuevaMano = new Mano(jugadores);
        assertEquals("1", nuevaMano.getNumeroJugada());
    }
}

