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
	AltaAsignacion Aa;
	ConsultaOfertas ConOf;
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
		//BAJA DEMANDANTE
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
		}
		try {
			if(BajaDem.btnEliminar.equals(ae.getSource())) 
			{
				int seleccion = JOptionPane.showOptionDialog( null,"Â¿Desea eliminar demandante?","Eliminar demandante",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,new Object[] { "Eliminar", "Cancelar"},"Cancelar");

				if (seleccion == 0)
				{
					try 
					{
						String[] array= BajaDem.demandantes.getSelectedItem().toString().split(".-");
						demandanteSeleccionado = Integer.parseInt(array[0]);
					} catch (NumberFormatException Nf) 
					{
						JOptionPane.showMessageDialog(null,"Introduzca demandante válido","Error de demandante", JOptionPane.ERROR_MESSAGE);
					}
					Model.ejecutarIDA("DELETE FROM demandantes where idDemandante ="+demandanteSeleccionado+";", Model.conectar("practicamvc", "root", "Studium2018;"));
					JOptionPane.showMessageDialog(null,"Demandante eliminado con éxito","Demandante eliminado", JOptionPane.INFORMATION_MESSAGE);
				} else if(seleccion == 1){}
			}
			if(BajaDem.btnCancelar.equals(ae.getSource())) {
				BajaDem.setVisible(false);
				MenuPrinci.setVisible(true);
			}
		} catch(NullPointerException np) {}
		//MODIFICAR OFERTA
		if(MenuPrinci.mniOfertasModificacion.equals(ae.getSource()))
		{
			ModOf = new ModificacionOferta();
			MenuPrinci.setVisible(false);
			ModOf.addWindowListener(this);
			ModOf.btnEditar.addActionListener(this);
			ModOf.btnCancelar.addActionListener(this);
			ResultSet of = Model.ejecutarSelect("SELECT idOferta,DATE_FORMAT(fechaOferta, '%d/%m/%Y'),DATE_FORMAT(fechaFinOferta, '%d/%m/%Y')  FROM ofertas", Model.conectar("practicamvc","root","Studium2018;"));
			try 
			{
				while(of.next())
				{
					String ofertas=Integer.toString(of.getInt("idOferta"));
					ofertas = ofertas+".-"+"Fecha Oferta:"+of.getString("DATE_FORMAT(fechaOferta, '%d/%m/%Y')")+" Fecha Fin Oferta:"+ of.getString("DATE_FORMAT(fechaFinOferta, '%d/%m/%Y')");
					ModOf.ofertas.add(ofertas);
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			}
			Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
		}
		//EDICIÓN OFERTA
		try {
			if(ModOf.btnEditar.equals(ae.getSource())) 
			{
				String[] array= ModOf.ofertas.getSelectedItem().toString().split(".-");
				ofertaSeleccionada = Integer.parseInt(array[0]);
				EdOf = new EdicionOferta(ofertaSeleccionada);
				EdOf.btnActualizar.addActionListener(this);
				EdOf.btnCancelar.addActionListener(this);
				EdOf.addWindowListener(this);
				ModOf.setVisible(false);
				ResultSet ofSel = Model.ejecutarSelect("SELECT DATE_FORMAT(fechaOferta, '%d/%m/%Y'),DATE_FORMAT(fechaFinOferta, '%d/%m/%Y'),requisitosOferta   FROM ofertas where idOferta ="+ofertaSeleccionada+";", Model.conectar("practicamvc","root","Studium2018;"));
				try 
				{
					ofSel.next();
					EdOf.txtFecha.setText(ofSel.getString("DATE_FORMAT(fechaOferta, '%d/%m/%Y')"));
					EdOf.txtFechaFin.setText(ofSel.getString("DATE_FORMAT(fechaFinOferta, '%d/%m/%Y')"));
					EdOf.txtRequisitos.setText(ofSel.getString("requisitosOferta"));

				} catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
			}
			else if(ModOf.btnCancelar.equals(ae.getSource())) 
			{
				ModOf.setVisible(false);
				MenuPrinci.setVisible(true);
			}
			if(EdOf.btnActualizar.equals(ae.getSource())) 
			{
				String Fecha = EdOf.txtFecha.getText();
				String[] arrayFecha = Fecha.split("/");
				String FechaFin = EdOf.txtFechaFin.getText();
				String[] arrayFechaFin = FechaFin.split("/");
				try 
				{
					Fecha = arrayFecha[2]+"-"+arrayFecha[1]+"-"+arrayFecha[0];
					FechaFin = arrayFechaFin[2]+"-"+arrayFechaFin[1]+"-"+arrayFechaFin[0];
				} catch (ArrayIndexOutOfBoundsException ai) {}
				Model.ejecutarIDA("UPDATE ofertas SET fechaOferta ='"+Fecha+"', fechaFinOferta='"+FechaFin+"', requisitosOferta ='"+EdOf.txtRequisitos.getText()+"' WHERE idOferta ="+ofertaSeleccionada+";", Model.conectar("practicamvc","root" ,"Studium2018;"));
				JOptionPane.showMessageDialog(null,"Oferta Modificada con éxito","Oferta Modificada", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (EdOf.btnCancelar.equals(ae.getSource()))
			{
				EdOf.setVisible(false);
				MenuPrinci.setVisible(true);
			}
		} catch(NullPointerException np) {}

		//CONSULTA OFERTAS
		try 
		{	
			if(MenuPrinci.mniOfertasConsulta.equals(ae.getSource())) 
			{
				ConOf = new ConsultaOfertas();
				MenuPrinci.setVisible(false);
				ConOf.addWindowListener(this);
				ConOf.btnAceptar.addActionListener(this);
				ConOf.modelo.addColumn("Número");
				ConOf.modelo.addColumn("Número Demandantes");
				ConOf.modelo.addColumn("Fecha Fin");
				ResultSet Co = Model.ejecutarSelect("select idOfertaFK, count(idDemandanteFK), DATE_FORMAT(fechaFinOferta, '%d/%m/%Y') from asignaciones join ofertas on idOfertaFK = idOferta group by idOfertaFK order by idOfertaFK;", Model.conectar("practicamvc","root" ,"Studium2018;"));
				try {
					while (Co.next())
					{
						Object [] fila = new Object[3];
						for (int i=0;i<3;i++)
							if(i==2) {
								fila[i] = Co.getString("DATE_FORMAT(fechaFinOferta, '%d/%m/%Y')");
							} else {
								fila[i] = Co.getObject(i+1); 
							}
						ConOf.modelo.addRow(fila); 
					}
				} catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
				ConOf.tablaOfertas.setEnabled(false);
			}
			if(ConOf.btnAceptar.equals(ae.getSource())) 
			{
				ConOf.setVisible(false);
				MenuPrinci.setVisible(true);
			}

		} catch(NullPointerException np) {}
		try 
		{
			if(MenuPrinci.mniGestionAlta.equals(ae.getSource())) 
			{
				Aa = new AltaAsignacion();
				MenuPrinci.setVisible(false);
				Aa.btnAceptar.addActionListener(this);
				Aa.btnCancelar.addActionListener(this);
				Aa.addWindowListener(this);
				//CHOICE OFERTAS
				ResultSet Aof = Model.ejecutarSelect("SELECT idOferta, DATE_FORMAT(fechaOferta, '%d/%m/%Y'),DATE_FORMAT(fechaFinOferta, '%d/%m/%Y') FROM ofertas;", Model.conectar("practicamvc","root","Studium2018;"));
				try 
				{
					while(Aof.next())
					{
						String ofertas=Integer.toString(Aof.getInt("idOferta"));
						ofertas = ofertas+".-"+"Fecha Oferta:"+Aof.getString("DATE_FORMAT(fechaOferta, '%d/%m/%Y')")+" Fecha Fin Oferta:"+ Aof.getString("DATE_FORMAT(fechaFinOferta, '%d/%m/%Y')");
						Aa.ofertas.add(ofertas);
					}
				} catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));

				//CHOICE DEMANDANTES
				ResultSet Adm = Model.ejecutarSelect("SELECT * FROM demandantes", Model.conectar("practicamvc","root","Studium2018;"));
				try 
				{
					while(Adm.next())
					{
						String demandantes=Integer.toString(Adm.getInt("idDemandante"));
						demandantes = demandantes+".-"+ Adm.getString("nombreDemandante")+" "+ Adm.getString("apellidosDemandante")+" - "+Adm.getString("dniDemandante");
						Aa.demandantes.add(demandantes);
					}
				} catch (SQLException e) 
				{
					JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
				}
				Model.desconectar(Model.conectar("practicamvc","root" ,"Studium2018;"));
				Aa.txtFecha.setText(Model.Calendario());
			}
			if(Aa.btnAceptar.equals(ae.getSource())) 
			{
				String Fecha = Aa.txtFecha.getText();
				String[] arrayFecha = Fecha.split("/");
				try 
				{
					Fecha = arrayFecha[2]+"-"+arrayFecha[1]+"-"+arrayFecha[0];
				} catch (ArrayIndexOutOfBoundsException ai) {}
				String[] arrayOferta= Aa.ofertas.getSelectedItem().toString().split(".-");
				ofertaSeleccionada = Integer.parseInt(arrayOferta[0]);
				String[] arrayDemanda= Aa.demandantes.getSelectedItem().toString().split(".-");
				demandanteSeleccionado = Integer.parseInt(arrayDemanda[0]);
				Model.ejecutarIDA("INSERT INTO asignaciones VALUES (null,'"+Fecha+"', '"+ofertaSeleccionada+"', '"+demandanteSeleccionado+"');",Model.conectar("practicamvc","root","Studium2018;"));
				JOptionPane.showMessageDialog(null,"Asignación añadida con éxito","Asignación añadida", JOptionPane.INFORMATION_MESSAGE);
			} else if (Aa.btnCancelar.equals(ae.getSource())) 
			{
				MenuPrinci.setVisible(true);
				Aa.setVisible(false);
			}
		} catch(NullPointerException np){}
	}
	@Override
	public void windowActivated(WindowEvent arg0) {}
	@Override
	public void windowClosed(WindowEvent arg0) {}
	@Override
	public void windowClosing(WindowEvent arg0) 
	{
		System.exit(0);
	}
	@Override
	public void windowDeactivated(WindowEvent arg0) {}

	@Override
	public void windowDeiconified(WindowEvent arg0) {}

	@Override
	public void windowIconified(WindowEvent arg0) {}

	@Override
	public void windowOpened(WindowEvent arg0) {}
}
