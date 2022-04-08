package com.Assignment.company.jersey_rest;

import java.util.List;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@jakarta.ws.rs.Path("Student")

public class Crud 
{
	
	Student_DB student=new Student_DB();
	@jakarta.ws.rs.GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Student> getStudent() {
		return student.getStudents();
	}


	@jakarta.ws.rs.Path("insert")
	@jakarta.ws.rs.POST
	public String insertData(Student d) {
		return student.createStudent(d);
	}


	@jakarta.ws.rs.Path("update")
	@jakarta.ws.rs.PUT
	public String updateData(Student s) {
		return student.updateStudent(s);
	}


	@jakarta.ws.rs.Path("delete/{num}")
	@jakarta.ws.rs.DELETE
	public String deleteData(@jakarta.ws.rs.PathParam("num") int num) {
		 return student.deleteStudent(num);
	}


	@jakarta.ws.rs.Path("search/{num}")
	@jakarta.ws.rs.Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	@jakarta.ws.rs.GET
	public Student getData(@jakarta.ws.rs.PathParam("num") int num) {
		 return student.fetchStudent(num);
	}
	
}
