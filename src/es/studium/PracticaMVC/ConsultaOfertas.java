package es.studium.PracticaMVC;

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ConsultaOfertas extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	DefaultTableModel modelo = new DefaultTableModel();
	JTable tablaOfertas= new JTable(modelo);
	JButton btnAceptar = new JButton("Aceptar");
	JPanel pnlB = new JPanel();
	public ConsultaOfertas() {
		this.setSize(500,200);
		this.setTitle("Consulta Ofertas");
		this.setLocationRelativeTo(null);
		this.setLayout(new BorderLayout());
		this.add(new JScrollPane(tablaOfertas),BorderLayout.CENTER);
		pnlB.add(btnAceptar);
		this.add(pnlB,BorderLayout.SOUTH);
		this.setVisible(true);
	}
	
	

}
