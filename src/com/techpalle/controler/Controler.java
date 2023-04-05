package com.techpalle.controler;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.techpalle.DAO.AdminDao;
import com.techpalle.DAO.CustomerDAO;
import com.techpalle.model.Customer;

@WebServlet("/")
public class Controler extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		String path=request.getServletPath();
		
		switch(path)
		{
			case"/delete":
				getDeleteForm(request,response);
				break;
				
			case"/edit":
				getUpdateForm(request,response);
				break;
				
			case"/editForm":
				getEditForm(request,response);
				break;
				
			case "/insertForm":
				getInsertForm(request,response);
				break;

			case "/add":
				addCustomer(request,response);
				break;
				
			case "/list":
				validateAdmin(request,response);
				break;
				
			case "/table":
				getCustomerpage(request ,response);
				break;
				
			default:
				getStartUpPage(request ,response);
				break;
		}
	}
	private void getCustomerpage(HttpServletRequest request, HttpServletResponse response)
	{
		try 
		{
			ArrayList<Customer> alcustomer=CustomerDAO.getAllCustomer(); 
			RequestDispatcher rd=request.getRequestDispatcher("customer_list.jsp");
			request.setAttribute("al",alcustomer);
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e)
		{
		
			e.printStackTrace();
		}

	}
	
	private void validateAdmin(HttpServletRequest request, HttpServletResponse response)
	{
		//read the username and password
		String u=request.getParameter("tbUser");
		String p=request.getParameter("tbPass");
		
		//call the dao method for validate admin
		boolean res=AdminDao.validadmin(u, p);
		
		//if condition is true redirect admin to list page
		if(res==true)
		{
			getCustomerpage(request, response);
		}
		else 
		{
			try 
			{
				response.sendRedirect(request.getContextPath()+"/default");
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}
	

	
	private void getDeleteForm(HttpServletRequest request, HttpServletResponse response)
	{
		
		int i=Integer.parseInt(request.getParameter("cid"));
		
		CustomerDAO.deleteCustomer(i);
		
		getCustomerpage(request, response);
//		try 
//		{
//			response.sendRedirect("list");
//		} 
//		catch (IOException e1)
//		{
//			e1.printStackTrace();
//		}
		
	}


	private void getUpdateForm(HttpServletRequest request, HttpServletResponse response) 
	{
		int i=Integer.parseInt(request.getParameter("tbId"));
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbEmail");
		long m=Long.parseLong(request.getParameter("tbMobile"));
		
		Customer c=new Customer(i, n, e, m);
		
		CustomerDAO.editCustomer(c);
		
		//getStartUpPage(request, response);
		getCustomerpage(request, response);
//		try 
//		{
//			response.sendRedirect("list");
//		} 
//		catch (IOException e1)
//		{
//			e1.printStackTrace();
//		}
		
	}


	private void getEditForm(HttpServletRequest request, HttpServletResponse response) 
	{
		// Fetch the id form url:
		
		int i=Integer.parseInt(request.getParameter("cid"));
		
		Customer c=CustomerDAO.getOneCustomer(i);
		
		
		try 
		{
			RequestDispatcher rd=request.getRequestDispatcher("customer_form.jsp");
			request.setAttribute("customer", c);
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e) 
		{
			
			e.printStackTrace();
		}
		
		
		
	}


	private void getInsertForm(HttpServletRequest request, HttpServletResponse response) 
	{
		try 
		{
			
			RequestDispatcher rd=request.getRequestDispatcher("customer_form.jsp");
			rd.forward(request, response);
		} 
		catch (ServletException | IOException e) 
		{
			e.printStackTrace();
		}
	
	
	}


	private void addCustomer(HttpServletRequest request, HttpServletResponse response) 
	{
		String n=request.getParameter("tbName");
		String e=request.getParameter("tbEmail");
		long m=Long.parseLong(request.getParameter("tbMobile"));
		
		Customer c=new Customer(n,e,m);
		
		CustomerDAO.insertCustomer(c);
		
		getCustomerpage(request, response);
		
	}


	private void getStartUpPage(HttpServletRequest request, HttpServletResponse response)
	{
			try 
			{
				RequestDispatcher rd=request.getRequestDispatcher("admin_login.jsp");
				rd.forward(request, response);
			} 
			catch (ServletException | IOException e)
			{
				
				e.printStackTrace();
			}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doGet(request, response);
	}

}
