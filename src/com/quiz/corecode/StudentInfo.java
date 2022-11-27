package com.quiz.corecode;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.quiz.connection.ConnectionClass;

public class StudentInfo {

	int id;
	String name;
	int score ;
	String grade;

	Connection conn = null;
	PreparedStatement preStmt;
	ConnectionClass conClass= new ConnectionClass();

	ArrayList<String> studRes;
	ArrayList<String> ansRes = new ArrayList<>();

	public void enterInfoandStartTest() {

		Scanner sc = new Scanner(System.in);

		System.out.println("Enter the student id: ");
		id=sc.nextInt();

		System.out.println("Enter the student name: ");
		name = sc.next();

		studRes = new StudentTest().startTest();

		System.out.println();
		score=getScore(studRes);
		System.out.println("Score of student: "+score);

		grade=getGrade(score);
		System.out.println("Grade of student: "+grade);

		new Student().storeStudentDetails(id,name,grade,score);
	}

	public String getGrade(int score) {
		String grade="";

		if(score>=8 && score<=10) {
			grade="Class A";
		}else if(score>=6 && score<8) {
			grade="Class B";
		}else if(score==5) {
			grade="Class C";
		}else {
			grade="Class D";
		}

		return grade;
	} 

	public int getScore(ArrayList<String> studRes) {

		int count=0;

		try {
			conn = new ConnectionClass().getConnectionDetails();

			String sql = "select qid, ans from answers";
			preStmt = conn.prepareStatement(sql);

			ResultSet rs = preStmt.executeQuery();

			while (rs.next()) {

				ansRes.add(rs.getString(2));
			}
			
			/*
			 * System.out.println(studRes); System.out.println(ansRes);
			 * System.out.println(ansRes.size());
			 */
                      //i=0
		    //studRes:  A, B, C, D, A, B, C , D, A, B
			//ansRes: A, C, D, A , B, A , A, B, A, D
			         //j=0
			
			for (int i = 0,j = 0; i < studRes.size(); i++, j++) {

				if(studRes.get(i).equals(ansRes.get(j))) {
					count+=1;	
				}
			}
			//System.out.println(count);
			conn.close();
			preStmt.close();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

		return count;
	}

	public void getAllStudentDetails() {

		try {
			conn=conClass.getConnectionDetails();	
			//System.out.println("Connection established");

			String sql="select id, name, score from studentDetails order by score";

			//stmt=connection.createStatement();	
			preStmt= conn.prepareStatement(sql);

			ResultSet rs=preStmt.executeQuery();	


			while(rs.next()) {

				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
				System.out.println("----------------");
			}
			//System.out.println(studRes);
			conn.close();
			preStmt.close();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}
	}

	public void getSpecificStudentDetails(int id) {

		try {
			conn=conClass.getConnectionDetails();	
			//System.out.println("Connection established");

			String sql="select id, score from studentDetails where id=?";

			//stmt=connection.createStatement();	
			preStmt= conn.prepareStatement(sql);
			preStmt.setInt(1, id);

			ResultSet rs=preStmt.executeQuery();

			while(rs.next()) {

				System.out.println("Student Id: "+rs.getInt(1)+" "+"Score: "+rs.getInt(2));					
			}	
			conn.close();
			preStmt.close();

		} catch (ClassNotFoundException | SQLException e) {

			e.printStackTrace();
		}

	}
}
