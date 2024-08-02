package ar.edu.unlu.corazones.app;

import ar.edu.unlu.corazones.controlador.Controlador;
import ar.edu.unlu.corazones.modelo.Juego;
import ar.edu.unlu.corazones.vista.IVista;
import ar.edu.unlu.corazones.vista.VistaConsola;
import ar.edu.unlu.corazones.vista.VistaGrafica;

public class App {

	public static void main(String[] args) {
		Juego modelo = new Juego();
		IVista vista = new VistaConsola();
		//IVista vista = new VistaGrafica();
		Controlador controlador = new Controlador(modelo, vista);
		vista.iniciar();
	}

}
