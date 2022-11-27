package com.quiz.corecode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.quiz.connection.ConnectionClass;

public class StudentTest {

	Connection connection=null;
	
	PreparedStatement preStmt;
	ArrayList <String> studRes= new ArrayList<>();
	
	public ArrayList<String> startTest() {
		
		ConnectionClass conClass= new ConnectionClass();
		try {
			connection=conClass.getConnectionDetails();	
			//System.out.println("Connection established");
			
			String sql="select questions.qid,questions.qname, options.A, options.B, options.C,"
					+ "options.D from questions  inner join options where questions.qid=options.qid;";
			
			//stmt=connection.createStatement();	
			preStmt= connection.prepareStatement(sql);
						
			ResultSet rs=preStmt.executeQuery();	
			
			
			while(rs.next()) {
				
	        	System.out.println(rs.getInt(1)+". " +rs.getString(2));
	        	System.out.println("A : " +rs.getString(3));
	        	System.out.println("B : " +rs.getString(4));
	        	System.out.println("C : " +rs.getString(5));
	        	System.out.println("D : " +rs.getString(6));
	        	Scanner sc = new Scanner (System.in);
	        	System.out.println("select the correct option");	
	        	String ans=sc.next();
	        	//Improve here
	        	studRes.add(ans.toUpperCase());
			}
		    //System.out.println(studRes);
			connection.close();
			preStmt.close();
			
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return studRes;
	}	
}
