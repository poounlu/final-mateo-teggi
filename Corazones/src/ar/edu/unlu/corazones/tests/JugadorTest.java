package ar.edu.unlu.corazones.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Jugador;
import ar.edu.unlu.corazones.modelo.Palo;

import java.util.ArrayList;

public class JugadorTest {

    private Jugador jugador;
    private Carta carta1;
    private Carta carta2;

    @BeforeEach
    public void setUp() {
        jugador = new Jugador("Jugador1", 1);
        carta1 = new Carta(Palo.TREBOL, 2);
        carta2 = new Carta(Palo.CORAZONES, 10);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals("Jugador1", jugador.getNombre());
        assertEquals(0, jugador.getPuntaje());
        assertTrue(jugador.getMano().isEmpty());
    }

    @Test
    public void testRecibirCarta() {
        jugador.recibirCarta(carta1);
        assertEquals(1, jugador.cartasEnMano());
        assertEquals(carta1, jugador.obtenerCarta(0));
    }

    @Test
    public void testTirarCarta() {
        jugador.recibirCarta(carta1);
        Carta tirada = jugador.tirarCarta(0);
        assertEquals(carta1, tirada);
        assertTrue(jugador.getMano().isEmpty());
    }

    @Test
    public void testBuscarCarta() {
        jugador.recibirCarta(carta1);
        assertEquals(0, jugador.buscarCarta(carta1));
        assertEquals(-1, jugador.buscarCarta(carta2));
    }

    @Test
    public void testTieneDosDeTrebol() {
        assertEquals(-1, jugador.tieneDosDeTrebol());
        jugador.recibirCarta(carta1);
        assertEquals(0, jugador.tieneDosDeTrebol());
    }

    @Test
    public void testCartasJugables() {
        jugador.recibirCarta(carta1);
        jugador.recibirCarta(carta2);
        // Corazones no rotos, primera carta es trébol
        String jugables = jugador.cartasJugables(carta1, false);
        assertTrue(jugables.contains("1) 2 de Trebol"));
        assertFalse(jugables.contains("2) 10 de Corazones"));
    }

    @Test
    public void testTieneCartasDelMismoPalo() {
        jugador.recibirCarta(carta1);
        assertTrue(jugador.tieneCartasDelMismoPalo(new Carta(Palo.TREBOL, 3)));
        assertFalse(jugador.tieneCartasDelMismoPalo(new Carta(Palo.CORAZONES, 10)));
    }

    @Test
    public void testAgregarPuntaje() {
        jugador.agregarPuntaje(10);
        assertEquals(10, jugador.getPuntaje());
    }

    @Test
    public void testMostrarMano() {
        jugador.recibirCarta(carta1);
        jugador.recibirCarta(carta2);
        String mano = jugador.mostrarMano();
        assertTrue(mano.contains("1) 2 de Trebol"));
        assertTrue(mano.contains("2) 10 de Corazones"));
    }
}