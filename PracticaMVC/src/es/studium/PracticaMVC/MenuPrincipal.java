package es.studium.PracticaMVC;

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
	JMenu menuGestion = new JMenu("Gestión");
	JMenuItem mniDemandantesBaja = new JMenuItem("Baja");
	JMenuItem mniOfertasModificacion = new JMenuItem("Modificación");
	JMenuItem mniOfertasConsulta = new JMenuItem("Consulta");
	JMenuItem mniGestionAlta = new JMenuItem("Alta");
	JPanel pnl1 = new JPanel();
	JLabel lblvacio = new JLabel();
	
	public MenuPrincipal() {
		
		this.setSize(300,200);
		this.setTitle("Práctica MVC");
		this.setLocationRelativeTo(null);
		
		menuDemandantes.add(mniDemandantesBaja);
		menuOfertas.add(mniOfertasModificacion);
		menuOfertas.add(mniOfertasConsulta);
		menuGestion.add(mniGestionAlta);
		barraMenu.add(menuDemandantes);
		barraMenu.add(menuOfertas);
		barraMenu.add(menuGestion);
		pnl1.add(lblvacio);
		this.add(pnl1);
		this.add(barraMenu);
		
		
		this.setVisible(true);
		
	}
	
}
