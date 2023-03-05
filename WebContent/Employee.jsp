<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Salary Slip</title>
</head>
<style>    
table, th, td {    
border: 1px solid black;  
margin-left: auto;  
margin-right: auto;  
border-collapse: collapse;    
width: 500px;  
text-align: center;  
font-size: 20px;  
}    
</style> 
<body>


<table>
  <caption>Salary Details</caption>
  <tr>
    <th>Name</th>
    <th>Gross Salary</th>
    <th>Net Salary with TDS</th>
  </tr>
  <tr>
    <td><%=request.getAttribute("userName")%></td>
    <td><%=request.getAttribute("gross")%></td>
    <td><%=request.getAttribute("net")%></td>
  </tr>

</table>

</body>
</html>