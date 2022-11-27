package com.quiz.corecode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
//import java.util.Scanner;

import com.quiz.connection.ConnectionClass;

public class Student {

	Connection connection=null;
	PreparedStatement preStmt;
	ConnectionClass conClass= new ConnectionClass();

	public HashSet<Integer>checkDuplicateId(){

		HashSet<Integer> hs= new HashSet<>();

		try {
			connection=conClass.getConnectionDetails();	
			//System.out.println("Connection established");

			String sql="select id from studentDetails";

			//stmt=connection.createStatement();	
			preStmt= connection.prepareStatement(sql);

			ResultSet rs=preStmt.executeQuery();

			while(rs.next()) {

				int studId=rs.getInt(1);
				hs.add(studId);     	
			}	

			connection.close();
			preStmt.close();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
		//System.out.println("Student Ids:"+hs);
		return hs;
	}

	public void storeStudentDetails(int id, String name, String grade,int score) {

		HashSet <Integer> hsId=checkDuplicateId();

		if(hsId.contains(id)) {
			//System.out.println("Student Ids inside the storeStudentDetails:"+id);
			updateStudentDetails(id,name,grade,score);

		}else {

			try {
				connection=conClass.getConnectionDetails();	
				//System.out.println("Connection established");

				String sql="insert into studentDetails values(?,?,?,?)";

				//stmt=connection.createStatement();	
				preStmt= connection.prepareStatement(sql);
				preStmt.setInt(1,id);
				preStmt.setString(2,name);
				preStmt.setString(3,grade);
				preStmt.setInt(4, score);

				preStmt.executeUpdate();

				connection.close();
				preStmt.close();

			} catch (ClassNotFoundException | SQLException e) {

				e.printStackTrace();
			}
		}

	}	

	public void updateStudentDetails(int id, String name, String grade, int score) {

		try {
			connection=conClass.getConnectionDetails();	
			//System.out.println("Connection established");

			String sql="update studentDetails set name=?,score=?,grade=? where id=?";

			//stmt=connection.createStatement();	
			preStmt= connection.prepareStatement(sql);
			preStmt.setString(1, name);
			preStmt.setInt(2, score);
			preStmt.setString(3, grade);
			preStmt.setInt(4, id);

			preStmt.executeUpdate();

			connection.close();
			preStmt.close();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}
}