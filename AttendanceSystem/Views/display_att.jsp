<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HNS Attendance System</title>


<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.table2excel.js"></script>
<script type="text/javascript" src="js/jquery.dataTables.min.js"></script>
<link href="css/style.css" rel="stylesheet" type="text/css">


<script >

function fnExcelReport()
{
    var tab_text="<table border='2px'><tr bgcolor='#87AFC6'>";
    var textRange; var j=0;
    tab = document.getElementById('data'); // id of table

    for(j = 0 ; j < tab.rows.length ; j++) 
    {     
        tab_text=tab_text+tab.rows[j].innerHTML+"</tr>";
        //tab_text=tab_text+"</tr>";
    }

    tab_text=tab_text+"</table>";
    tab_text= tab_text.replace(/<A[^>]*>|<\/A>/g, "");//remove if u want links in your table
    tab_text= tab_text.replace(/<img[^>]*>/gi,""); // remove if u want images in your table
    tab_text= tab_text.replace(/<input[^>]*>|<\/input>/gi, ""); // reomves input params

    var ua = window.navigator.userAgent;
    var msie = ua.indexOf("MSIE "); 

    if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./))      // If Internet Explorer
    {
        txtArea1.document.open("txt/html","replace");
        txtArea1.document.write(tab_text);
        txtArea1.document.close();
        txtArea1.focus(); 
        sa=txtArea1.document.execCommand("SaveAs",false,"Say Thanks to Sumit.xls");
    }  
    else                 //other browser not tested on IE 11
        sa = window.open('data:application/vnd.ms-excel,' + encodeURIComponent(tab_text));	

    return (sa);
}

</script>

<script type="text/javascript">


$(document).ready(function () {
	
	
	
	 
	 $(document).ajaxStart(function () {
	        $("#waitmsg").show();
	        $("#buttongrid").hide();
	                
	    }).ajaxStop(function () {
	        $("#waitmsg").hide();
	        $("#buttongrid").show();	
	    });
	
	
	$("#search").click(function(){
		$("#data tr").empty();
				
		loadhead();	
		loadData();		
		
	});
		
	loadhead();
	
	loadData();

	
	
	
	//var jsonData = JSON.parse( JSONObject );
	
	function loadhead()
	{
		var strm = document.getElementById("strm");
		var sem = document.getElementById("sem");
		var cls = document.getElementById("class");
		
		var JSONObject= {"stm":strm.value+"_sem_"+sem.value+"_"+cls.value};
		
		 $.ajax({
		      //  url: '/HNSAS/prelist?stm='+x.value,
		    	url: '/HNSAS/prelist',
		        type: "POST",
		        data: JSONObject,
		        dataType: 'application/json; charset=utf-8',
		        success: function (data) {
		        		        		        	        	
		        	var returnedData = JSON.parse(data);	   
		        
		     
		        	
		        	$("#att_head").append("<td align='center' rowspan='2'>ROLL NO</td>");		        	
		        	
		        	 $.each(returnedData, function(i, field){
		        	               
		               $("#att_head").append("<td  colspan='"+field["mergecolsize"]+"' align='center' > "+field["dt"]+"</td>");
		               
		               
		               $.each(field["subject"], function(i, ff){
		            	   $("#lac_head").append("<td  align='center'>"+ff["sub"]+"</td>");		            	   
		               });  
		              		              
		               
		              		               
		             });
		        	 
		        	 
		        	
		        	 
		        	 $.ajax({
		   		      //  url: '/HNSAS/prelist?stm='+x.value,
		   		    	url: '/HNSAS/totallac',
		   		        type: "POST",
		   		        data: JSONObject,
		   		        dataType: 'application/json; charset=utf-8',
		   		        success: function (data) {
		   		        		        		        	        	
		   		        	var returnedData = JSON.parse(data);	   
		   		        
		   		     	
		   		         $.each(returnedData, function(i, field){
		   		        	
		   		    		 $("#att_head").append("<td style='background-color : yellow; color:#004080;' align='center'>"+field["subjectname"]+"</td>");
		   		        	 $("#lac_head").append("<td style='background-color : yellow; color:#004080; font-weight:bold;' align='center'>"+field["totallac"]+"</td>");		   		        	 
		   		        	
		   		         
		   		         });
		   		  
		   		        }		        
		   		       
		   		        
		   		    });
		   		 
		        				        	
		        	
		        }		        
		       
		        
		    });
		 
		
		 
	}
	
	
	function getObjects(obj, key, val) {
	    var objects = [];
	    for (var i in obj) {
	        if (!obj.hasOwnProperty(i)) continue;
	        if (typeof obj[i] == 'object') {
	            objects = objects.concat(getObjects(obj[i], key, val));
	        } else if (i == key && obj[key] == val) {
	            objects.push(obj);
	        }
	    }
	    return objects;
	}
	
	
	
	
	
	function loadData()
	{
		
		//var x = document.getElementById("stream");
		var strm = document.getElementById("strm");
		var sem = document.getElementById("sem");
		var cls = document.getElementById("class");
		
		var JSONObject= {"stm":strm.value+"_sem_"+sem.value+"_"+cls.value};
		
		 $.ajax({
		        //url: '/HNSAS/studlist?stm='+x.value,
		        url: '/HNSAS/studlist',
		        data: JSONObject,
		        type: "POST",	        
		        dataType: 'application/json; charset=utf-8',
		        success: function (data) {
		        		        		        	        	
		        	var returnedData = JSON.parse(data);	   
		        
		        	//$("#att_head").append("<td align='center' rowspan='2'>ROLL NO</td>");
		        	
		        	var tot = 1;
		        	 
		        	 $.each(returnedData, function(i, field){
		        		 
		              //$("#data").append("");
		              
		              var str = "<tr ><td id='dat' align='center'>"+field["rollno"]+"</td>";
		              
		             $.each(field["subject"], function(i, ff){
		            	   str = str + "<td  align='center'>"+ff["sub"]+"</td>";	            	   
		               }); 
	
		          
		             console.log("Roll no : " + field["rollno"]);   
		           	
		             	var Stdrno= {"rno":field["rollno"],"stm":strm.value+"_sem_"+sem.value+"_"+cls.value};
		           	
		           
		             
		           		$.ajax({
				        //url: '/HNSAS/studlist?stm='+x.value,
				        url: '/HNSAS/studlac',
				        data: Stdrno,
				        type: "POST",	        
				        dataType: 'application/json; charset=utf-8',
				        async: false,
				        success: function (data) {
				        		        		        	        	
				        	var retData = JSON.parse(data);	  			        	
				        	
				        	 $.each(retData, function(i, field){
				        		 
				        		 str = str + "<td  style='background-color:#004080;' align='center'>"+field["attlac"]+"</td>";		       
				               
				             });
				        	 
				        	 
				        	 $("#data").append(str + "</tr>"); 
				        	 
				        	 
				        },                
				        
		           	
				        
				    });
		           
		   
		             
		            //  str = str + "<td align='center'>"+field["attlac"]+"</td>";
		              
		           //   tot = tot + 1;
		              
		             // $("#data").append(str + "</tr>"); 
		               
		           		
		             });	           
		        },	                
		 		        
		    });
		 
	 
	}
	
	
	$("#att_head").live("click",function(){
		$("#data tr").removeClass('highlight');
	});
	
	$("#data tr").live("click",function(){
		if(!document.getElementById('mulsel').checked)
			{
				$("#data tr").removeClass('highlight');
			}
		$(this).addClass('highlight');
		$("#att_head").removeClass('highlight');
		$("#lac_head").removeClass('highlight');
	});
	
	 
});




