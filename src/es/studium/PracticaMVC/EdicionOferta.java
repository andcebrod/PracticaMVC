package es.studium.PracticaMVC;

import java.awt.GridLayout;

import javax.swing.*;

public class EdicionOferta extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String idOf;
	JLabel lblOferta = new JLabel("Oferta:");
	JLabel lblNumOferta = new JLabel("");
	JLabel lblFecha = new JLabel ("Fecha:");
	JLabel lblFechaFin = new JLabel ("Fecha Fin:");
	JLabel lblRequisitos = new JLabel ("Requisitos:");
	JTextField txtFecha = new JTextField(10);
	JTextField txtFechaFin = new JTextField(10);
	JTextField txtRequisitos = new JTextField(10);
	JButton btnActualizar = new JButton("Actualizar");
	JButton btnCancelar = new JButton("Cancelar");

	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	JPanel pnl4 = new JPanel();
	JPanel pnl5 = new JPanel();
	
	public EdicionOferta(int id) {
		this.setTitle("Edición Oferta");
		this.setLayout(new GridLayout(5,1));
		this.setSize(300, 500);
		this.setLocationRelativeTo(null);
		idOf = Integer.toString(id);
		lblNumOferta.setText(idOf);
		pnl1.add(lblOferta);
		pnl1.add(lblNumOferta);
		pnl2.add(lblFecha);
		pnl2.add(txtFecha);
		pnl3.add(lblFechaFin);
		pnl3.add(txtFechaFin);
		pnl4.add(lblRequisitos);
		pnl4.add(txtRequisitos);
		pnl5.add(btnActualizar);
		pnl5.add(btnCancelar);
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);
		this.add(pnl4);
		this.add(pnl5);
		this.setVisible(true);
	}
	
	
}
