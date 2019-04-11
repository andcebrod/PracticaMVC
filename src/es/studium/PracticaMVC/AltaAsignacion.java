package es.studium.PracticaMVC;

import java.awt.Choice;
import java.awt.GridLayout;

import javax.swing.*;

public class AltaAsignacion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Choice ofertas = new Choice();
	Choice demandantes = new Choice();
	
	JLabel lblFecha = new JLabel("Fecha");
	JLabel lblOferta = new JLabel("Oferta:");
	JLabel lblDemandante = new JLabel("Demandante:");

	JTextField txtFecha = new JTextField(10);
	
	JButton btnAceptar = new JButton("Aceptar");
	JButton btnCancelar = new JButton("Cancelar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	JPanel pnl4 = new JPanel();
	
	
	public AltaAsignacion() {
		this.setSize(500,250);
		this.setTitle("Alta Asignación");
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(4,1));
		
		ofertas.add("Elegir una...");
		demandantes.add("Elegir uno...");
		
		pnl1.add(lblFecha);
		pnl1.add(txtFecha);
		pnl2.add(lblOferta);
		pnl2.add(ofertas);
		pnl3.add(lblDemandante);
		pnl3.add(demandantes);
		pnl4.add(btnAceptar);
		pnl4.add(btnCancelar);
		
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);
		this.add(pnl4);
		
		this.setVisible(true);
		
		
	}
}
