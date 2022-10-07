package conncetion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

public class Connectiondatabase 
{
	
	Connection conn=null;
	PreparedStatement stmt=null;
	ResultSet rs=null;
	public static Connection getConnect()
	{
		try
		{
			//Class.forName("com.mysql.jdbc.Driver");
			Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","qwe123");
			return conn;
			//ResultSet rs=stmt.executeQuery("select * from book");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		return null;
	}
}