package com.techpalle.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDao 
{
	
	private static final String dburl="jdbc:mysql://localhost:3306/customer_mangement";
	private static String dbusername="root";
	private static String dbpassword="admin";
	
	private static Connection con=null;
	private static PreparedStatement ps =null;
	private static ResultSet rs=null;
	
	private static final String ValiQry="select * from store_admin where username=? and password=?";
	
	public static boolean validadmin(String user,String pass)
	{
		boolean b=false;
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(dburl, dbusername, dbpassword);	
			
			ps=con.prepareStatement(ValiQry);
			ps.setString(1, user);
			ps.setString(2, pass);
			rs=ps.executeQuery();
			
			b=rs.next();	
			
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		finally 
		{
			
			try
			{
				if (rs != null)
				{
					rs.close();
				}
				if (ps != null)
				{
					ps.close();
				}
				if (con != null)
				{
					con.close();
				}
				
			}
			catch (SQLException e) 
			{
			
				e.printStackTrace();
				
			}
		}
		return b;
	}


}
