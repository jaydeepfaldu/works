package com.hns.pojo;

public class StudTotalAtt {
	
	private String sbct;
	private int attlac;
	
	
	
	public StudTotalAtt(String sbct, int attlac)
	{
		this.sbct = sbct;
		this.attlac = attlac;
	}
	
	public void setSbct(String sbct)
	{
		this.sbct = sbct;
	}
	public String getSbct()
	{
		return sbct;
	}
	
	public void setAttLac(int attlac)
	{	
		this.attlac = attlac;		
	}
	
	public int getAttLac()
	{
		return attlac;
	}		

}
