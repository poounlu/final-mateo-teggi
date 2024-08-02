package ar.edu.unlu.vista;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class VentanaReglas extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JLabel label1;
    
    private JTextArea area;
    
    private JScrollPane panel;
    
    
    public VentanaReglas() {
        this.setTitle("Reglas de juego");
        iniciar();
        alinear();
        setSize(530,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);
    }
    
    public void alinear() {
        setLayout(null);
        label1.setBounds(160,0,500,50);
        getContentPane().add(label1);
        panel.setBounds(0,50,530,200);
        getContentPane().add(panel);
    }
    
    public void iniciar() {
        label1 = new JLabel("Corazones");
        label1.setFont(new Font("Arial", Font.CENTER_BASELINE, 18));
        area = new JTextArea();
        area.setEditable(false);
        area.setText("Objetivo del juego\r\n" 
                + "El fin de este juego es intentar sumar el menor número de puntos posible en el desarrollo de las trece bazas de las que cuenta el juego.\r\n" + 
                "\r\n" + 
                "Ganar muchas bazas o cartas no tiene validez en este juego, lo importante es no llevarse en ellas cartas que sumen puntos a la puntuación final del apostador.\r\n" +
                "El que tenga menos unidades al final de una partida será el que se la adjudique, de ahí la importancia de este aspecto.");
        panel = new JScrollPane(area);
}
}
