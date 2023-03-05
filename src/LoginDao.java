import java.sql.*;

public class LoginDao {
	public String authenticateUser(LoginInfo logininfo)
	{
	    String userName = logininfo.getUserName();
	    String password = logininfo.getPassword();
	 
	    Connection con = null;
	    Statement statement = null;
	    ResultSet resultSet = null;
	 
	    String userNameDB = "";
	    String passwordDB = "";
	    String roleDB = "";
	 
	    try
	    {
	        con = DBConnection.createConnection();
	        System.out.println("Connection set");
	        statement = con.createStatement();
	        resultSet = statement.executeQuery("select username,password,UserRole from login ");
	 
	        while(resultSet.next())
	        {
	            userNameDB = resultSet.getString("username");
	            passwordDB = resultSet.getString("password");
	            roleDB = resultSet.getString("UserRole");
	 
	            if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("Admin"))
	            return "Admin_Role";
	          
	            else if(userName.equals(userNameDB) && password.equals(passwordDB) && roleDB.equals("User"))
	            return "User_Role";
	        }
	    }
	    catch(SQLException e)
	    {
	        e.printStackTrace();
	    }
	    return "Invalid user credentials";
	}

}
