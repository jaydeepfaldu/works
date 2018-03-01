package com.hns.pojo;

public class SubTotal {
	
	private String subjectname;
	private int totallac;
	
	public SubTotal(String subjectname, int totallac)
	{
		this.subjectname = subjectname;
		this.totallac = totallac;
	}

	public void setSubjectName(String subjectname){
		this.subjectname = subjectname;
	}
	
	public void setTotalLac(int totallac)
	{
		this.totallac = totallac;
	}
	
	public String getSubjectName()
	{
		return subjectname;
	}
	public int getTotalLac()
	{
		return totallac;
	}
}

