package com.Assignment.company.jersey_rest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Student_DB 
{
	Connection con;
	String url="jdbc:mysql://localhost:3306/assignment";
	String user="root";
	String pwd="root";
	{
	try {
		Class.forName("com.mysql.jdbc.Driver");
		con=DriverManager.getConnection(url,user,pwd);

	} catch (Exception e) {
		e.printStackTrace();
	}	
	}
	public List<Student> getStudents(){
		List<Student> stud=new ArrayList<Student>();
		String sql="select * from Student";
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			while(rs.next()) {
				Student s=new Student();
				s.setStudent_No(rs.getInt(1));
				s.setStudent_Name(rs.getString(2));
				s.setStudent_DOB(rs.getDate(3));
				s.setStudent_DOJ(rs.getDate(4));
				stud.add(s);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return stud;
  }
	public String createStudent(Student d) {
		String Message="Data Inserted Successfully";
		String ErrMessage="Data Insertion Failed";
		int flag=0;
		String sql="insert into student values(?,?,?,?)";
		try {
			java.sql.Date date1=new java.sql.Date(d.getStudent_DOB().getTime());
			java.sql.Date date2=new java.sql.Date(d.getStudent_DOJ().getTime());
			PreparedStatement st=con.prepareStatement(sql);
			st.setInt(1,d.getStudent_No());
			st.setString(2,d.getStudent_Name());
			st.setDate(3,date1);
			st.setDate(4,date2);
			st.executeUpdate();
			flag=1;
			} catch (SQLException e) {
			e.printStackTrace();
		}
		if(flag==1) {
		return Message;
		}else {
			return ErrMessage;
		}

	}
	public String updateStudent(Student s) {
		String sql="update student set Student_Name=?,Student_DOB=?,Student_DOJ=? where Student_No=?";
		String Message="Data Updated Successfully";
		String ErrMessage="Data Updation Failed";
		int flag=0;
		try {
			java.sql.Date date1=new java.sql.Date(s.getStudent_DOB().getTime());
			java.sql.Date date2=new java.sql.Date(s.getStudent_DOJ().getTime());
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,s.getStudent_Name());
			st.setDate(2,date1);
			st.setDate(3,date2);
			st.setInt(4,s.getStudent_No());
			st.executeUpdate();
			flag=1;
		} catch (SQLException e) {

			e.printStackTrace();
		}
		if(flag==1) {
			return Message;
			}else {
				return ErrMessage;
			}

    }
	public String deleteStudent(int num) {
		int flag=0;
		String sql=String.format("delete from student where Student_No=%d",num);
		try {
			Statement st=con.createStatement();
			 flag=st.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(flag==0) {
			return "Data Not Found!!";
			}else {
				return "Data Deleted Successfully";
			}

	}
	public Student fetchStudent(int num) {
		String sql="select * from student where Student_No="+num;
		try {
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(sql);
			if(rs.next()) {
				Student s=new Student();
				s.setStudent_No(rs.getInt(1));
				s.setStudent_Name(rs.getString(2));
				s.setStudent_DOB(rs.getDate(3));
				s.setStudent_DOJ(rs.getDate(4));
				return s;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
