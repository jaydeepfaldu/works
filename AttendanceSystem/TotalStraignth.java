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
import com.hns.pojo.SubTotal;
import com.hns.pojo.TotalStrnth;

/**
 * Servlet implementation class TotalStraignth
 */
@WebServlet("/totalstraignth")
public class TotalStraignth extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TotalStraignth() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String stream = request.getParameter("stm");
		
		Connect con = new Connect();
		ResultSet rs = con.getData("SELECT class_no, class_start, class_end from class_seat_detail where class_name='"+stream+"'");
		
		Map<Integer, TotalStrnth> total = new TreeMap<Integer, TotalStrnth>();
		
		int i = 1;
		try
		{
			while(rs.next())
			{
				total.put(i,new TotalStrnth(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
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
