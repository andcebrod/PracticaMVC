package es.studium.PracticaMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener, WindowListener
{
	Modelo Model;
	MenuPrincipal MenuPrinci;
	BajaDemandante BajaDem;


	public Controlador(Modelo m, MenuPrincipal Mp) {
		Model = m;
		MenuPrinci = Mp;
		Mp.mniDemandantesBaja.addActionListener(this);
		Mp.mniOfertasModificacion.addActionListener(this);
		Mp.mniOfertasConsulta.addActionListener(this);
		Mp.mniGestionAlta.addActionListener(this);
		Mp.addWindowListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent ae) 
	{
		if(MenuPrinci.mniDemandantesBaja.equals(ae.getSource())) {
			BajaDem = new BajaDemandante();
			BajaDem.addWindowListener(this);
			BajaDem.btnEliminar.addActionListener(this);
			BajaDem.btnCancelar.addActionListener(this);
			

		} else if (MenuPrinci.mniOfertasModificacion.equals(ae.getSource())) {

		} else if (MenuPrinci.mniOfertasConsulta.equals(ae.getSource())) {

		} else if (MenuPrinci.mniGestionAlta.equals(ae.getSource())) {

		}
		try {
			if(BajaDem.btnEliminar.equals(ae.getSource())) {
				int seleccion = JOptionPane.showOptionDialog( null,"¿Desea eliminar demandante?","Eliminar demandante",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Eliminar", "Cancelar"},"Cancelar");

				if (seleccion == 0){
					Model.ejecutarIDA("DELETE FROM demandantes", Model.conectar("practicaMVC", "root", "Studium2018;"));
				} else if(seleccion == 1) {
					
				}
				
			}
		} catch(NullPointerException np) {

		}


	}

	@Override
	public void windowActivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent arg0) {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowOpened(WindowEvent arg0) {
		// TODO Auto-generated method stub

	}

}
