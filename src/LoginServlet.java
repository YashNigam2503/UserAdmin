import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
@WebServlet("/src/LoginServlet") 
public class LoginServlet extends HttpServlet {
private static final long serialVersionUID = 1L;
 
public LoginServlet() {
	System.out.println("Servlet is working");
}
 
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
{
    String userName = request.getParameter("username");
    String password = request.getParameter("password");
 
    LoginInfo logininfo = new LoginInfo();
 
    logininfo.setUserName(userName);
    logininfo.setPassword(password);
 
    LoginDao loginDao = new LoginDao();
    
	PrintWriter out =response.getWriter();
    
    Connection conn = null;
    Statement statement = null;
    ResultSet resultSet = null;
 
 
    try
    {
        String userValidate = loginDao.authenticateUser(logininfo);
 
        if(userValidate.equals("Admin_Role"))
        {
            System.out.println("Admin's Home");
 
            HttpSession session = request.getSession(); 
            session.setAttribute("Admin", userName); 
            request.setAttribute("userName", userName);
 
            request.getRequestDispatcher("/Admin.jsp").forward(request, response);
        }
        else if(userValidate.equals("User_Role"))
        {
		      try {
			        conn = DBConnection.createConnection();
			        statement = conn.createStatement();
			        resultSet = statement.executeQuery("select * from salarydetails where EmpID='"+userName+"'");
			        
			        out.println("<html><head>"); 
			        out.println("<title>"+userName+" Details</title></head><body>");
			        out.println("<u><center><h1>"+userName+" Salary Details </h1></center></u><br>");
			        
			      
			        
			        while(resultSet.next()){
			        	
			        	 
				        if(resultSet.getString("BasicSalary")==null) {
	                  	  out.println("<h3 style=\"color:red\"> NOTE: Salary details not present in database. Please contact ADMIN.<h3>");
	                  	  break;
	                    }
			     
			          out.println("<table style=\"float: left;margin-left:150px;margin-right:200px\" width=\"30%\" align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">");
			          out.println("<tr><th>Salary Components</th><th>IN RUPEES</th></tr>");
			          out.println("<tr><td>BasicSalary</td><td>"+resultSet.getString("Basic_Salar")+"</td></tr>");
			          out.println("<tr><td>HRA</td><td>"+resultSet.getString("hra")+"</td></tr>");
			          out.println("<tr><td>Transport</td><td>"+resultSet.getString("TA")+"</td></tr>");
			          out.println("<tr><td>Dearness</td><td>"+resultSet.getString("Dearness")+"</td></tr>");
			          out.println("<tr><td>Entertainment</td><td>"+resultSet.getString("Entertain")+"</td></tr>");
			          out.println("<tr><td>General</td><td>"+resultSet.getString("GeneralA")+"</td></tr>");
			          out.println("<tr><td>ProvidentFund</td><td>"+resultSet.getString("Provident")+"</td></tr>");
			          out.println("<tr><td>Medical</td><td>"+resultSet.getString("Mediclaim")+"</td></tr>");
			          out.println("<tr><td>TDS</td><td>"+resultSet.getString("tds")+"</td></tr>");
			          out.println("</table> ");
			        
			        
			        int Basic_Salary = Integer.parseInt(resultSet.getString("Basic_Salar"));
					int GeneralAllowance = Integer.parseInt(resultSet.getString("GeneralA"));
					
					CalcSalary c=new CalcSalary();
					c.setHra(Basic_Salary);
					c.setDearness();
					c.setEntertainment(Basic_Salary);
					c.setTransport(Basic_Salary);
					c.setMediclaim(Basic_Salary);
					c.setProvident(Basic_Salary);
					c.setTds(Basic_Salary, GeneralAllowance);
					
					int gross=c.gSalary(Basic_Salary, GeneralAllowance);
					int total=c.total(Basic_Salary, GeneralAllowance);
					int net=c.netSalary(Basic_Salary, GeneralAllowance);
					int monthly=c.monthly(Basic_Salary, GeneralAllowance);
					
					  out.println("<table style=\"float: left\" width=\"30%\" align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">");
			          out.println("<tr><th>GrossSalary</th><th>NetSalary</th><th>NetSalary(Taxes Included)</th><th>MonthlySalary</th></tr>");
			          out.println("<tr><td>"+gross+"</td><td>"+total+"</td><td>"+net+"</td><td>"+monthly+"</td></tr>");
			          out.println("</table>");
			          
			          out.println("</body></html>");
			          
					
			          
			      }} catch (Exception e) {
			        // TODO Auto-generated catch block
			        e.printStackTrace();
			      }
        }
        else
        {
            System.out.println("Error message = "+userValidate);
            request.setAttribute("errMessage", userValidate);
 
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
    catch (IOException e1)
    {
        e1.printStackTrace();
    }
    catch (Exception e2)
    {
        e2.printStackTrace();
    }
} //End of doPost()
}