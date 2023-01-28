package todolist;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.jdbc.ResultSetMetaData;

public class DB {
	Connection con = null;
	java.sql.PreparedStatement pst;
	
	public static Connection dbconnect() {
		
		
		Connection conn;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/todolist","root","");
			return conn;
		
		} catch (Exception e2) {
			// TODO Auto-generated catch block
			System.out.println(e2);
			return null;
		} 
		
	}

}

