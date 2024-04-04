package com.prowings.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.entity.Student;
import com.prowings.exception.StudentNotFoundException;
import com.prowings.model.MyCustomError;
import com.prowings.service.StudentService;

@RestController
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
//	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping("/students")
	public ResponseEntity<String> saveStudent(@RequestBody Student student)
	{
		System.out.println("request received to save the student to DB!!");
		System.out.println("Incoming student object : "+student);
		boolean res = studentService.saveStudent(student);
		if (res)
			return new ResponseEntity<String>("Student saved successfully!!!", HttpStatus.CREATED);
		else
			return new ResponseEntity<String>("Error while saving the Student!!!", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@GetMapping("/students")
	public List<Student> getAllStudents()
	{
		System.out.println("request received to fetch Students from DB!!");
		return studentService.getAllStudents();
	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) throws StudentNotFoundException, SQLException
	{
		System.out.println("request received to fetch Student of id: "+id +"from DB!!");
		
		if(id==0)
		throw new StudentNotFoundException(id);
		else if(id == 2)
			throw new SQLException();
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("my header", "myHeaderValue");
		headers.add("aaaa", "bbbb");
		
		return new ResponseEntity<Student>(studentService.getStudentById(id),headers ,HttpStatus.OK);
	}

	@GetMapping("/students/search")
	public List<Student> getAllStudentsSearchBy(@RequestParam("city") String city)
	{
		System.out.println("request received to fetch all Students from city : "+city);
		return studentService.findByCity(city);
	}

	@GetMapping("/students/sort")
	public List<Student> getAllStudentsSortBy(@RequestParam("field") String field)
	{
		System.out.println("request received to fetch all Students and sort by : "+field);
		return studentService.findAllSortedByField(field);
	}
	
	
	@DeleteMapping("/students/{id}")
	public ResponseEntity<String> deleteStudentById(@PathVariable int id)
	{
		System.out.println("request received to delete Student of id: "+id +"from DB!!");
		return studentService.deleteStudentById(id) ? new ResponseEntity<String>("Deleted Successfully", HttpStatus.NO_CONTENT) : new ResponseEntity<String>("Failed to delete", HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/students")
	public String updateStudent(@RequestBody Student student)
	{
		System.out.println("request received to update the student");
		System.out.println("Incoming student object : "+student);
		boolean res = studentService.updateStudent(student);
		if (res)
			return "Student updated successfully!!!";
		else
			return "Error while updating the Student!!!";
	}

//	@ExceptionHandler(StudentNotFoundException.class)
//	public ResponseEntity<MyCustomError> handleEmployeeNotFoundException(HttpServletRequest request, Exception ex){
//		
//		MyCustomError error = new MyCustomError();
//		error.setMessage(ex.getMessage());
//		error.setRootCause("abc");
//		error.setStatusCode(404);
//		
//		return new ResponseEntity<MyCustomError>(error, HttpStatus.NOT_FOUND);
//	}	
	
}
