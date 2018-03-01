package com.hns.pojo;

public class TotalStrnth {

	private int classno;
	private int startvalue;
	private int endvalue;
	
	
	public TotalStrnth(int classno, int startvalue, int endvalue) {
		this.classno = classno;
		this.startvalue = startvalue;
		this.endvalue = endvalue;				
	}
	
	public void setClassNo(int classno)
	{
		this.classno = classno;
	}
	public void setStartValue(int startvalue)
	{
		this.startvalue = startvalue;
	}
	public void setEndValue(int endvalue)
	{
		this.endvalue = endvalue;
	}
	
	public int getClassNo()
	{
		return classno;
	}
	public int getStartValue()
	{
		return startvalue;
	}
	public int getEndValue()
	{
		return endvalue;
	}
	
}
