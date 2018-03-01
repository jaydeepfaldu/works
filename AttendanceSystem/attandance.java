package com.hns;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.util.DateTool;

import com.hns.database.Connect;

/**
 * Servlet implementation class attandance
 */

public class attandance extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public attandance() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session=request.getSession(false);
		if(session == null)
		{
			response.sendRedirect("/HNSAS/index.html");			
		}
		
		int rsrt =  Integer.valueOf(request.getParameter("rstart"));
		int rend =  Integer.valueOf(request.getParameter("rend"));
		
		String dd = request.getParameter("dt");
		String mm = request.getParameter("mt");
		String yy = request.getParameter("yr");
		
	    if(Integer.valueOf(dd)<9)
		{
			dd = "0"+dd;
		}
		
		if(Integer.valueOf(mm)<9)
		{
			mm = "0"+mm;
		}
		
		
		String date1 = dd+"/"+mm+"/"+yy;
		
		String pattern = "dd/MM/yyyy";
		 SimpleDateFormat format = new SimpleDateFormat(pattern);
		
		Date date = null;
		try {
			date = format.parse(date1);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		
		//String date = request.getParameter("dt")+ "-" + request.getParameter("mt") +"-"+ request.getParameter("yr");
			
		//String[] ss =  request.getParameterValues("roll");
		
		//System.out.println(date);
		
				
		Connect con = new Connect();
		int flag = 0;
		
				
		ResultSet rs = con.getData("select  * from " +request.getParameter("stream")+"_"+request.getParameter("sem")+"_"+request.getParameter("class")+" where convert(varchar, date, 103) = '"+date1+"' and lacture_no = '" +request.getParameter("lacno")+"'");
		try {
			rs.next();
			
			if(rs.getRow() > 0)
			{				
				flag = 1;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("ERROR ::::::::"); e.printStackTrace();
		}
		
		
		
		if(flag==0)
		{
			
		
		for(int i=rsrt; i<=rend; i++)
		{
			
			
			String[] ss =  request.getParameterValues("roll"+i);
			
			if(ss == null)
			{
				con.interData("INSERT INTO "+request.getParameter("stream")+"_"+request.getParameter("sem") + "_" +request.getParameter("class") +" values ((CONVERT(date,'"+date1+"',104)),'"+request.getParameter("sub")+"',"+i+",'A','"+request.getParameter("fname")+"','"+request.getParameter("lacno")+"', '"+request.getParameter("subname")+"')");	
			}
			else
			{
				con.interData("INSERT INTO "+request.getParameter("stream")+"_"+request.getParameter("sem") + "_" +request.getParameter("class") +" values ((CONVERT(date,'"+date1+"',104)),'"+request.getParameter("sub")+"',"+i+",'P','"+request.getParameter("fname")+"','"+request.getParameter("lacno")+"', '"+request.getParameter("subname")+"')");
			}
			
		}
		}
		/*for(int i=1; i< ss.length; i++)
		{
			con.interData("INSERT INTO "+request.getParameter("stream")+"_"+request.getParameter("sem")+" values "+"('"+date+"','"+request.getParameter("sub")+"',"+ss[i]+",'P','"+request.getParameter("fname")+"')");
		}
		
		for(int i=1; i<=399; i++ )
		{
			con.interData("INSERT INTO "+request.getParameter("stream")+"_"+request.getParameter("sem")+" values "+"('"+date+"','"+request.getParameter("sub")+"',"+i+",'A','"+request.getParameter("fname")+"')");
		}*/
		
		if(flag==1)
		{
			request.setAttribute("user_name",request.getParameter("fname"));	
			request.setAttribute("error","yes");
			
			
			request.getRequestDispatcher("home.jsp").forward(request, response);
			//return;
		}
		else
		{		
			response.sendRedirect("/HNSAS/index.html");
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
