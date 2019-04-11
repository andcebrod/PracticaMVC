package es.studium.PracticaMVC;

import java.awt.Choice;
import java.awt.GridLayout;
import javax.swing.*;

public class ModificacionOferta extends JFrame
{
	JLabel lblModOf = new JLabel("Elegir oferta a modificar:");
	Choice ofertas = new Choice();
	JButton btnEditar = new JButton("Editar");
	JButton btnCancelar = new JButton("Cancelar");
	
	JPanel pnl1 = new JPanel();
	JPanel pnl2 = new JPanel();
	JPanel pnl3 = new JPanel();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ModificacionOferta() {
		this.setTitle("Modificación Oferta");
		this.setLayout(new GridLayout (3,1));
		this.setSize(500, 200);
		this.setLocationRelativeTo(null);
		pnl1.add(lblModOf);
		pnl2.add(ofertas);
		pnl3.add(btnEditar);
		pnl3.add(btnCancelar);
		this.add(pnl1);
		this.add(pnl2);
		this.add(pnl3);
		this.setVisible(true);
		
	}

}
