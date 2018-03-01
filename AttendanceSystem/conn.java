package com.hns;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hns.database.Connect;



/**
 * Servlet implementation class conn
 */

public class conn extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public conn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String id = "" ;
		String pass = "";
		if(request.getParameter("txtname") != null || request.getParameter("txtpass") !=null)
		{
			id = request.getParameter("txtname");
			 pass = request.getParameter("txtpass");
		}
		
		Connect con = new Connect();		
		ResultSet rs = con.getData("SELECT * FROM faculty WHERE faculty_name ='"+id.toString().trim()+"' and faculty_pass='"+pass.toString().trim()+"'");
		
		try
		{			
			rs.next();
			
		if(rs.getRow() > 0)
		{			
			Connect con1 = new Connect();
			DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
			
			Date date = new Date();
			
			String ss = dateformat.format(date).toString();
			
			HttpSession session=request.getSession();  
			session.setAttribute("name",rs.getString("faculty_name"));
			
			con1.interData("Insert into log (faculty_name, login_date, login_time) values ('"+rs.getString("faculty_name")+"', '"+dateformat.format(date)+"', '"+timeformat.format(date)+"' )");
			
			//response.addCookie(new Cookie("user",rs.getString("user_name")));
						
			request.setAttribute("user_name",rs.getString("faculty_name"));			
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			
		}
		else
		{
		
				response.sendRedirect("/HNSAS/index.html?msg=user_invalid");
		
		}
		}
		catch(Exception e)
		{
			System.out.println("Error in login"+e);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
