package es.studium.PracticaMVC;

import java.awt.FlowLayout;
import javax.swing.*;

public class MenuPrincipal extends JFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JMenuBar barraMenu = new JMenuBar();
	JMenu menuDemandantes = new JMenu("Demandantes");
	JMenu menuOfertas = new JMenu("Ofertas");
	JMenu menuGestion = new JMenu("Gesti�n");
	JMenuItem mniDemandantesBaja = new JMenuItem("Baja");
	JMenuItem mniOfertasModificacion = new JMenuItem("Modificaci�n");
	JMenuItem mniOfertasConsulta = new JMenuItem("Consulta");
	JMenuItem mniGestionAlta = new JMenuItem("Alta");
	JPanel pnl1 = new JPanel();
	
	public MenuPrincipal() {
		this.setSize(300,250);
		this.setTitle("Pr�ctica MVC");
		this.setLocationRelativeTo(null);
		this.setLayout(new FlowLayout());
		this.setJMenuBar(barraMenu);
		menuDemandantes.add(mniDemandantesBaja);
		menuOfertas.add(mniOfertasModificacion);
		menuOfertas.add(mniOfertasConsulta);
		menuGestion.add(mniGestionAlta);
		barraMenu.add(menuDemandantes);
		barraMenu.add(menuOfertas);
		barraMenu.add(menuGestion);
		this.add(pnl1);
		this.setVisible(true);	
	}
	
}
