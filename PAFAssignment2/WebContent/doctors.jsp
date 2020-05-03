<%@page import="com.Doctor"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head> 
<meta charset="ISO-8859-1"> 
<title>Doctor Management</title> 
<link rel="stylesheet" href="Views/bootstrap.min.css"> 
<script src="Components/jquery-3.2.1.min.js"></script> 
<script src="Components/main.js"></script> 
</head> 
<body> 
	
	<div class="container"> 
		<div class="row">  
		
			<div class="col-8">       
				<h1 class="m-3">Doctor Management</h1>        
				
				<form id="formDoctor" name="formDoctor" method="post" action="doctors.jsp">  
					Name:  
					<input id="name" name="name" type="text" class="form-control form-control-sm">  
					
					<br> 
					NIC:  
					<input id="nic" name="nic" type="text" class="form-control form-control-sm">  
					
					<br>
					 Address:  
					 <input id="address" name="address" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Mobile Number:  
					 <input id="mobile" name="mobile" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Email:  
					 <input id="email" name="email" type="text" class="form-control form-control-sm">  
					 
					 <br> 
					 Specialization:  
					 <input id="spec" name="spec" type="text" class="form-control form-control-sm"> 
					 
					 <br> 
					 Hospital Name:  
					 <input id="hospital" name="hospital" type="text" class="form-control form-control-sm">

					 <br> 
					 Department Name:  
					 <input id="dept" name="dept" type="text" class="form-control form-control-sm">   
					 
					 <br>  
					 <input id="btnSave" name="btnSave" type="button" value="Save" class="btn btn-primary">  
					 <input type="hidden" id="hidDoctorIDSave" name="hidDoctorIDSave" value=""> 
					 
				</form> 
				
				<div id="alertSuccess" class="alert alert-success"></div>  
				<div id="alertError" class="alert alert-danger"></div> 
				
				<br>  
				<div id="divItemsGrid">   
					<%    
						Doctor docObj = new Doctor();
						out.print(docObj.readDoctors());   
					%>  
				</div> 
				  
 			</div>
 		 
 		</div>    
 		
 
	</div> 
</body> 
</html> 