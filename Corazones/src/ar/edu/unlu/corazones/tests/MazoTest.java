package ar.edu.unlu.corazones.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Mazo;
import ar.edu.unlu.corazones.modelo.Palo;

public class MazoTest {

    private Mazo mazo;

    @BeforeEach
    public void setUp() {
        mazo = new Mazo();
    }

    @Test
    public void testConstructor() {
        assertNotNull(mazo);
        assertEquals(52, mazo.getMazo().length);
    }

    @Test
    public void testCrearMazo() {
        Carta[] mazoCartas = mazo.getMazo();
        int count = 0;
        for (Palo palo : Palo.values()) {
            for (int i = 1; i <= 13; i++) {
                Carta carta = mazoCartas[count++];
                assertEquals(palo, carta.getPalo());
                assertEquals(i, carta.getValor());
            }
        }
    }

    @Test
    public void testBarajarMazo() {
        Carta[] mazoAntesDeBarajar = mazo.getMazo().clone();
        mazo.barajarMazo();
        Carta[] mazoDespuesDeBarajar = mazo.getMazo();
        
        boolean esDistinto = false;
        for (int i = 0; i < mazoAntesDeBarajar.length; i++) {
            if (!mazoAntesDeBarajar[i].equals(mazoDespuesDeBarajar[i])) {
                esDistinto = true;
                break;
            }
        }
        assertTrue(esDistinto, "El mazo no se ha barajado correctamente");
    }

    @Test
    public void testSacarCarta() {
        int topeInicial = 51;
        Carta cartaSacada = mazo.sacarCarta();
        assertNotNull(cartaSacada);
        assertNull(mazo.getMazo()[topeInicial]);
        assertEquals(topeInicial - 1, mazo.getMazo().length - 1);
    }

    @Test
    public void testMostrarMazo() {
        String mazoString = mazo.mostrarMazo();
        assertNotNull(mazoString);
        assertTrue(mazoString.contains("0)"));
        assertTrue(mazoString.contains("51)"));
    }
}

