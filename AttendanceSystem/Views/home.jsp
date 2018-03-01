<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HNS Attendance System</title>

<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="css/dropdown.css" rel="stylesheet" type="text/css">
<link href="css/menugrid.css" rel="stylesheet" type="text/css">


<style type="text/css">
#grid{
	width: 100%;
    height: 150px;
    overflow: scroll;
}
table { border: 2px solid black;
	border-collapse: collapse;}
td { border: thin solid black }

</style>



<script type="text/javascript" src="js/jquery-1.3.2.js"></script>


<script type="text/javascript">


function urlget()
{
	//var myParam =  location.pathname.toString(); //location.search.split('error=')[1] ? location.search.split('error')[1] : 'not have';
	
	<%
		if(request.getAttribute("error")!=null)
		{	
	%>
		 alert("Selected lacture is already placed");		 		
	<%
		}
	%>
	
//	alert(myParam);
		
}


$(document).ready(function(){
	
		 load_stream("BBA_sem_1");
	 	 
		 var today = new Date();
         	    
		    var dt = today.getDate();
		    $('#dt').val(dt);
		    
		    var mt = today.getMonth();
		   	$('#mt').val(mt+1);
		    
		    var yr = today.getFullYear();    
		    $('#yr').val(yr);
		    
		 
	 
	 $("input[type=radio]").change(function()
	 {
		 var stream = $('input[name=stream]:checked').val();
		 var sem = $('input[name=sem]:checked').val();
		 
		 
		 load_stream(stream+"_"+sem);
		 
	 });
				
	 
});


function load_stream(str1)
{	 	
	str = "#"+str1;
	
	
	var cl = $('input[name=class]:checked').val();
	 
	 
	 var JSONObj= {"stm":str1+"_"+cl};
	 
	 var st = 0;
	 var end = 0;
	 
	 
	 $.ajax({
		      //  url: '/HNSAS/prelist?stm='+x.value,
		    	url: '/HNSAS/totalstraignth',
		        type: "POST",
		        data: JSONObj,
		        async: false,
		        dataType: 'application/json; charset=utf-8',
		        success: function (data) {
		        		        		        	        	
		        	var returnedData = JSON.parse(data);	   
		        
		     	
		         $.each(returnedData, function(i, field){
		        	
		    		st = (parseInt(field["startvalue"]));
		    		end = (parseInt(field["endvalue"]));
		        	 
		         });
		        	
		        }		        
		       
		        
		    });
	 
	
	$("#att_fill").empty();
	$("#att_fill").append("<tr bgcolor='Green'>	<td colspan=3>STUDENT ROLL NO</td></tr>");
	
	$("#rsrt").val(st);
	$("#rend").val(end);
	
	
	
	
	 for (i = st; i <= end; i++) {
		 $("#att_fill").append("<tr><td ><input type='checkbox' value='"+i+"' name='roll"+i+"'>"+i+"</td></tr>");	
	 }
	 
	 $("#att_fill").append("<tr><td colspan=3 ><input type='submit' value='SUBMIT' style='margin-bottom: 20px;'></td></tr>");
	 
	
	
	
	$("select").hide();
    $(str).show();
    	
    $('#dt').show();
    $('#mt').show();
    $('#yr').show();
    $("#sub").val($(str).val());  
    $("#subname").val($(' '+str+' option:selected').text());
}

function chng_BBA1() {
	$("#sub").val($("#BBA_sem_1").val());
	
	$("#subname").val($('#BBA_sem_1 option:selected').text());
}

function chng_BBA3() {
	$("#sub").val($("#BBA_sem_3").val());
	
	$("#subname").val($('#BBA_sem_3 option:selected').text());
}

function chng_BBA5() {
	$("#sub").val($("#BBA_sem_5").val());
	
	$("#subname").val($('#BBA_sem_5 option:selected').text());
	
	
	
}

function chng_BCOM1() {
	$("#sub").val($("#BCOM_sem_1").val());
}

