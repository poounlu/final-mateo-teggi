package ar.edu.unlu.corazones.tests;

import ar.edu.unlu.corazones.modelo.Carta;
import ar.edu.unlu.corazones.modelo.Palo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class CartaTest {
    @Test
    public void testCartaValorNumerico() {
        Carta carta = new Carta(Palo.CORAZONES, 10);
        assertEquals(Palo.CORAZONES, carta.getPalo());
        assertEquals(10, carta.getValor());
        assertEquals("10", carta.getValorTexto());
        assertEquals("10 - CORAZONES", carta.mostrarCarta());
    }

    @Test
    public void testCartaValorJ() {
        Carta carta = new Carta(Palo.TREBOL, 11);
        assertEquals(Palo.TREBOL, carta.getPalo());
        assertEquals(11, carta.getValor());
        assertEquals("J", carta.getValorTexto());
        assertEquals("J - TREBOLES", carta.mostrarCarta());
    }

    @Test
    public void testCartaValorQ() {
        Carta carta = new Carta(Palo.DIAMANTE, 12);
        assertEquals(Palo.DIAMANTE, carta.getPalo());
        assertEquals(12, carta.getValor());
        assertEquals("Q", carta.getValorTexto());
        assertEquals("Q - DIAMANTES", carta.mostrarCarta());
    }

    @Test
    public void testCartaValorK() {
        Carta carta = new Carta(Palo.PICAS, 13);
        assertEquals(Palo.PICAS, carta.getPalo());
        assertEquals(13, carta.getValor());
        assertEquals("K", carta.getValorTexto());
        assertEquals("K - PICAS", carta.mostrarCarta());
    }

    @Test
    public void testCartaValorA() {
        Carta carta = new Carta(Palo.CORAZONES, 1);
        assertEquals(Palo.CORAZONES, carta.getPalo());
        assertEquals(14, carta.getValor()); // El As se convierte en 14
        assertEquals("A", carta.getValorTexto());
        assertEquals("A - CORAZONES", carta.mostrarCarta());
    }

    @Test
    public void testGetPalo() {
        Carta carta = new Carta(Palo.CORAZONES, 2);
        assertEquals(Palo.CORAZONES, carta.getPalo());
    }

    @Test
    public void testGetValor() {
        Carta carta = new Carta(Palo.CORAZONES, 3);
        assertEquals(3, carta.getValor());
    }

    @Test
    public void testGetValorTexto() {
        Carta carta = new Carta(Palo.CORAZONES, 4);
        assertEquals("4", carta.getValorTexto());
    }

    @Test
    public void testMostrarCarta() {
        Carta carta = new Carta(Palo.CORAZONES, 5);
        assertEquals("5 - CORAZONES", carta.mostrarCarta());
    }
}
