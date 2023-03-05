<%@ page import="java.sql.*" %>
<% Class.forName("com.mysql.cj.jdbc.Driver"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>AdminPage</title>
</head>
<style>
caption{
font-weight:bold;
font-size:24px;
}
h2{
margin-left:20px;
}
</style>
<body>
<center><h1>Admin's Home</h1></center>

<h2>Welcome <%=request.getAttribute("userName") %></h2>

<br>
<br>

<div>
<table style="float: left;margin-left:20px;margin-right:200px" width="30%" align="center" cellpadding="5" cellspacing="5" border="1">
<caption>List Of Employees</caption>
<tr>
<th>ID</th>
<th>Employee Name</th>
</tr>

  <%
  try{
  Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/useradmin","root","Agnel@04");  
  Statement statement = con.createStatement();
  ResultSet resultSet = statement.executeQuery("select EmpID,username from login");
  
  while(resultSet.next()){
    %>
<tr bgcolor="#DEB887">
<td><%=resultSet.getString("EmpID") %></td>
<td><%=resultSet.getString("username") %></td>
</tr>


<% }
  
  } catch (Exception e) {
	  e.printStackTrace();
	  } 
	  %>
  </table>
<form name="Salary" action="<%=request.getContextPath()%>/SalaryServlet" method="Post">
 
        <table style="float: left">
        <caption> Enter Employee Details: </caption>
        <tr>
        <td>Employee ID :</td>
        <td><input type="number" name="empID" /></td>
        </tr>
        <tr>
        <td>Enter Name :</td>
        <td><input type="text" name="username" /></td>
        </tr>
        <tr>
        <td>Basic Salary :</td>
        <td><input type="number" name="Basic_Salary" /></td>
        </tr>
        <tr>
        <td>General Allowance :</td>
        <td><input type="number" name="General_Allowance" /></td>
        </tr>
        <tr>
      
        <td>  <br> <span style="color:red"><%=(request.getAttribute("errMessage") == null) ? "" : request.getAttribute("errMessage")%></span></td>
        </tr>
        <tr>
        <td></td>
        <td><input type="submit" value="Submit"></input><input type="reset" value="Reset"></input></td>
        </tr>
        </table>
        </form>
</div>
</body>
</html>