function chng_BCOM3() {
	$("#sub").val($("#BCOM_sem_3").val());
}

function chng_BCOM5() {
	$("#sub").val($("#BCOM_sem_5").val());
}

</script>


</head>
<body onload="urlget()">

<div class="div_header">
	HNS Attendance System
</div>
<div style="width: 100%; " align="center">


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

<div >
		<form action="/HNSAS/attandance" method="post">
		
		<input type="hidden" name="rstart" id="rsrt">
		<input type="hidden" name="rend" id="rend">
		
		<input value="<%out.println(request.getAttribute("user_name")); %>"  type="hidden" name="fname" > 
		<table class="home_table">
			<tr bgcolor="Green">
				<td colspan="3"> DATE </td>		
						
			</tr>
			<tr  >
				<td> DATE : <select id="dt" name="dt">
					 <%
					 	for( int i = 1; i<=31; i++)
					 	{
					 		
					 			if(i<10)
					 			{
					 			%>
					 				<option value=<% out.println(i);%>> <% out.println("0"+i); %> </option>
					 			<%
					 			}
					 			else
					 			{
					 			%>
					 				<option value=<% out.println(i);%>> <% out.println(i); %> </option>
					 			<%
					 			}
					 	}
					 %>
				</select> </td>
				<td> MONTH : <select id="mt" name="mt">
					 <%
					 	for( int i = 1; i<=12; i++)
					 	{
					 		if(i<10)
				 			{
				 			%>
				 				<option value=<% out.println(i);%>> <% out.println("0"+i); %> </option>
				 			<%
				 			}
				 			else
				 			{
				 			%>
				 				<option  value=<% out.println(i);%>> <% out.println(i); %> </option>
				 			<%
				 			}
				 		
					 	}
					 %>
				</select> </td>
				<td> YEAR : <select id="yr" name="yr">
					 <%
					 	for( int i = 16; i<=20; i++)
					 	{
					 		%>
					 			<option> 20<% out.println(i); %> </option>
					 		<%
					 	}
					 %>
				</select> </td>
			</tr>
		</table>
		<table class="home_table">
			<tr bgcolor="Green" > 
				<td colspan="2"> STREAM </td> 
			</tr>
			<tr >
				<td >	<input type="radio"  name="stream" value="BBA" checked="checked"> B.B.A.</td> 
					
				<td >	<input type="radio" name="stream" value="BCOM" disabled="disabled"> B.COM. </td>				
				
			</tr>
		</table>
		<table  class="home_table" >
			<tr bgcolor="Green"> 
				<td colspan="3"> SEMESTER </td> 
			</tr>
			<tr>
				<td>	<input type="radio"  name="sem" value="sem_1" checked="checked"> 1st </td>		
					
				<td>	<input type="radio" name="sem" value="sem_3" > 3rd </td>
		
				<td>	<input type="radio" name="sem" value="sem_5" > 5th </td>
			</tr>			
		</table> 
		
		<table  class="home_table" >
			<tr bgcolor="Green"> 
				<td colspan="5"> CLASS </td> 
			</tr>
			<tr>
				<td>	<input type="radio"  name="class" value="A" checked="checked"> A </td>		
					
				<td>	<input type="radio" name="class" value="B" > B </td>
		
				<td>	<input type="radio" name="class" value="C" > C </td>
				<td>	<input type="radio" name="class" value="D" > D </td>
				<td>	<input type="radio" name="class" value="E" > E </td>
			</tr>			
		</table> 
		
		
		<table  class="home_table" >
			<tr bgcolor="Green"> 
				<td colspan="8"> LACTURE NO. </td> 
			</tr>
			<tr>
				<td>	<input type="radio"  name="lacno" value="1" checked="checked"> 1st  </td>		
				<td>	<input type="radio"  name="lacno" value="2" > 2nd  </td>
				<td>	<input type="radio"  name="lacno" value="3" > 3rd  </td>
				<td>	<input type="radio"  name="lacno" value="4" > 4th  </td>
			</tr>
			<tr>
				<td>	<input type="radio"  name="lacno" value="5" > 5th  </td>
				<td>	<input type="radio"  name="lacno" value="6" > 6th  </td>
				<td>	<input type="radio"  name="lacno" value="7" > 7th  </td>
				<td>	<input type="radio"  name="lacno" value="8" > 8th  </td>				
			</tr>			
		</table> 
		
		<table  class="home_table" >
			<tr bgcolor="Green">
				<td>SUBJECT</td>
			</tr>
		</table>
		
		<div style="text-align: center; padding-top: 20px; padding-bottom: 20px;">
				
				<select id="BBA_sem_1" onchange="chng_BBA1()">
				
					<option value="PCS" label="CS"> CS  </option>
					<option value="PFB" label="FBO"> FBO  </option>
					<option value="PGS" label="GSI"> GSI  </option>
					<option value="PEC" label="ECO"> ECO  </option>
					<option value="PAC" label="AC"> AC  </option>
					<option value="PMT" label="POM"> POM  </option>
					<option value="PMS" label="MAT"> MAT  </option>
					<option value="POA" label="OAT"> OAT  </option>

				</select>
			
				<select id="BBA_sem_3" onchange="chng_BBA3()">
					<option value="PCS" label="CS"> CS3 </option>
					<option value="PMM" label="MM"> MM  </option>
					<option value="PHR" label="HRM"> HRM  </option>
					<option value="PME" label="ME"> ME  </option>
					<option value="PET" label="ENT"> ENT </option>
					<option value="PST" label="STAT"> STAT  </option>
					<option value="PFM" label="FM"> FM  </option>
					<option value="PAC" label="CAC"> CAC  </option>					
				</select>
				
				
				<select id="BBA_sem_5" onchange="chng_BBA5()">
					<option value="PMM" label="MM"> MM  </option>
					<option value="PBE" label="BE"> BE  </option>
					<option value="PTAX" label="TAX"> TAX  </option>
					<option value="PMA" label="MGTAC"> MGTAC  </option>
					<option value="PBL" label="BL"> BL </option>
					<option value="PFM" label="AFM"> AFM </option>
					<option value="PAM" label="AMM"> AMM  </option>
					<option value="PHR" label="HRM"> HRM  </option>									
				</select>
				
				
				<select id="BCOM_sem_1" onchange="chng_BCOM1()">
					<option> BCOM_1  </option>
					<option> BCOM_1  </option>
					<option> BCOM_1  </option>
					<option> BCOM_1  </option>
					<option> FS_1 </option>
					<option> FM_1 </option>
					<option> HRM_1  </option>
					<option> FB  </option>
					<option> AF_5  </option>
					<option> BM_5  </option>
					<option> CS_5  </option>
					<option> BANKING_5  </option>					
				</select>
				
				<select id="BCOM_sem_3" onchange="chng_BCOM3()">
					<option> BCOM_3  </option>
					<option> BCOM_3  </option>
					<option> BCOM_3  </option>
					<option> PA_1  </option>
					<option> FS_1 </option>
					<option> FM_1 </option>
					<option> HRM_1  </option>
					<option> FB  </option>
					<option> AF_5  </option>
					<option> BM_5  </option>
					<option> CS_5  </option>
					<option> BANKING_5  </option>					
				</select>
				
				<select id="BCOM_sem_5" onchange="chng_BCOM5()">
					<option> ENG_5  </option>
					<option> BE_1  </option>
					<option> MA_1  </option>
					<option> PA_1  </option>
					<option> FS_1 </option>
					<option> FM_1 </option>
					<option> HRM_1  </option>
					<option> FB  </option>
					<option> AF_5  </option>
					<option> BM_5  </option>
					<option> CS_5  </option>
					<option> BANKING_5  </option>					
				</select>
			<input id="sub" name="sub" type="hidden">
			<input id="subname" name="subname" type="hidden">			
		</div>		
		
		
		<table class="home_table" id="att_fill" >
			
		</table>
		
	</form>
</div>

</body>
</html>
