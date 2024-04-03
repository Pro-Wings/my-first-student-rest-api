package com.prowings.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.prowings.dao.StudentDao;
import com.prowings.entity.Student;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentDao studentDao;
	
	@Override
	public boolean saveStudent(Student std) {
		System.out.println("inside StudentService :: saveStudent()");
		return studentDao.saveStudent(std);
	}

	@Override
	public Student getStudentById(int id) {
		System.out.println("inside StudentService :: getStudentById()");
		return studentDao.getStudentById(id);
		
	}

	@Override
	public List<Student> getAllStudents() {
		System.out.println("inside StudentService :: getAllStudents()");
		
		return studentDao.getAllStudents();
	}

	@Override
	public List<Student> findByCity(String city) {
		return studentDao.findByCity(city);
	}

	@Override
	public List<Student> findAllSortedByField(String field) {
		return studentDao.findAllSortedByField(field);
	}

	@Override
	public boolean deleteStudentById(int id) {
		System.out.println("inside StudentService :: getStudentById()");
		return studentDao.deleteStudentById(id);
	}

	@Override
	public boolean updateStudent(Student student) {
		System.out.println("inside StudentService :: saveStudent()");
		return studentDao.updateStudent(student);
	}

}
