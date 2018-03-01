package com.hns;

import java.io.IOException;
import java.sql.ResultSet;
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
import com.hns.pojo.StudTotalAtt;

/**
 * Servlet implementation class StudLac
 */
@WebServlet("/studlac")
public class StudLac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudLac() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		
		String stream = request.getParameter("stm");
		String rollno = request.getParameter("rno");
		
		
		
		Connect con = new Connect();
		ResultSet rs = con.getData("select sum(BBB.nn) as total,  subject_name  from (select  CASE WHEN stud_status = 'A' THEN '0' else COUNT(stud_status) end as nn, subject_name from hnsas.dbo."+ stream +" AAA where AAA.stud_rollno = "+rollno+" group by AAA.subject_name, AAA.stud_status ) BBB group by BBB.subject_name  order by BBB.subject_name ");
		
		Map<Integer, StudTotalAtt> menu = new TreeMap<Integer, StudTotalAtt>();
		
		int i=1;
		
				
		try
		{			
			while(rs.next())
			{
				menu.put(i, new StudTotalAtt(rs.getString(2),rs.getInt(1)));
				i= i + 1;
			}
		}
		catch(Exception e)
		{
			System.out.println("Error in Stud_lac " + e);
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
