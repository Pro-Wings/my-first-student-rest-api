package com.prowings.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.prowings.entity.Student;

//@Controller
@RestController
public class StudentController {
	
//	@GetMapping(value = "/hello",  produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value = "/hello")
//	@ResponseBody
	public Student helloWorld()
	{
		return new Student(10, "Ram", "Pune");
	}

	@PostMapping("/hello")
	public Student helloWorld2(@RequestBody Student std)
	{
		System.out.println(std);
		
		return std;
	}

}
