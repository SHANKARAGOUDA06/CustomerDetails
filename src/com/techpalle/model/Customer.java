package com.techpalle.model;

public class Customer 
{
	private int cid;
	private String name;
	private String email;
	private long mobile;
	
	public int getCid() 
	{
		return cid;
	}
	public void setCid(int cid) 
	{
		this.cid = cid;
	}
	public String getName() 
	{
		return name;
	}
	public void setName(String name) 
	{
		this.name = name;
	}
	public String getEmail()
	{
		return email;
	}
	public void setEmail(String email)
	{
		this.email = email;
	}
	public long getMobile() 
	{
		return mobile;
	}
	public void setMobile(long mobile) 
	{
		this.mobile = mobile;
	}
	public Customer(int cid, String name, String email, long mobile) 
	{
		super();
		this.cid = cid;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	public Customer(String name, String email, long mobile)
	{
		super();
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}
	
	
	
}
