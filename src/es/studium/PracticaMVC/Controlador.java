package es.studium.PracticaMVC;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Controlador implements ActionListener, WindowListener
{
	Modelo Model;
	MenuPrincipal MenuPrinci;
	BajaDemandante BajaDem;
	ModificacionOferta ModOf;
	EdicionOferta EdOf;
	int ofertaSeleccionada = 0;
	int demandanteSeleccionado = 0;


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
			MenuPrinci.setVisible(false);
			BajaDem.addWindowListener(this);
			BajaDem.btnEliminar.addActionListener(this);
			BajaDem.btnCancelar.addActionListener(this);
			ResultSet dm = Model.ejecutarSelect("SELECT * FROM demandantes", Model.conectar("practicamvc","root","Studium2018;"));

			try {
				while(dm.next())
				{
					String demandantes=Integer.toString(dm.getInt("idDemandante"));
					demandantes = demandantes+".-"+ dm.getString("nombreDemandante")+" "+ dm.getString("apellidosDemandante")+" - "+dm.getString("dniDemandante");
					BajaDem.demandantes.add(demandantes);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
			Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));

		} else if (MenuPrinci.mniOfertasModificacion.equals(ae.getSource())) {

		} else if (MenuPrinci.mniOfertasConsulta.equals(ae.getSource())) {

		} else if (MenuPrinci.mniGestionAlta.equals(ae.getSource())) {

		}
		try {
			if(BajaDem.btnEliminar.equals(ae.getSource())) {
				int seleccion = JOptionPane.showOptionDialog( null,"¿Desea eliminar demandante?","Eliminar demandante",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Eliminar", "Cancelar"},"Cancelar");

				if (seleccion == 0){
					try {
						String[] array= BajaDem.demandantes.getSelectedItem().toString().split(".-");
						demandanteSeleccionado = Integer.parseInt(array[0]);
					} catch (NumberFormatException Nf) {
						JOptionPane.showMessageDialog(null,"Introduzca demandante válido","Error de demandante", JOptionPane.ERROR_MESSAGE);
					}
					Model.ejecutarIDA("DELETE FROM demandantes where idDemandante ="+demandanteSeleccionado+";", Model.conectar("practicamvc", "root", "Studium2018;"));
				} else if(seleccion == 1) {

				}

			}
			if(BajaDem.btnCancelar.equals(ae.getSource())) {
				BajaDem.setVisible(false);
				MenuPrinci.setVisible(true);
			}
		} catch(NullPointerException np) {

		}
		
		if(MenuPrinci.mniOfertasModificacion.equals(ae.getSource())) {
			ModOf = new ModificacionOferta();
			MenuPrinci.setVisible(false);
			ModOf.addWindowListener(this);
			ModOf.btnEditar.addActionListener(this);
			ModOf.btnCancelar.addActionListener(this);
			
			ResultSet of = Model.ejecutarSelect("SELECT * FROM ofertas", Model.conectar("practicamvc","root","Studium2018;"));
			try {
				while(of.next())
				{
					String ofertas=Integer.toString(of.getInt("idOferta"));
					ofertas = ofertas+".-"+"Fecha Oferta:"+of.getString("fechaOferta")+" Fecha Fin Oferta:"+ of.getString("fechaFinOferta");
					ModOf.ofertas.add(ofertas);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
			Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
		}
		try {
			if(ModOf.btnEditar.equals(ae.getSource())) {
				String[] array= ModOf.ofertas.getSelectedItem().toString().split(".-");
				ofertaSeleccionada = Integer.parseInt(array[0]);
				EdOf = new EdicionOferta(ofertaSeleccionada);
				EdOf.btnActualizar.addActionListener(this);
				EdOf.btnCancelar.addActionListener(this);
				EdOf.addWindowListener(this);
				ModOf.setVisible(false);
				ResultSet ofSel = Model.ejecutarSelect("SELECT * FROM ofertas where idOferta ="+ofertaSeleccionada+";", Model.conectar("practicamvc","root","Studium2018;"));
				try {
					ofSel.next();
					EdOf.txtFecha.setText(ofSel.getString("fechaOferta"));
					EdOf.txtFechaFin.setText(ofSel.getString("fechaFinOferta"));
					EdOf.txtRequisitos.setText(ofSel.getString("requisitosOferta"));

				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
			}
			else if(ModOf.btnCancelar.equals(ae.getSource())) {
				ModOf.setVisible(false);
				MenuPrinci.setVisible(true);
			}
			
			if(EdOf.btnActualizar.equals(ae.getSource())) {
				String Fecha = EdOf.txtFecha.getText();
				String[] arrayFecha = Fecha.split("/");
				String FechaFin = EdOf.txtFechaFin.getText();
				String[] arrayFechaFin = FechaFin.split("/");
				try {
				Fecha = arrayFecha[2]+"-"+arrayFecha[1]+"-"+arrayFecha[0];
				FechaFin = arrayFechaFin[2]+"-"+arrayFechaFin[1]+"-"+arrayFechaFin[0];
				} catch (ArrayIndexOutOfBoundsException ai) {
					
				}
				Model.ejecutarIDA("UPDATE ofertas SET fechaOferta ='"+Fecha+"', fechaFinOferta='"+FechaFin+"', requisitosOferta ='"+EdOf.txtRequisitos.getText()+"' WHERE idOferta ="+ofertaSeleccionada+";", Model.conectar("practicamvc","root" ,"Studium2018;"));
				JOptionPane.showMessageDialog(null,"Oferta Modificada con éxito","Oferta Modificada", JOptionPane.INFORMATION_MESSAGE);
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