</script>


<style type="text/css">
#grid{
	width: 100%;
    height: 150px;
    
}
table { border: 2px solid white;
	border-collapse: collapse;
	color: white;
	
	}
td { border: thin solid white }

.highlight{
	background-color: green;
	font-weight:bold;
	color: yellow;
	}

</style>

</head>
<body >

<div style="width: 100%; margin-bottom: 20px; margin-top: 20px;" align="center">

	<table>
		<tr>
			<td>STREAM</td>
			<td><select id="strm">
				<option value="BBA">BBA</option>
				<option value="BCOM">BCOM</option>				
			</select></td>
		</tr>
		<tr>
			<td>SEMESTER</td>
			<td><select id="sem">
				<option value="1">1st</option>
				<option value="3">3rd</option>
				<option value="5">5th</option>				
			</select></td>
		</tr>
		
		<tr>
			<td>CLASS</td>
			<td><select id="class">
				<option value="A">A</option>
				<option value="B">B</option>
				<option value="C">C</option>
				<option value="D">D</option>
				<option value="E">E</option>				
			</select></td>
		</tr>	
		
		<tr>
			<td>SUBJECT</td>
			<td><select id="sub" disabled="disabled" >
				<option value="NOSUB">SELECT SUBJECT</option>
				<option value="CS">CS</option>				
				<option value="FBO">FBO</option>
				<option value="GSI">GSI</option>	
				<option value="ECO">ECO</option>
				<option value="AC">AC</option>
				<option value="POM">POM</option>
				<option value="MAT">MAT</option>
				<option value="OAT">OAT</option>
				<option value="MM">MM</option>
				<option value="HRM">HRM</option>
				<option value="ME">ME</option>
				<option value="ENT">ENT</option>
				<option value="STAT">STAT</option>
				<option value="FM">FM</option>
				<option value="CAC">CAC</option>
				<option value="BE">BE</option>
				<option value="TAX">TAX</option>
				<option value="MGTAC">MGTAC</option>
				<option value="BL">BL</option>
				<option value="AFM">AFM</option>
				<option value="AMM">AMM</option>						
			</select></td>
		</tr>
		
		<tr>
			<td id="waitmsg" colspan="2" align="center" style="background-color: red; font-weight: bold;">
				  <img alt="" src="images/processing.gif">				  
			</td>
			<td id="buttongrid" colspan="2" align="center" >
			<button id="search" > SEARCH </button>
			<button id="export" onclick="fnExcelReport();">Export</button>
			</td>
		</tr>
			
			
	</table>

	

	



</div>




<div id="grid" style="height: 100%" >
	<input type="checkbox" id="mulsel"> <span style="color: white;">Multiple Select</span>
    <table width="100%" id="data" >
    	<tr id="att_head" style="font-weight: bold;" >
    		    				
    	</tr>
    	<tr id="lac_head">    		

    	</tr>   
    	
    	 	
    </table>
    <table  id="dd">
    	
    </table>
    </div>
</body>
</html>