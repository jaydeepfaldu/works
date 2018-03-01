package com.hns.pojo;

import java.util.HashMap;
import java.util.Map;

import javax.tools.SimpleJavaFileObject;

public class studroll {
	
	private int rollno;
	private Map<String, studsub> subject;
	
	public studroll(int rollno, Map<String, studsub> subject) {
		this.rollno = rollno;
		this.subject = subject;
	}
	
	public void setStudroll(int rollno)
	{
		this.rollno = rollno;
	}
	public int getStudroll()
	{
		return rollno;
	}
	
	
	public void setSubject(HashMap<String, studsub> subject)
	{
		this.subject = subject;
	}
	public Map<String, studsub> getSubject()
	{
		return subject;
	}
	
}

