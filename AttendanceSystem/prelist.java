package com.hns;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
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


/**
 * Servlet implementation class prelist
 */
@WebServlet("/prelist")
public class prelist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public prelist() {
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
		//ResultSet rs = con.getData("Select ROW_NUMBER() OVER(ORDER BY date asc) AS Row, date, count(DISTINCT  lacture_no) from BBA_sem_5 group by date");
		
		//ResultSet rs = con.getData("Select ROW_NUMBER() OVER(ORDER BY date asc) AS Row, date, count(DISTINCT  lacture_no) from BBA_sem_5 group by date");
		ResultSet rs = con.getData("Select ROW_NUMBER() OVER(ORDER BY date asc) AS Row, convert(varchar, date, 103) as dt, count(DISTINCT  lacture_no) from hnsas.dbo."+stream + " group by date");
		
		Map<String, Date> menu = new TreeMap<String, Date>();
		
		
	
		
		try
		{
			while(rs.next())
			{
							
				Connect con1 = new Connect();
				
				HashMap<String, HeaderSubject> sub = new HashMap<String, HeaderSubject>();
				//ResultSet rs1 = con1.getData("Select  ROW_NUMBER() OVER(ORDER BY lacture_no asc) as ROW, subject_name from hnsas.dbo.BBA_sem_5  where date='"+rs.getString(2)+"' group by date, subject_name, lacture_no ");
				ResultSet rs1 = con1.getData("Select  ROW_NUMBER() OVER(ORDER BY lacture_no asc) as ROW, subject_name from "+stream+"  where convert(varchar,date,103)='"+rs.getString(2)+"' group by date, subject_name, lacture_no ");
				int i=1;
				while(rs1.next())
				{	
					
					//System.out.println(rs.getString(2) + "="+ i +"="+ rs1.getString(2));
					HeaderSubject s1 = new HeaderSubject(rs1.getString(2));				
					sub.put(i+"",s1);
					i++;
				}				
				Date m1 = new Date(rs.getInt(1), rs.getString(2),rs.getInt(3), sub);	
		
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
