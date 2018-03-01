package com.hns.pojo;

import java.util.HashMap;

public class Date {
	
	private int id;
	private String dt;
	private int mergecolsize;
	private HashMap<String, HeaderSubject> subject;
	
	
	public Date(int id, String dt,int mergecolsize, HashMap<String, HeaderSubject> subject){
		this.id = id;
		this.dt = dt;
		this.mergecolsize = mergecolsize;
		this.subject = subject;
	}
	
	
	public void setId(int id)
	{
		this.id = id;
	}
	public int getId()
	{
		return id;
	}	
	public void setDt(String dt)
	{
		this.dt = dt;
	}
	public String getDt()
	{
		return dt;
	}	
	public void setMergecolsize(int mergecolsize)
	{
		this.mergecolsize = mergecolsize;
	}
	public int getMergecolsize()
	{
		return mergecolsize;
	}
	
	
	public void setSubject(HashMap<String, HeaderSubject> subject)
	{
		this.subject = subject;
	}
	public HashMap<String, HeaderSubject> getSubject()
	{
		return subject;
	}
}
