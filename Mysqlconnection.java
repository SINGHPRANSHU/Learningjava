package swingDemo;
import java.sql.*;
import javax.swing.*;

public class Mysqlconnection {
   public static Connection dbConnector() {
	   try {
	   String url="jdbc:mysql://localhost:3306/first";
		String uname="root";
		String pass="005501";
		
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(url,uname,pass);
		JOptionPane.showMessageDialog(null,"connection successfull");
//		Statement st=con.createStatement();
//		ResultSet rs =st.executeQuery(Query);
//		 while(rs.next())
//	       {String userdata=rs.getInt(1)+":"+rs.getString(2);
//	       
//	       System.out.println(userdata);}
//	   
//	       st.close();
//	       con.close();
		return con;
		}
	   catch(Exception e) {
		   JOptionPane.showMessageDialog(null,e);
		   return null;
	   }
   }
}
