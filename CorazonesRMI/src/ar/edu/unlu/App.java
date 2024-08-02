package ar.edu.unlu;

import java.awt.EventQueue;

import ar.edu.unlu.controlador.Controlador;
import ar.edu.unlu.modelo.Juego;
import ar.edu.unlu.vista.IVista;
import ar.edu.unlu.vista.VistaConsola;
import ar.edu.unlu.vista.VistaGrafica;

public class App {

	public static void main(String[] args) {
	    Juego modelo = Juego.getInstancia();
		IVista vista = new VistaConsola();
		//IVista vista = new VistaGrafica();
		Controlador controlador = new Controlador(modelo, vista);
		vista.iniciar();
	}
}
