package com.prowings.dao;

import java.util.List;

import com.prowings.entity.Student;

public interface StudentDao {
	
	public boolean saveStudent(Student student);
	
	public Student getStudentById(int id);

	public List<Student> getAllStudents();

	public List<Student> findByCity(String city);

	public List<Student> findAllSortedByField(String field);

	public boolean deleteStudentById(int id);

	public boolean updateStudent(Student student);


}
