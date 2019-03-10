
package DBsetup;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 *
 * @author clone-sniper
 */
public class connectDB {
    
        public static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
        public static final  String url = "jdbc:derby://localhost:1527/CRM;create=true;";
        public static final  String username = "app";
        public static final  String password = "app";
    
    //Makes connection to DB Server based on string stored in url as long as JavaDB is running
    public Connection getConnection() throws Exception
    {
        try
        {
        Class.forName(driver);       
        Connection conn = DriverManager.getConnection(url,username,password);
        return conn;
        }
        catch(Exception e)
        {           
            System.out.println(e);
        }
        
        return null;
    }
    
    //Opens connection to CRM db server and creates a table with the values of ID,Name,Email, and phone number
    //returns a connection object so a connection doesn't have to be constantly opened if the tabled is to be changed
    public Connection createTable() throws Exception
    {
        try
        {   
            Connection conn = getConnection();  
            DatabaseMetaData md = conn.getMetaData();
            ResultSet rs = md.getTables(null, null, "CUSTOMER", null);
            if(!rs.next())
            {
                conn.createStatement().execute("create table Customer(ID int NOT NULL  GENERATED BY DEFAULT AS IDENTITY (START WITH 1, INCREMENT BY 1) primary key, name varchar(20), Email varchar(30),PhoneNumber varchar(12))");
                System.out.println("Customer Table Created");
            }
            return conn;
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return null;
    }    
}
