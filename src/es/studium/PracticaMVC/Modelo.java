package es.studium.PracticaMVC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;

import javax.swing.JOptionPane;

public class Modelo 
{
	public Connection conectar(String baseDatos, String usuario, String clave)
	{
		String driver = "com.mysql.jdbc.Driver";
		String url ="jdbc:mysql://localhost:3306/"+baseDatos+"?autoReconnect=true&useSSL=false";
		String login = usuario;
		String password = clave;
		Connection connection = null;

		try
		{
			Class.forName(driver);
			connection = DriverManager.getConnection(url, login,password);
		}
		catch (ClassNotFoundException cnfe)
		{
			JOptionPane.showMessageDialog(null,cnfe.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		catch (SQLException sqle)
		{
			JOptionPane.showMessageDialog(null,sqle.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
		return connection;
	}
	public void desconectar(Connection c) 
	{
		try
		{
			if(c!=null)
			{
				c.close();
			}
		}
		catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	public ResultSet ejecutarSelect(String sentencia, Connection c) 
	{

		try
		{
			Statement statement = c.createStatement();
			ResultSet rs= statement.executeQuery(sentencia);
			return rs;
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}

	}
	public void ejecutarIDA(String sentencia, Connection c) 
	{

		try
		{
			Statement statement = c.createStatement();
			statement.executeUpdate(sentencia);
		}
		catch(SQLException e)
		{
			JOptionPane.showMessageDialog(null,e.getMessage(),"Error", JOptionPane.ERROR_MESSAGE);
		}

	}
	public String Calendario() {
		Calendar horaFecha = Calendar.getInstance();
		int dia,mes,anyo;

		dia = horaFecha.get(Calendar.DAY_OF_MONTH);
		mes = horaFecha.get(Calendar.MONTH)+1;
		anyo = horaFecha.get(Calendar.YEAR);

		if(dia<10 && mes<10) 
		{
			return "0"+dia+"/"+"0"+mes+"/"+anyo;
		} else if(dia<10 && mes>=10) 
		{
			return "0"+dia+"/"+mes+"/"+anyo;
		} else if (dia>=10 && mes<10) 
		{
			return dia+"/"+"0"+mes+"/"+anyo;
			
		} else if(dia>=10 && mes<=10) 
		{
			return dia+"/"+mes+"/"+anyo;
		}
		return dia+"/"+mes+"/"+anyo;
		
	}
}
