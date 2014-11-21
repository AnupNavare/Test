package com.ecosystem;

import java.lang.reflect.Constructor;
import java.sql.*;
public class SQLConnection {
	//public static void main(String args[]){
	Statement stmt = null;
	Connection con = null;
		public SQLConnection(){
		/*.......Driver Name and Database Name............*/
		String url = "jdbc:mysql://localhost:3306/";
		String driver = "com.mysql.jdbc.Driver";
		
		/*...........Databse Credentials..............*/
		String username = "root";
		String password = "";
		
		
		
		try{
			/*................Register JDBC Driver....*/
			Class.forName(driver);
			
			/*............Open a connection...........*/
			con = DriverManager.getConnection(url,username,password);
			/*..........Creating database................*/
			System.out.println("Connecting to DB");
			stmt = con.createStatement();
			//String createDBsql = "CREATE DATABASE ECORECYCLEDB";
			//stmt.executeUpdate(createDBsql);
			//System.out.println("Database created successfully");
			
			con.close();
		}catch(SQLException se){
			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(stmt != null)
				try{
					stmt.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
				try{
					con.close();
				}
				catch(Exception e){
					e.printStackTrace();
				}
		}   //end finally
	}     //end main

}     //end class
