import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SalaryServlet
 */
@WebServlet("/SalaryServlet")
public class SalaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SalaryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType ("text/html");
		String userName = request.getParameter("username");
		int Basic_Salary = Integer.parseInt(request.getParameter("Basic_Salary"));
		int GeneralAllowance = Integer.parseInt(request.getParameter("General_Allowance"));
		String EID=request.getParameter("empID");
		SalaryInfo info =new SalaryInfo();
		
		
		if(GeneralAllowance>Basic_Salary/4) {
			
		//	 HttpSession session = request.getSession();
	    //        session.setMaxInactiveInterval(10*60);
			System.out.println("Error message = GeneralAllowance can only be 25% of BasicSalary");
            request.setAttribute("errMessage", "NOTE : GeneralAllowance cannot be \n greater than 25% BasicSalary");
 
            request.getRequestDispatcher("/Admin.jsp").forward(request, response);
		}
		
		info.setUserName(userName);
		info.setBasic_Salary(Basic_Salary);
		info.setGeneralAllowance(GeneralAllowance);
		info.setEmpID(EID);
		
		PrintWriter out =response.getWriter();
		
		
		CalcSalary c=new CalcSalary();
		c.setHra(Basic_Salary);
		c.setDearness();
		c.setEntertainment(Basic_Salary);
		c.setTransport(Basic_Salary);
		c.setMediclaim(Basic_Salary);
		c.setProvident(Basic_Salary);
		c.setTds(Basic_Salary, GeneralAllowance);
		
		int hra=c.getHra();
		int dearness=c.getDearness();
		int transport=c.getTransport();
		int entertain=c.getEntertainment();
		int med=c.getMediclaim();
		int prov=c.getProvident();
		int tds=c.getTds();
		int gross=c.gSalary(Basic_Salary, GeneralAllowance);
		int total=c.total(Basic_Salary, GeneralAllowance);
		int net=c.netSalary(Basic_Salary, GeneralAllowance);
		int monthly=c.monthly(Basic_Salary, GeneralAllowance);
		
		 //  out.println("<h1>"+"hello</h1><br>");
				
		 
		      try {
		    	  int i;
		        Connection conn = DBConnection.createConnection();
		        PreparedStatement stmt = conn.prepareStatement("UPDATE salarydetails SET `BasicSalary`=?,`hra`=?,`TA`=?,`Dearness`=?,`Entertain`=?,`GeneralA`=?,`Provident`=?,`mediclaim`=?,`tds`=? WHERE `EmpID`=?");              
		        
		        stmt.setInt(1 , Basic_Salary );
		        stmt.setInt(2 , hra );
		        stmt.setInt(3 , transport );
		        stmt.setInt(4 , dearness );
		        stmt.setInt(5 , entertain);
		        stmt.setInt(6 ,  GeneralAllowance );
		        stmt.setInt(7 , prov );
		        stmt.setInt(8, med);
		        stmt.setInt(9, tds);
		        stmt.setString(10, EID);
		          i =   stmt.executeUpdate();
		       
		          out.println("<html><head>"); 
		          out.println("<title>"+userName+" Details</title></head><body>");
		          out.println("<u><center><h1>"+userName+" Salary Details </h1></center></u><br>");

		          out.println("<table style=\"float: left;margin-left:150px;margin-right:200px\" width=\"30%\" align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">");
		          out.println("<tr><th>Salary Components</th><th>IN RUPEES</th></tr>");
		          out.println("<tr><td>HRA</td><td>"+hra+"</td></tr>");
		          out.println("<tr><td>Transport</td><td>"+transport+"</td></tr>");
		          out.println("<tr><td>Dearness</td><td>"+dearness+"</td></tr>");
		          out.println("<tr><td>Entertainment</td><td>"+entertain+"</td></tr>");
		          out.println("<tr><td>General</td><td>"+GeneralAllowance+"</td></tr>");
		          out.println("<tr><td>ProvidentFund</td><td>"+prov+"</td></tr>");
		          out.println("<tr><td>Medical</td><td>"+med+"</td></tr>");
		          out.println("<tr><td>TDS</td><td>"+tds+"</td></tr>");
		          out.println("</table>");
		          
		          out.println("<table style=\"float: left\" width=\"30%\" align=\"center\" cellpadding=\"5\" cellspacing=\"5\" border=\"1\">");
		          out.println("<tr><th>GrossSalary</th><th>NetSalary</th><th>NetSalary(Taxes Included)</th><th>MonthlySalary</th></tr>");
		          out.println("<tr><td>"+gross+"</td><td>"+total+"</td><td>"+net+"</td><td>"+monthly+"</td></tr>");
		          out.println("</table>");
		          
		          out.println("</body></html>");
		          
		      } catch (Exception e) {
		        // TODO Auto-generated catch block
		        e.printStackTrace();
		      }
		
	
	}
}
	
	
	
	
	
	/* try {
	Connection con= DBConnection.createConnection();
	Statement statement = con.createStatement();
    ResultSet resultSet = statement.executeQuery("select Basic_Salar from SalaryDetails where EmpID="+EID);
 
        while(resultSet.next())
        {
        	 i = (Integer) resultSet.getObject("Basic_Salar");
        	 
        }
        if ( i == null) {
        	out.println("<h1>"+"VAlue is NULL</h1>");
    	 }else {
    		 
    	 }
	 
	 }
    catch(SQLException e)
    {
        e.printStackTrace();
    }

}*/
