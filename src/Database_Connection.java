import java.sql.*;

public class Database_Connection {

	Connection c;
	Statement s;

	public Database_Connection(){  
	        try{  
	            Class.forName("com.mysql.cj.jdbc.Driver");  
	            c =DriverManager.getConnection("jdbc:mysql:///bankmanagementsystem","root","");    
	            s =c.createStatement(); 
	           
	          
	            
	        }catch(Exception e){ 
	            System.out.println(e);
	        }  
	    }
}
