<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HNS Attendance System</title>

<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>

<div class="div_header">
	HNS Attendance System
</div>
<div class="div_header_user">
	Hello, <span >
					<%
					
					if(session.getAttribute("name") == null)
					{
						response.sendRedirect("/HNSAS/index.html");
					}
							
						out.println(request.getAttribute("user_name"));
					%>
				</span>
</div>



</body>
</html>