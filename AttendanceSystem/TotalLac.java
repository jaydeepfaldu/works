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
import com.hns.pojo.SubTotal;

/**
 * Servlet implementation class TotalLac
 */
@WebServlet("/totallac")
public class TotalLac extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalLac() {
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
		ResultSet rs = con.getData("SELECT ABC.subject_name, COUNT(AA) AS total_sub FROM (select subject_name, COUNT(distinct subject_name) AS AA from "+stream+" group by date, lacture_no, subject_name) ABC GROUP BY ABC.subject_name");
		
		Map<Integer, SubTotal> total = new TreeMap<Integer, SubTotal>();
		int i = 1;
		try
		{
			while(rs.next())
			{
				total.put(i,new SubTotal(rs.getString(1), rs.getInt(2)));
				i = i+1;
			}
		}
		catch(Exception ex){
			System.out.println("Error in total lac." + ex.getMessage());
		}
		
		String json = null ;
	    json= new Gson().toJson(total);	  	    
	    
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
