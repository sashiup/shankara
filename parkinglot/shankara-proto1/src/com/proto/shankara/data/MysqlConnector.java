package com.proto.shankara.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.proto.shankara.pojo.Employee;

public class MysqlConnector {
	private Logger sqlLog = Logger.getLogger(MysqlConnector.class);
	protected static String url = "jdbc:mysql://localhost:3306/euphonyadmin";
	protected static String userName = "surya";
	protected static String pass = "krishna";

	public List<Employee> searchEmployee(Map<String,String> srch){
		List<Employee> employees = new ArrayList<Employee>();
		try
		{
			// Load the database driver
			Class.forName( "com.mysql.jdbc.Driver" ) ;

			// Get a connection to the database
			Connection conn = DriverManager.getConnection(url,userName, pass) ;

			// Print all warnings
			for( SQLWarning warn = conn.getWarnings(); warn != null; warn = warn.getNextWarning() )
			{
				sqlLog.debug( "SQL Warning:" ) ;
				sqlLog.debug( "State  : " + warn.getSQLState()  ) ;
				sqlLog.debug( "Message: " + warn.getMessage()   ) ;
				sqlLog.debug( "Error  : " + warn.getErrorCode() ) ;
			}
			StringBuilder query = new StringBuilder("SELECT * FROM euphonyadmin.employee");
			if(srch.size() > 0){
				query.append(" where ");
			}
			if(srch.get("empLastName") !=null && !srch.get("empLastName").isEmpty() ){
				query.append(" lastname=").append("'").append(srch.get("empLastName")).append("'");
			}
			if(srch.get("empFirstName") !=null && !srch.get("empFirstName").isEmpty() ){
				query.append("and firstname=").append("'").append(srch.get("empFirstName")).append("'");;
			}
			// Get a statement from the connection
			Statement stmt = conn.createStatement() ;
			sqlLog.debug("******************************");
			sqlLog.debug("Query::"+query.toString());
			sqlLog.debug("******************************");
			// Execute the query
			ResultSet rs = stmt.executeQuery(query.toString()) ;
			// Loop through the result set
			while( rs.next() ){
				sqlLog.debug( rs.getString("firstname") ) ;
				Employee em = new Employee();
				em.setFirstName(rs.getString("firstname"));
				em.setLastName(rs.getString("lastname"));
				em.setEmail(rs.getString("email"));
				em.setTitle(rs.getString("title"));
				employees.add(em);
			}
			// Close the result set, statement and the connection
			rs.close() ;
			stmt.close() ;
			conn.close() ;
		}
		catch( SQLException se )
		{
			sqlLog.debug( "SQL Exception:" ) ;

			// Loop through the SQL Exceptions
			while( se != null )
			{
				sqlLog.debug( "State  : " + se.getSQLState()  ) ;
				sqlLog.debug( "Message: " + se.getMessage()   ) ;
				sqlLog.debug( "Error  : " + se.getErrorCode() ) ;

				se = se.getNextException() ;
			}
		}
		catch( Exception e )
		{
			sqlLog.debug( e ) ;
		}
		return employees;
	}
}
