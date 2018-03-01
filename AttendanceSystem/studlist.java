package com.hns;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hns.database.Connect;
import com.hns.pojo.Date;
import com.hns.pojo.HeaderSubject;
import com.hns.pojo.studroll;
import com.hns.pojo.studsub;

/**
 * Servlet implementation class studlist
 */
@WebServlet("/studlist")
public class studlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public studlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		
		String stream = request.getParameter("stm");
		
		
		
		Connect con = new Connect();
		//ResultSet rs = con.getData("Select ROW_NUMBER() OVER(ORDER BY stud_rollno asc) as ROW, [hnsas].dbo.BBA_sem_5.stud_rollno as ss  from [hnsas].dbo.BBA_sem_5 group by [hnsas].dbo.BBA_sem_5.stud_rollno order by [hnsas].dbo.BBA_sem_5.stud_rollno");
		
		ResultSet rs = con.getData("Select ROW_NUMBER() OVER(ORDER BY stud_rollno asc) as ROW, stud_rollno as ss  from "+stream+" group by stud_rollno order by stud_rollno");
		
		Map<String, studroll> menu = new TreeMap<String, studroll>();
		//HashMap<String, studroll> menu = new HashMap<String, studroll>();
		
		try
		{
			while(rs.next())
			{
				
				
				Connect con1 = new Connect();
				//HashMap<String, studsub> sub = new HashMap<String, studsub>();
				Map<String, studsub> sub = new TreeMap<String, studsub>();
				ResultSet rs1 = con1.getData("Select  ROW_NUMBER() OVER(ORDER BY date , lacture_no asc) as ROW, (case when stud_status ='A' then 'A' else subject end) as stat from "+stream+"  where stud_rollno="+rs.getString(2)+" order by date asc, lacture_no, subject_name   ");
				//ResultSet rs1 = con1.getData("Select  ROW_NUMBER() OVER(ORDER BY date, lacture_no asc) as ROW, (case when stud_status ='A' then 'A' else subject end) as stat from hnsas.dbo.BBA_sem_5  where stud_rollno="+rs.getString(2)+" order by date, lacture_no   ");
				int i=1;
				while(rs1.next())
				{	
					//System.out.println(rs1.getString(1));
					studsub s1 = new studsub(rs1.getString(2));				
					sub.put(i+"",s1);
					i++;
				}				
				studroll m1 = new studroll(rs.getInt("ss"), sub);				
				menu.put(rs.getString(1), m1);
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		
		String json = null ;
	    json= new Gson().toJson(menu);	    
	    
	    response.setContentType("application/json");
	    response.setCharacterEncoding("UTF-8");
	    response.getWriter().write(json);  
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
