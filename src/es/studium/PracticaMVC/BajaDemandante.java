package es.studium.PracticaMVC;

import java.awt.Choice;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BajaDemandante extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel lblElegir = new JLabel("Elegir demandante a dar de baja:");
	Choice demandantes = new Choice();
	JButton btnEliminar = new JButton("Eliminar");
	JButton btnCancelar = new JButton("Cancelar");
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	public BajaDemandante() {
		this.setSize(300,200);
		this.setTitle("Baja Demandante");
		this.setLocationRelativeTo(null);
		this.setLayout(new GridLayout(3,1));
		pnl1.add(lblElegir);
		demandantes.add("Elegir uno...");
		pnl2.add(demandantes);
		pnl3.add(btnEliminar);
		pnl3.add(btnCancelar);
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);
		this.setVisible(true);
	}
}