import java.sql.*;

public class DBConnection {
	
	 public static Connection createConnection()
	    {
	    Connection con = null;
	    String url = "jdbc:mysql://localhost:3306/useradmin";
	    String username = "root";
	    String password = "Agnel@04";
	 
	    try
	    {
	        try
	        {
	            //Class.forName("com.mysql.jdbc.Driver");
	        	Class.forName("com.mysql.cj.jdbc.Driver");
	        }
	        catch (Exception e)
	        {
	            e.printStackTrace();
	        }
	        con = DriverManager.getConnection(url, username, password);
	        System.out.println("Post establishing a DB connection - "+con);
	    }
	    catch (SQLException e)
	        {
	           System.out.println("An error occurred. Maybe user/password is invalid");
	         e.printStackTrace();
	       }
	    return con;


}
	 }
