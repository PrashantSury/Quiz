package com.quiz.drivercode;

import java.util.Scanner;

import com.quiz.corecode.StudentInfo;

public class DriverClass {

	public static void main(String[] args) {

		StudentInfo studTest = new StudentInfo();

		Scanner sc= new Scanner (System.in);

		System.out.println("Enter the choices as below:  ");
		System.out.println(" 1 For : To see the studentlist with their marks in sorting order");
		System.out.println(" 2 For : To give the test");
		System.out.println(" 3 For : To see the particular student details");
		int i=sc.nextInt();

		switch(i) {
		case 1:  new StudentInfo().getAllStudentDetails();
		         break;
			
		case 2: studTest.enterInfoandStartTest();
		        System.out.println();
	           	System.out.println("Test is completed");
	           	break;
	           	
		case 3: System.out.println("Enter the student id to get the details");
		        int id=sc.nextInt();
			    new StudentInfo().getSpecificStudentDetails(id);
	            break;
		default: 
			System.out.println("Enter the correct input choice");
		}
	}
}
