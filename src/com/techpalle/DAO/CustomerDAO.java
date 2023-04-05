package com.techpalle.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.techpalle.model.Customer;

public class CustomerDAO
{
	private static final String dburl="jdbc:mysql://localhost:3306/customer_mangement";
	private static String dbusername="root";
	private static String dbpassword="admin";
	
	private static Connection con=null;
	private static Statement s=null;
	private static PreparedStatement ps =null;
	private static ResultSet rs=null;
	
	private static final String qry="select * from customer";
	private static final String inQry="insert into customer (name,email,mobile)values(?,?,?)";
	private static final String EditQry="select * from customer where cid=?";
	private static final String UpdQry="update customer set name=?,email=?,mobile=? where cid=?";
	private static final String DelQry="delete from customer where cid=?";
	
			
	public static Connection getConnectionDef()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			con=DriverManager.getConnection(dburl, dbusername, dbpassword);		
		} 
		catch (ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
		return con;
	}
	
	public static void deleteCustomer(int cid)
	{
			try
			{
				con=getConnectionDef();
				ps=con.prepareStatement(DelQry);
				ps.setInt(1, cid);
				ps.executeUpdate();
			} 
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			finally 
			{
				try
				{
					if (ps != null)
					{
						s.close();
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
	}
	
	public static void editCustomer(Customer c)
	{
		
		try 
		{
			con=getConnectionDef();
			ps=con.prepareStatement(UpdQry);
			
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail());
			ps.setLong(3, c.getMobile());
			ps.setInt(4, c.getCid());
			
			ps.executeUpdate();
		} 
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				if (ps != null)
				{
					s.close();
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
	}
	
	public static Customer getOneCustomer(int cid)
	{
		Customer c=null;
		try 
		{
			con=getConnectionDef();
			ps=con.prepareStatement(EditQry);

			ps.setInt(1, cid);
		
			rs=ps.executeQuery();
			
			rs.next();
			
			int i =rs.getInt("cid");
			String n=rs.getString("Name");
			String e=rs.getString("email");
			long m=rs.getLong("mobile");
			
			c=new Customer(i, n, e, m);

		}
		catch (SQLException e)
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
					s.close();
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
		return c;
		
	}
	public static void insertCustomer(Customer customer)
	{
		try 
		{
		con=getConnectionDef();
		ps=con.prepareStatement(inQry);
		
		ps.setString(1 ,customer.getName());
		ps.setString(2 ,customer.getEmail());
		ps.setLong(3,customer.getMobile());
		
		ps.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}

		finally 
				{
				
					
					try
					{
						
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
	}

	
	public static ArrayList<Customer> getAllCustomer()
	{
		
		ArrayList<Customer>al=new ArrayList<Customer>();
		
				try
				{
					
					con=getConnectionDef();
					
					s=con.createStatement();
					rs=s.executeQuery(qry);
					
					while(rs.next())
					{
						int i =rs.getInt("cid");
						String n=rs.getString("Name");
						String e=rs.getString("Email");
						long m=rs.getLong("Mobile");

						
						Customer c=new Customer(i, n, e, m);
						al.add(c);
					}
					
				} 
				catch (SQLException e)
				{
					e.printStackTrace();
				}
				
			finally 
					{
						//close connection
						
						try
						{
							if (rs != null)
							{
								rs.close();
							}
							if (s != null)
							{
								s.close();
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
				return al;
	
	}
}
